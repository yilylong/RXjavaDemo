package com.zhl.rx.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.zhl.rx.R;
import com.zhl.rx.views.ShaderTextView;

public class ShaderTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shader_text);
        final ShaderTextView shaderTextView = findViewById(R.id.sharder_textview);
        final ProgressBar progressBar = findViewById(R.id.progress);
        Button button = findViewById(R.id.btn_start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shaderTextView.start();
                progressBar.setProgress(60);
            }
        });

    }
}
