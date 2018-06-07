package com.zhl.rx.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.zhl.rx.R;
import com.zhl.rx.views.CaptureView;

public class CaptureViewActivity extends AppCompatActivity {
    private CaptureView captureView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_view);
        captureView = findViewById(R.id.capture_view);
        captureView.setCaptureImageRes(R.mipmap.img_2);
        captureView.setCaptureSize(800,450);
        captureView.setCaptureStroke(false);
        Button btn = findViewById(R.id.btn_capture);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureView.startCapture(Environment.getExternalStorageDirectory()+"/rxjavademo/capture/","capture.jpg");
            }
        });
    }
}
