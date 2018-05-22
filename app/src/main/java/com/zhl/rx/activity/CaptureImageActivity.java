package com.zhl.rx.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.zhl.rx.R;
import com.zhl.rx.utils.MeasureUtil;
import com.zhl.rx.utils.PermissionUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CaptureImageActivity extends AppCompatActivity {
    private ImageView img;
    private static final int REQ_TAKE_PHOTO = 1;
    private static final int REQ_PICTURE_CUT = 2;
    private static final int REQ_PICK_PHOTO = 3;
    String outPutPath = Environment.getExternalStorageDirectory()+"/rxjavademo/cache/";
    String tempFilePath = null;
    Uri destUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_image);
        img = findViewById(R.id.img);
        Button btnTakePhoto = findViewById(R.id.btn_takephoto);
        Button btnPicImg = findViewById(R.id.btn_pickimg);

        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
            }
        });
        btnPicImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickPhoto();
            }
        });
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==1001&&grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            takePhoto();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case REQ_TAKE_PHOTO:
                    /**
                     * 没有添加MediaStore.EXTRA_OUTPUT的情况下，可以直接data.getExtras().get("data")获取返回的小图，
                     * 要获取全尺寸的照片需要设置MediaStore.EXTRA_OUTPUT存储路径
                     */
//                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
//                    img.setImageBitmap(bitmap);

                    // 先压缩下照片(这里按理要异步操作)
                    Bitmap compressedBitmap = MeasureUtil.compressImage(1080,1920,tempFilePath);
//                    img.setImageBitmap(compressedBitmap);
                    String imgFileName = "temp_avator.jpg";
                    MeasureUtil.saveBitmap2jpg(compressedBitmap,outPutPath,imgFileName);
                    // 删除原始照片
                    new File(tempFilePath).delete();
                    // 开始裁剪
                    Uri srcUri = Uri.fromFile(new File(outPutPath,imgFileName));
                    File fileCroped = new File(outPutPath,"crop_img.jpg");
                    if(fileCroped.exists()){
                        fileCroped.delete();
                    }
                    try {
                        fileCroped.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    destUri = Uri.fromFile(fileCroped);
                    cropImage(srcUri,destUri);
                   break;

                case REQ_PICTURE_CUT:
                    try {
                        final Bitmap corpBitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(destUri));
                        img.setImageBitmap(corpBitmap);
                        // 删除压缩照片
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                new File(outPutPath,"temp_avator.jpg").delete();
                            }
                        }).start();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case REQ_PICK_PHOTO:
                    Uri uri = data.getData();
                    try {
                        File destFile = new File(outPutPath,"crop_img.jpg");
                        if(destFile.exists()){
                            destFile.delete();
                        }
                        destFile.createNewFile();
                        destUri = Uri.fromFile(destFile);
                        cropImage(uri,destUri);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void takePhoto() {
        // 6.0以上需要存储SD卡权限，记得去动态获取，这里测试就直接在设置里面授权了
        File cacheFile = new File(outPutPath);
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        if(!cacheFile.exists()){
            try {
                cacheFile.mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            File tempImg = File.createTempFile(imageFileName,".jpg",cacheFile);
            if(tempImg!=null){
                tempFilePath = tempImg.getAbsolutePath();
                Uri photoUri = FileProvider.getUriForFile(this,"com.zhl.rx.fileprovider",tempImg);
                if(PermissionUtil.needGrantPermission(this,Manifest.permission.CAMERA)){
                    if(PermissionUtil.hasGrantedPermission(this, Manifest.permission.CAMERA)){
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                        startActivityForResult(intent,REQ_TAKE_PHOTO);
                    }else{
                        PermissionUtil.requestPermission(this,Manifest.permission.CAMERA);
                    }
                }else{
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                    startActivityForResult(intent,REQ_TAKE_PHOTO);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void pickPhoto() {

        Intent intent = new Intent(Intent.ACTION_PICK);

//        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
//        intent.addCategory(Intent.CATEGORY_OPENABLE);
        // 打开内容
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,REQ_PICK_PHOTO);

    }

    private void cropImage(Uri srcUri,Uri destUri){
        // 这种方法不是官方API 也可以找一些第三方的图片裁剪库处理
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.setDataAndType(srcUri, "image/*");
        //裁剪图片的宽高比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("crop", "true");//可裁剪
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        intent.putExtra("scale", true);//支持缩放
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, destUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());//输出图片格式
        intent.putExtra("noFaceDetection", true);//取消人脸识别
        startActivityForResult(intent, REQ_PICTURE_CUT);
    }


}
