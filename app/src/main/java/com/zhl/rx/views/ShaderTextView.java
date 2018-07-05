package com.zhl.rx.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.zhl.rx.R;

/**
 * 描述：
 * Created by zhaohl on 2018-6-22.
 */
public class ShaderTextView extends View {
    private Paint paint1,sharderPaint1,paint2,sharderPaint2;
    private String text="歌词渲染测试ing...";
    private float textWidth = 0;
    private Rect sharderRect = new Rect(0,0,0,0);
    private boolean anmiatedfinish;
    public ShaderTextView(Context context) {
        this(context,null);
    }

    public ShaderTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public ShaderTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        sharderPaint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        sharderPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.img_3);
        paint1.setTextSize(100);
//        paint.setShader(new BitmapShader(bitmap, Shader.TileMode.MIRROR,Shader.TileMode.MIRROR));
//        paint.setShader(new LinearGradient(200,500,250,500, 0x994faa00,0x55edbb60, Shader.TileMode.MIRROR));
//        paint.setShader(new SweepGradient(500,500,0x234faa00,0x562bbd00));
//        paint.setColor(Color.RED);
        textWidth = paint1.measureText(text);
        paint1.setColor(Color.parseColor("#ff39BF78"));
        sharderPaint1.setShader(new LinearGradient(200,600,240,600, 0x33ffffff,0xff39BF78, Shader.TileMode.CLAMP));
        paint2.setColor(Color.parseColor("#FFE9534C"));
        sharderPaint2.setShader(new LinearGradient(200,800,240,800, 0x33ffffff,0xFFE9534C, Shader.TileMode.CLAMP));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(text,200,500,paint1);
        canvas.drawRect(new Rect(200,600,800,700),paint1);
        canvas.drawRect(new Rect(200,800,800,900),paint2);
        if(!anmiatedfinish){
            canvas.drawRect(new Rect(200,600,800,700),sharderPaint1);
            canvas.drawRect(new Rect(200,800,800,900),sharderPaint2);
        }
    }

    public void run(){
        ValueAnimator animator = ValueAnimator.ofFloat(0, textWidth);
        animator.setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float d = (float) animation.getAnimatedValue();
                Log.d("mytag","-------d=="+d);
                paint1.setShader(new LinearGradient(200+d,500,220+d,500, 0x994faa44,0x55edbb60, Shader.TileMode.CLAMP));
                invalidate();
            }
        });
        animator.start();
    }
    public void start(){
        anmiatedfinish = false;
        ValueAnimator animator = ValueAnimator.ofFloat(0, 600);
        animator.setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float d = (float) animation.getAnimatedValue();

//                sharderRect.left = (int) (d+220);
//                sharderRect.right = (int) (d+240);
//                sharderRect.top = 600;
//                sharderRect.bottom = 700;
                sharderPaint1.setShader(new LinearGradient(200+d,600,240+d,600, 0x33ffffff,0xff39BF78, Shader.TileMode.CLAMP));
                sharderPaint2.setShader(new LinearGradient(200+d,800,240+d,800, 0x33ffffff,0xFFE9534C, Shader.TileMode.CLAMP));
                Log.d("mytag","-------LEFT=="+sharderRect.left+"-----RIGHT=="+sharderRect.right);
                invalidate();
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
//                sharderRect.left = 0;
//                sharderRect.right = 0;
//                sharderRect.top = 600;
//                sharderRect.bottom = 700;
                anmiatedfinish = true;
                invalidate();
            }
        });
        animator.start();
    }
}
