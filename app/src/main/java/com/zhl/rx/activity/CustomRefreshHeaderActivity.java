package com.zhl.rx.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhl.rx.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class CustomRefreshHeaderActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<String> datas = new ArrayList<>();
    private SmartRefreshLayout refreshView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_refresh_header);
        refreshView =  findViewById(R.id.refresh_layout);
        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        for (int i = 0; i < 30; i++) {
            datas.add("custom_refresh_header" + i);
        }
        mRecyclerView.setAdapter(new CommonAdapter(this, R.layout.item_cardview, datas) {
            @Override
            protected void convert(ViewHolder holder, Object o, int position) {
                holder.setText(R.id.item_tx, datas.get(position));
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            }
        });
        refreshView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshView.finishRefresh();
                    }
                },5000);
            }
        });
    }
}
