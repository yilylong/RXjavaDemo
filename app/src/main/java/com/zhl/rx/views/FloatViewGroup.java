package com.zhl.rx.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 描述：
 * Created by zhaohl on 2018-1-17.
 */

public class FloatViewGroup extends ViewGroup {

    public FloatViewGroup(Context context) {
        this(context,null);
    }

    public FloatViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public FloatViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int usedWidth = 0;
        int usedHeight = 0;
        for(int i=0;i<getChildCount();i++){
            View childView = getChildAt(i);
            if(childView.getVisibility()!=View.GONE){
                LayoutParams params = (LayoutParams) childView.getLayoutParams();
                int childWidthSpec = MeasureSpec.AT_MOST;
                int childHeightSpec = MeasureSpec.AT_MOST;
                switch (params.width){
                    case LayoutParams.MATCH_PARENT:
                        if(widthMode==MeasureSpec.EXACTLY||widthMode==MeasureSpec.AT_MOST){
                            childWidthSpec = MeasureSpec.makeMeasureSpec(widthSize,MeasureSpec.EXACTLY);
                        }else{
                            childWidthSpec = MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
                        }
                        break;
                    case LayoutParams.WRAP_CONTENT:
                        if(widthMode==MeasureSpec.EXACTLY||widthMode==MeasureSpec.AT_MOST){
                            childWidthSpec = MeasureSpec.makeMeasureSpec(widthSize,MeasureSpec.AT_MOST);
                        }else{
                            childWidthSpec = MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
                        }
                        break;
                    default:
                        childWidthSpec = MeasureSpec.makeMeasureSpec(params.width,MeasureSpec.EXACTLY);
                        break;
                }
                usedWidth+=MeasureSpec.getSize(childWidthSpec)+params.leftMargin+params.rightMargin;
                switch (params.height){
                    case LayoutParams.MATCH_PARENT:
                        if(heightMode==MeasureSpec.EXACTLY||heightMode==MeasureSpec.AT_MOST){
                            childHeightSpec = MeasureSpec.makeMeasureSpec(heightSize,MeasureSpec.EXACTLY);
                        }else{
                            childHeightSpec = MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
                        }
                        break;
                    case LayoutParams.WRAP_CONTENT:
                        if(heightMode==MeasureSpec.EXACTLY||heightMode==MeasureSpec.AT_MOST){
                            childHeightSpec = MeasureSpec.makeMeasureSpec(heightSize,MeasureSpec.AT_MOST);
                        }else{
                            childHeightSpec = MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
                        }
                        break;
                    default:
                        childHeightSpec = MeasureSpec.makeMeasureSpec(params.height,MeasureSpec.EXACTLY);
                        break;
                }
                usedHeight+=MeasureSpec.getSize(childHeightSpec)+params.bottomMargin+params.topMargin;
                measureChild(childView,childWidthSpec,childHeightSpec);
            }
        }
        if(widthMode==MeasureSpec.UNSPECIFIED){
            widthSize = usedWidth;
        }
        if(heightMode==MeasureSpec.UNSPECIFIED){
            heightSize = usedHeight;
        }
        setMeasuredDimension(widthSize,heightSize);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = getMeasuredWidth();
        int left=0;
        int top = 0;
        int bottom = 0;
        for(int i=0;i<getChildCount();i++){
            View childView = getChildAt(i);
            int childRight = 0;
            int childBottom = 0;
            int childLeft = 0;
            int childTop = 0;
            FloatViewGroup.LayoutParams params = (LayoutParams) childView.getLayoutParams();
            if(left+childView.getMeasuredWidth()>width){
                left = 0;
                top=bottom;
            }
            childLeft = left+params.leftMargin;
            childTop = top+params.topMargin;
            childRight = childLeft+childView.getMeasuredWidth();
            childBottom = childTop+childView.getMeasuredHeight();
            childView.layout(childLeft,childTop,childRight,childBottom);
            left+=childRight+params.rightMargin;
            if(childBottom>bottom){
                bottom = childBottom + params.bottomMargin;
            }
        }
    }

    public static class LayoutParams extends MarginLayoutParams{

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }

    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(),attrs);
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }
}
