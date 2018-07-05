package com.zhl.rx.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhl.rx.R;

/**
 * 描述：
 * Created by zhaohl on 2018-7-3.
 */
public class EasyBehavior extends CoordinatorLayout.Behavior<View> {

    private boolean hidden;
    private ImageView back,share;
    private TextView title;
    private View childView;
    private View dependencyView;
    CoordinatorLayout.LayoutParams params;

    public EasyBehavior(Context context, AttributeSet attrs){
        super(context,attrs);
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        back = child.findViewById(R.id.backBt);
        share = child.findViewById(R.id.shareBt);
        title = child.findViewById(R.id.title);
        childView = child;
        dependencyView = dependency;
        return super.layoutDependsOn(parent,child,dependency);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        return super.onDependentViewChanged(parent,child,dependency);
    }


    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        Log.d("mytag","--dyConsumed=="+dyConsumed+"-----dyUnconsumed--=="+dyUnconsumed+"--getTranslationY()=="+child.getTranslationY()+"--child.getHeight()--"+child.getHeight());
        float translationY = child.getTranslationY() - dyConsumed;
        if(translationY>0){
            translationY = 0;
            hidden = false;
        }
        if(translationY<-child.getHeight()){
            translationY = -child.getHeight();
            hidden = true;
        }
        child.setTranslationY(translationY);
        startAlpha(translationY);
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
    }

    @Override
    public void onStopNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int type) {
        super.onStopNestedScroll(coordinatorLayout, child, target, type);
    }

    @Override
    public void onNestedScrollAccepted(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, axes, type);
    }

    @Override
    public boolean onNestedPreFling(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, float velocityX, float velocityY) {
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY);
    }


    private void startAlpha(float translationY){
        float fract = 1-(float)(Math.abs(translationY)/childView.getHeight());
        if(back!=null){
            back.setAlpha(fract);
            back.setScaleX(fract);
            back.setScaleY(fract);
        }
        if(share!=null){
            share.setAlpha(fract);
            share.setScaleX(fract);
            share.setScaleY(fract);
        }
        if(title!=null){
            title.setAlpha(fract);
            title.setScaleX(fract);
            title.setScaleY(fract);
        }
    }

}
