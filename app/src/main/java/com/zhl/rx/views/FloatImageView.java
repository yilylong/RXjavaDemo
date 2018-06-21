package com.zhl.rx.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 描述：
 * Created by zhaohl on 2018-6-15.
 */
public class FloatImageView extends android.support.v7.widget.AppCompatImageView {
    private int maxWidth = 1080,maxHeight=1800;
    private float downX,downY;
    private float orignalX,orignalY;

    public FloatImageView(Context context) {
        this(context,null);
    }

    public FloatImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public FloatImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                orignalX = getX();
                orignalY = getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float delX = event.getX()-downX;
                float delY = event.getY()-downY;
                float translateX = getX()+delX;
                float translateY = getY()+delY;
                if(translateX<=0){
                    translateX = 0;
                }
                if(translateX+getWidth()>=maxWidth){
                    translateX = maxWidth-getWidth();
                }
                if(translateY<=0){
                    translateY = 0;
                }
                if(translateY+getHeight()>=maxHeight){
                    translateY = maxHeight-getHeight();
                }
                setTranslationX(translateX);
                setTranslationY(translateY);
                break;
            case MotionEvent.ACTION_UP:
                float dx = getX()-orignalX;
                float dy = getY()-orignalY;
                // 触发点击事件
                if(Math.abs(dx)<=5&&Math.abs(dy)<=5){
                    callOnClick();
                }
                break;
        }
        return true;
    }

    public void setMaxWidthAndHeight(int maxWidth,int maxHeight){
        if(maxWidth>=0){
            this.maxWidth = maxWidth;
        }
        if(maxHeight>=0){
            this.maxHeight = maxHeight;
        }
    }

}
