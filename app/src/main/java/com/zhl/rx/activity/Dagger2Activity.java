package com.zhl.rx.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.zhl.rx.R;
import com.zhl.rx.bean.ShangjiaAModule;
import com.zhl.rx.bean.ZhaiNan;
import com.zhl.rx.annotation.Computer;
import com.zhl.rx.entry.DaggerActivityComponent;
import com.zhl.rx.entry.DaggerPlatform;
import com.zhl.rx.annotation.Phone;

import javax.inject.Inject;

public class Dagger2Activity extends AppCompatActivity {
    public ZhaiNan zhaiNan;
    private TextView textView;
    @Inject
    @Phone
    String testValue;
    @Inject
    @Computer
    String testValue2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2);
        textView = findViewById(R.id.msg);
//        zhaiNan = DaggerActivityProvider.builder().build().getZhaiNan();
        zhaiNan = DaggerPlatform.builder().shangjiaAModule(new ShangjiaAModule("黄小厨")).build().waimai();
        DaggerActivityComponent.builder().build().zhuru(this);

        textView.setText(zhaiNan.eat()+"---testValue=="+testValue+"-"+testValue2);
    }
}
