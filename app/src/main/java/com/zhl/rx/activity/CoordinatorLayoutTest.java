package com.zhl.rx.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.zhl.rx.R;

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
        WebView webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setDisplayZoomControls(false);//不显示放大缩小按钮
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        // 启用localStorage 和 essionStorage
        webView.getSettings().setDomStorageEnabled(true);
        // 开启应用程序缓存
        webView.getSettings().setAppCacheEnabled(true);
        String appCacheDir = this.getApplicationContext().getDir("cache", Context.MODE_PRIVATE).getPath();
        webView.getSettings().setAppCachePath(appCacheDir);

        //建议缓存策略为，判断是否有网络，有的话，使用LOAD_DEFAULT，无网络时，使用LOAD_CACHE_ELSE_NETWORK。
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.getSettings().setAppCacheMaxSize(1024 * 1024 * 10);// 设置缓冲大小，我设的是10M
        webView.getSettings().setAllowFileAccess(true);
        //webview中滚动拖动到顶部或者底部时的阴影
        webView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY); //取消滚动条白边效果

        // 启用Webdatabase数据库
        webView.getSettings().setDatabaseEnabled(true);
        String databaseDir = this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
        webView.getSettings().setDatabasePath(databaseDir);// 设置数据库路径
        // 启用地理定位
        webView.getSettings().setGeolocationEnabled(true);
        // 设置定位的数据库路径
        webView.getSettings().setGeolocationDatabasePath(databaseDir);

        // 开启插件（对flash的支持）
//		webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);













        webView.loadUrl("https://m.21jingji.com/mall/000001.html");
//        webView.loadUrl("https://app.21jingji.com/epaper/html/youPin21.html");
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
