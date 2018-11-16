package com.zhl.rx.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.zhl.rx.R;

/**
 * 描述：
 * Created by zhaohl on 2018-10-23.
 */
public class FloatDetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_detail);
        TextView ff = findViewById(R.id.textView4);
        ff.setText(getIntent().getStringExtra("desc"));
//        getWindow().setEnterTransition(new Slide(Gravity.RIGHT));
    }
}
