package com.zhl.rx.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.zhl.rx.R;
import com.zhl.rx.annotation.MyAnnotation;
import com.zhl.rx.bean.Movie;
import com.zhl.rx.utils.Mobile;

import java.lang.reflect.Method;

public class FileDirTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_dir_test);
        Log.d("mytag", "Environment.getExternalStorageState()==" + Environment.getExternalStorageState());
        Log.d("mytag", "Environment.getExternalStorageDirectory()==" + Environment.getExternalStorageDirectory());
        Log.d("mytag", "Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)==" + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC));
        Log.d("mytag", "getCacheDir()==" + getCacheDir());
        Log.d("mytag", "getFilesDir()==" + getFilesDir());
        Log.d("mytag", "getExternalCacheDir()==" + getExternalCacheDir());
        Log.d("mytag", "getExternalFilesDir(null)==" + getExternalFilesDir(null));
        Log.d("mytag", "getExternalFilesDir(Environment.DIRECTORY_DCIM)==" + getExternalFilesDir(Environment.DIRECTORY_DCIM));

        Mobile iphone = new Mobile<String>("iphone");
        Log.d("mytag", "iphone.getType()-------" + iphone.getType());
        Movie movie = new Movie();
        movie.setImg("http://www.zhl.com/annotation/img/head.jpg");
        boolean isMyAnnotation = movie.getClass().isAnnotationPresent(MyAnnotation.class);
        Log.d("mytag", movie.getClass().getSimpleName() + "是否通过MyAnnotation注解过----" + isMyAnnotation);
        if (isMyAnnotation) {
            MyAnnotation myAnnotation = movie.getClass().getAnnotation(MyAnnotation.class);
            Log.d("mytag", "ID=" + myAnnotation.ID()+"---name="+myAnnotation.name());
        }
        try {
            Method getImg = movie.getClass().getDeclaredMethod("getImg",null);
            getImg.setAccessible(true);
            String img = (String) getImg.invoke(movie,null);
            Log.d("mytag","图片地址=="+img);
            Method[] methods = Movie.class.getDeclaredMethods();
            for(Method method:methods){
//                method.setAccessible(true);
//                method.invoke(iphone,null);
                Log.d("mytag",method.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
