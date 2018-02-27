package com.zhl.rx.activity;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.zhl.rx.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.bumptech.glide.Glide.with;

public class GlideActivity extends AppCompatActivity {
    private static final String url="http://h.hiphotos.baidu.com/image/pic/item/b7fd5266d016092494670382df0735fae7cd34ec.jpg";
    private static final String gif_url="http://s1.dwstatic.com/group1/M00/88/A0/7eaf482db9c6af6e0ebc8484370489c5.gif";
    ImageView imageView;
    RequestOptions options;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        imageView = (ImageView) findViewById(R.id.image);
        result = (TextView) findViewById(R.id.msg_result);
        options = new RequestOptions()
                .placeholder(R.mipmap.place_holder)
//                .skipMemoryCache(true)// 是否不使用内存缓存
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.mipmap.error)
//                .override(200,100)// 重新制定图片大小
                ;
        // 预加载
        with(this).load(gif_url).preload();

        Button doA = (Button) findViewById(R.id.doA);
        doA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doA();
            }
        });
        Button doB = (Button) findViewById(R.id.doB);
        doB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doB();
            }
        });
        Button doC = (Button) findViewById(R.id.doC);
        doC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doC();
            }
        });
        Button doD = (Button) findViewById(R.id.doD);
        doD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doD();
            }
        });
        Button doE = (Button) findViewById(R.id.doE);
        doE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doE();
            }
        });
        Button doF = (Button) findViewById(R.id.doF);
        doF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doF();
            }
        });
        Button doG = (Button) findViewById(R.id.doG);
        doG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doG();
            }
        });
        Button doH = (Button) findViewById(R.id.doH);
        doH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doH();
            }
        });
        Button doI = (Button) findViewById(R.id.doI);
        doI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doI();
            }
        });
    }


    private void doA() {
        Glide.with(this).load(url).apply(options).into(imageView);
    }

    private void doB() {
        File file = new File(Environment.getExternalStorageDirectory()+"/436506.jpg");
        if(file!=null&&file.exists()){
            Glide.with(this).load(file).apply(options).into(imageView);
        }
    }

    private void doC() {
        Glide.with(this).load(getResources().getDrawable(R.mipmap.movie_banner_default,null)).apply(options).into(imageView);
    }

    private void doD() {
        try {
            InputStream in = getAssets().open("android.jpg");
            byte[] bytes = new byte[1024*100];
            in.read(bytes);
            Glide.with(this).load(bytes).apply(options).into(imageView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void doE() {
        Uri uri = Uri.parse("http://a.hiphotos.baidu.com/image/pic/item/500fd9f9d72a6059f550a1832334349b023bbae3.jpg");
        Glide.with(this).load(uri).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                Toast.makeText(getApplication(),"图片加载完成",Toast.LENGTH_SHORT).show();
                return false;
            }
        }).into(imageView);
    }

    private void doF() {
        Glide.with(this).load("error_image_url").apply(options).into(imageView);
    }

    private void doG() {
        Glide.with(this).load(gif_url).apply(options).into(imageView);
    }

    private void doH() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                FutureTarget<File> futureTarget = with(getApplication())
                        .asFile()
                        .load("http://c.hiphotos.baidu.com/image/h%3D300/sign=1ac3db8a3aadcbef1e3478069cae2e0e/cdbf6c81800a19d8765f664b38fa828ba61e4624.jpg")
                        .submit();
                emitter.onNext(futureTarget.get().getAbsolutePath());
            }
        }).subscribeOn(Schedulers.newThread())
          .observeOn(AndroidSchedulers.mainThread())
           .subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                result.setText("缓存图片路径---=="+s);
            }
        });

    }

    private void doI(){
         RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.place_holder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.mipmap.error)
                .circleCrop();
        with(this).load("http://e.hiphotos.baidu.com/image/h%3D300/sign=8d3a9ea62c7f9e2f6f351b082f31e962/500fd9f9d72a6059099ccd5a2334349b023bbae5.jpg")
                .apply(options).into(imageView);
    }

}
