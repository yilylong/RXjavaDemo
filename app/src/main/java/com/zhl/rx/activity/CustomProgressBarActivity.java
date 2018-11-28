package com.zhl.rx.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.zhl.rx.R;
import com.zhl.rx.views.ImageProgressBar;

public class CustomProgressBarActivity extends AppCompatActivity {
    private static final int UPDATE_PROGRESS=0;
    boolean stop;
    int progress = 0;
    ImageProgressBar progressBar;
    Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_PROGRESS:
                    progressBar.setProgress(msg.arg1);
                    break;
                default:
                    break;
            }
        };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_progress_bar);
        progressBar = findViewById(R.id.progressBar);
//        progressBar.setProgress(20);
//        progressBar.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                progressBar.setProgress(30);
//            }
//        },1500);
//
//        progressBar.setProgress(80);
        new Thread(new Runnable() {
            @Override
            public void run() {
                int progress = 0;
                while(!stop){
                    if(progress>=100){
                        break;
                    }
                    Message msg = handler.obtainMessage();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progress+=1;
                    msg.what= UPDATE_PROGRESS;
                    msg.arg1 = progress;
                    msg.sendToTarget();
                }
                progress = 0;
            }
        }).start();
    }
}
