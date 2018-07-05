package com.zhl.rx.views;

import android.content.Context;
import android.support.v4.view.NestedScrollingChild2;
import android.support.v4.view.NestedScrollingChildHelper;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * 描述：
 * Created by zhaohl on 2018-7-5.
 */
public class BehaviorWebView extends WebView implements NestedScrollingChild2 {
    private NestedScrollingChildHelper mNestedScrollingChildHelper;
    public BehaviorWebView(Context context) {
        this(context,null);
    }

    public BehaviorWebView(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public BehaviorWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,-1);
    }

    public BehaviorWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mNestedScrollingChildHelper = new NestedScrollingChildHelper(this);
        mNestedScrollingChildHelper.setNestedScrollingEnabled(true);
    }

    @Override
    public boolean startNestedScroll(int axes, int type) {
        return mNestedScrollingChildHelper.startNestedScroll(axes,type);
    }

    @Override
    public void stopNestedScroll(int type) {
        mNestedScrollingChildHelper.stopNestedScroll(type);
    }

    @Override
    public boolean hasNestedScrollingParent(int type) {
        return mNestedScrollingChildHelper.hasNestedScrollingParent(type);
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed,  int[] offsetInWindow, int type) {
        return mNestedScrollingChildHelper.dispatchNestedScroll(dxConsumed,dyConsumed,dxUnconsumed,dyUnconsumed,offsetInWindow,type);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy,  int[] consumed,  int[] offsetInWindow, int type) {
        return mNestedScrollingChildHelper.dispatchNestedPreScroll(dx,dy,consumed,offsetInWindow,type);
    }
}
