package com.zhl.rx.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.zhl.rx.R;
import com.zhl.rx.views.FloatImageView;

public class FloatImageViewTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_image_view_test);
        final FrameLayout frameLayout = findViewById(R.id.fml);
        final FloatImageView floatImageView = findViewById(R.id.floatView);
        Button button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatImageView.setMaxWidthAndHeight(frameLayout.getWidth(),frameLayout.getHeight());
            }
        });
        floatImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FloatImageViewTest.this,"点击浮窗广告",Toast.LENGTH_SHORT).show();
            }
        });
    }


}
