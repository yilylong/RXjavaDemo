package com.zhl.rx.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhl.rx.R;
import com.zhl.rx.views.FloatImageView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;

public class FloatImageViewTest extends AppCompatActivity {
    private RecyclerView mRecyclerview;
    private ArrayList<String> datas = new ArrayList<>();
    private CommonAdapter adapter;
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
        for(int i=0;i<5;i++){
            datas.add("撒娇佛国家艾薇A水调歌头--"+i);
        }
        mRecyclerview = findViewById(R.id.recyclerview);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(adapter = new CommonAdapter(this,R.layout.layout_item_recyclerview,datas) {
            @Override
            protected void convert(ViewHolder holder, Object o, int position) {
                holder.setText(R.id.item_float_detail_tx,datas.get(position));
            }
        });
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(FloatImageViewTest.this,FloatDetailActivity.class);
                intent.putExtra("desc",datas.get(position));
                ActivityCompat.startActivity(FloatImageViewTest.this,intent,
                        ActivityOptionsCompat.makeSceneTransitionAnimation(FloatImageViewTest.this,
                                Pair.create(((View)userhead),"userhead"),
                                Pair.create(((View)username),"desc"),
                                Pair.create((((ViewHolder)holder).getView(R.id.item_float_detail_img)),"item_head"),
                                Pair.create((((ViewHolder)holder).getView(R.id.item_float_detail_tx)),"item_desc")
                        ).toBundle());
            }
            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }


}
