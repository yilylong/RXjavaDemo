package com.zhl.rx.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
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
        final ImageView userhead = findViewById(R.id.img);
        final TextView username = findViewById(R.id.tv);
        Button button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatImageView.setMaxWidthAndHeight(frameLayout.getWidth(), frameLayout.getHeight());
            }
        });
        floatImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FloatImageViewTest.this, "点击浮窗广告", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(FloatImageViewTest.this,FloatDetailActivity.class);
                ActivityCompat.startActivity(FloatImageViewTest.this,intent,
                        ActivityOptionsCompat.makeSceneTransitionAnimation(FloatImageViewTest.this, Pair.create(((View)userhead),"userhead"),Pair.create(((View)username),"desc")).toBundle());
            }
        });
    }


}
