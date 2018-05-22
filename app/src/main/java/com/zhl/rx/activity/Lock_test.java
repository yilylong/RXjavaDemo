package com.zhl.rx.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zhl.rx.R;
import com.zhl.rx.views.LockScreenViewGroup;

public class Lock_test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_test);
        LockScreenViewGroup lockScreenViewGroup = (LockScreenViewGroup) findViewById(R.id.lockScreenViewGroup);
        int[] answers = {1,2,7,8,9,3};
        lockScreenViewGroup.setAnswers(answers);
    }
}
