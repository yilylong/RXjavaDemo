package com.zhl.rx.activity;

import android.app.Activity;
import android.os.Bundle;

import com.zhl.rx.R;
import com.zhl.rx.views.TagImageView;

public class CustomViewTestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_test);
        TagImageView imageView = findViewById(R.id.tag_img);
//        imageView.setTagSize(50);
//        imageView.setTagLocation(TagImageView.Location.ON_TOP_lEFT);
//        imageView.showTag(false);
//        imageView.setTagBackgroud(Color.parseColor("#FF3396FF"));
//        imageView.setTagTextColor(Color.parseColor("#FFFF336D"));
    }
}
