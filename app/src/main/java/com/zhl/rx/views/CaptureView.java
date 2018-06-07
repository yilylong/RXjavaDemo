package com.zhl.rx.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.zhl.rx.utils.MeasureUtil;

/**
 * 描述：
 * Created by zhaohl on 2018-6-6.
 */
public class CaptureView extends View {
    private Bitmap captureBitmap;
    private Bitmap fgBitmap;
    private Canvas mCanvas;
    private Context ctx;
    private int captureWidth = 800,captureHeight=800;
    private Paint capturePaint;
    private Rect captureRect;
    private boolean touching;
    private boolean startCapture;
    private float downY,downX;
    private String captureSavePath,captureSaveName;

    public CaptureView(Context context) {
        this(context,null);
    }

    public CaptureView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public CaptureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ctx = context;
        capturePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        capturePaint.setColor(Color.TRANSPARENT);
        capturePaint.setMaskFilter(new BlurMaskFilter(15F, BlurMaskFilter.Blur.SOLID));
        capturePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        captureRect = new Rect(0,0,captureWidth,captureHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawImage(canvas);
        drawCover(canvas);
        drawCapture(captureRect);
        if(startCapture){
            Bitmap cropBitmap = Bitmap.createBitmap(captureBitmap,captureRect.left,(captureRect.top-(getHeight()-captureBitmap.getHeight())/2),captureWidth,captureHeight,null,true);
            MeasureUtil.saveBitmap2jpg(cropBitmap, captureSavePath,captureSaveName);
            startCapture = false;
        }
    }


    private void drawCover(Canvas canvas) {
        if(captureBitmap!=null){
            fgBitmap = Bitmap.createBitmap(getMeasuredWidth(),getMeasuredHeight(), Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas(fgBitmap);
            mCanvas.drawColor(Color.parseColor("#88000000"));
            canvas.drawBitmap(fgBitmap,0,0,null);
            if(!touching){
                captureRect.left = (getMeasuredWidth()-captureWidth)/2;
                captureRect.top = (getMeasuredHeight()-captureHeight)/2;
                captureRect.right = captureRect.left+captureWidth;
                captureRect.bottom = captureRect.top+captureHeight;
            }
        }
    }


    private void drawImage(Canvas canvas) {
        if(captureBitmap!=null){
            int viewWidth = getMeasuredWidth();
            int viewHeight = viewWidth*captureBitmap.getHeight()/captureBitmap.getWidth();
            captureBitmap = Bitmap.createScaledBitmap(captureBitmap,viewWidth,viewHeight,true);
            int top = (getMeasuredHeight()-viewHeight)/2;
            canvas.drawBitmap(captureBitmap,0,top,null);
        }
    }

    public void setCaptureImageBitmap(Bitmap bitmap){
        this.captureBitmap = bitmap;
        invalidate();
    }

    public void setCaptureImageRes(int resID){
        BitmapDrawable drawable = (BitmapDrawable) ctx.getResources().getDrawable(resID,null);
        captureBitmap = drawable.getBitmap();
        invalidate();
    }

    public void setCaptureSize(int width,int height){
        this.captureWidth = width;
        this.captureHeight = height;
        invalidate();
    }

    public void drawCapture(Rect rect){
        if(mCanvas!=null&&rect!=null){
            mCanvas.drawRect(rect,capturePaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                touching = true;
                downX = event.getX();
                downY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float delX = event.getX()-downX;
                float delY = event.getY()-downY;
                captureRect.left+=delX;
                if (captureRect.left<=0) {
                    captureRect.left = 0;
                }
                if(captureRect.left+captureWidth>=getMeasuredWidth()){
                    captureRect.left = getMeasuredWidth()-captureWidth;
                }
                captureRect.top+=delY;
                if(captureRect.top<=(getMeasuredHeight()-captureBitmap.getHeight())/2){
                    captureRect.top = (getMeasuredHeight()-captureBitmap.getHeight())/2;
                }
                if(captureRect.top+captureHeight>=(getMeasuredHeight()+captureBitmap.getHeight())/2){
                    captureRect.top = (getMeasuredHeight()+captureBitmap.getHeight())/2-captureHeight;
                }
                captureRect.right = captureRect.left+captureWidth;
                captureRect.bottom = captureRect.top+captureHeight;
                invalidate();
                downX = event.getX();
                downY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    public void startCapture(String path,String name){
        startCapture  = true;
        this.captureSavePath = path;
        this.captureSaveName = name;
        invalidate();
    }

    public void setCaptureStroke(boolean isStroke){
        if(isStroke){
            capturePaint.setStyle(Paint.Style.STROKE);
            capturePaint.setStrokeCap(Paint.Cap.ROUND);
            capturePaint.setStrokeJoin(Paint.Join.ROUND);
            capturePaint.setStrokeWidth(5);
        }else{
            capturePaint.setStyle(Paint.Style.FILL);
        }
        invalidate();
    }
}
