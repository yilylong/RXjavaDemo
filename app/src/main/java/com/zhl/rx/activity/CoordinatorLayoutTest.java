package com.zhl.rx.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zhl.rx.R;
import com.zhl.rx.views.BehaviorWebView;

/**
 * 描述：
 * Created by zhaohl on 2018-7-3.
 */
public class CoordinatorLayoutTest extends AppCompatActivity {
    int donwy = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinatorlayout_test);
        BehaviorWebView webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webView.loadUrl("https://app.21jingji.com/epaper/html/market.html");
//        final RecyclerView recyclerView = findViewById(R.id.recyclerview);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(new RecyclerView.Adapter() {
//
//            @Override
//            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                View itemView = LayoutInflater.from(CoordinatorLayoutTest.this).inflate(R.layout.item_movie_list,parent,false);
//                return new MyViewHolder(itemView);
//            }
//
//            @Override
//            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//
//            }
//
//            @Override
//            public int getItemCount() {
//                return 30;
//            }
//
//             class MyViewHolder extends RecyclerView.ViewHolder{
//                View item;
//                public MyViewHolder(View itemView){
//                    super(itemView);
//                }
//             }
//        });
//        recyclerView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()){
//                    case MotionEvent.ACTION_DOWN:
//                        donwy = (int) event.getY();
//                        Log.d("mytag","downy=="+donwy);
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        Log.d("mytag","---getY=="+event.getY()+"----dwony=="+donwy);
//                        int dy = (int) (event.getY()-donwy);
//                        recyclerView.setTranslationY(recyclerView.getTranslationY()+dy);
//                        Log.d("mytag","dy=="+dy);
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        break;
//                }
//                return true;
//        }
//        });

//        final Button btn = findViewById(R.id.btn);
//        btn.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()){
//                    case MotionEvent.ACTION_DOWN:
//                        donwy = (int) event.getY();
//                        Log.d("mytag","downy=="+donwy);
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        Log.d("mytag","---getY=="+event.getY()+"----dwony=="+donwy);
//                        int dy = (int) (event.getY()-donwy);
//                        btn.setTranslationY(btn.getTranslationY()+dy);
//                        Log.d("mytag","dy=="+dy);
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        break;
//                }
//                return true;
//            }
//        });

    }
}
