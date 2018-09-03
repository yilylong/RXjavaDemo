package com.zhl.rx.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.zhl.rx.R;

public class EmojiTestActivity extends AppCompatActivity {
    private EditText editText;
    private TextView msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emoji_test);
        msg = findViewById(R.id.msg);
        editText = findViewById(R.id.edit);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                Log.d("mytag","---textchanged=="+s);
            }

            @Override
            public void afterTextChanged(Editable s) {
//                Log.d("mytag","---s=="+ EmojiCompat.get().process(s.toString()));
                msg.setText(s.toString());
            }
        });
    }
}
