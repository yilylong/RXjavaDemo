package com.zhl.rx.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zhl.rx.R;

import java.math.BigDecimal;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述：
 * Created by zhaohl on 2019-6-25.
 */
public class AliJavaTest extends Activity implements View.OnClickListener{
    private final static Lock lock = new ReentrantLock();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alijava_test);
        Button btn1 = findViewById(R.id.test_1);
        Button btn2 = findViewById(R.id.test_2);
        Button btn3 = findViewById(R.id.test_3);
        Button btn4 = findViewById(R.id.test_4);
        Button btn5 = findViewById(R.id.test_5);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.test_1:
                test1();
                break;
            case R.id.test_2:
                test2();
                break;
            case R.id.test_3:
                test3();
                break;
            case R.id.test_4:
                test4();
                break;
            case R.id.test_5:
                test5();
                break;
        }
    }

    private void test5() {
        try {
            lock.tryLock();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void test4() {
        BigDecimal a = new BigDecimal(0.1);
        Log.d("mytag","a="+a);
        BigDecimal b = new BigDecimal("0.1");
        Log.d("mytag","b="+b);
    }

    private void test3() {
        String param = null;
        switch (param) {
            case "null":
                Log.d("mytag","null");
                break;
            default:
                Log.d("mytag","default");
        }
    }

    private void test2() {

        Float a = Float.valueOf(1.0f - 0.9f);
        Float b = Float.valueOf(0.9f - 0.8f);
        if (a.equals(b)) {
            Log.d("mytag","true");
        } else {
            Log.d("mytag","false");
        }
    }

    private void test1() {
        float a = 1.0f - 0.9f;
        float b = 0.9f - 0.8f;
        if (a == b) {
            Log.d("mytag","true");
        } else {
            Log.d("mytag","false");
        }
    }
}
