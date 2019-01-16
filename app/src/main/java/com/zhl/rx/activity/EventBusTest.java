package com.zhl.rx.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.zhl.rx.R;
import com.zhl.rx.bean.Baozi;
import com.zhl.rx.bean.EventMessage1;

import org.greenrobot.eventbus.EventBus;

/**
 * 描述：
 * Created by zhaohl on 2019-1-10.
 */
public class EventBusTest extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus);
        Button btn1 = findViewById(R.id.event_bus_1);
        Button btn2 = findViewById(R.id.event_bus_2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventMessage1("eventbus_msg1"));
                EventBusTest.this.finish();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                EventBus.getDefault().post(new EventMessage2("eventbus_msg1",9527));
                EventBus.getDefault().post(new Baozi("小笼包"));
                EventBusTest.this.finish();
            }
        });
    }
}
