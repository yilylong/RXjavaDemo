package com.zhl.rx.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.text.emoji.EmojiCompat;
import android.support.text.emoji.bundled.BundledEmojiCompatConfig;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.OnCompositionLoadedListener;
import com.zhl.rx.R;
import com.zhl.rx.http.LottieHttpService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class StartActivity extends AppCompatActivity {
    private LottieAnimationView animationView;
    private String lottie_base_url = "https://www.lottiefiles.com/download/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        animationView = findViewById(R.id.animation_view);

        Button doA = findViewById(R.id.doA);
        doA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doA();
            }
        });
        Button doB = findViewById(R.id.doB);
        doB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doB();
            }
        });
        Button doC = findViewById(R.id.doC);
        doC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doC();
            }
        });
        EmojiCompat.init(new BundledEmojiCompatConfig(this));
    }


    private void doA() {
        animationView.setAnimation("rey_updated!.json");
        animationView.loop(true);
        animationView.playAnimation();
    }

    private void doB() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(lottie_base_url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();
        LottieHttpService lottieService = retrofit.create(LottieHttpService.class);
        lottieService.getLottieJsonByUrl("433")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody response)  {
                        if (response != null) {
                            try {
                                LottieComposition.Factory.fromJson(getResources(), new JSONObject(response.string()), new OnCompositionLoadedListener() {
                                    @Override
                                    public void onCompositionLoaded(@Nullable LottieComposition composition) {
                                        animationView.setComposition(composition);
                                        animationView.loop(false);
                                        animationView.playAnimation();
                                    }
                                });
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(StartActivity.this, "获取Lottie Json失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

//        Call<ResponseBody> call = lottieService.getLottieJsonByUrlCall("1127");
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//
//                if (response.body() != null) {
//                    try {
//                        LottieComposition.Factory.fromJson(getResources(), new JSONObject(response.body().string()), new OnCompositionLoadedListener() {
//                            @Override
//                            public void onCompositionLoaded(@Nullable LottieComposition composition) {
//                                animationView.setComposition(composition);
//                                animationView.playAnimation();
//                            }
//                        });
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                } else {
//                    Toast.makeText(StartActivity.this, "获取Lottie Json失败", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Toast.makeText(StartActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    private void doC() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}
