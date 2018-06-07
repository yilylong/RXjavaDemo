package com.zhl.rx.utils;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View.MeasureSpec;
import android.view.WindowManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 测绘工具类
 *
 * @since 2014/11/19
 */
public final class MeasureUtil {
    public static final int RATION_WIDTH = 0;
    public static final int RATION_HEIGHT = 1;

    /**
     * 获取屏幕尺寸
     *
     * @param context
     * @return 屏幕尺寸像素值，下标为0的值为宽，下标为1的值为高
     */
    public static int[] getScreenSize(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);
        return new int[]{metrics.widthPixels, metrics.heightPixels};
    }

    /**
     * 自定义控件获取测量后的尺寸方法
     *
     * @param measureSpec 测量规格
     * @param ratio       宽高标识
     * @param mStr        自定义控件上需要测绘的文字
     * @param mBitmap     自定义控件上需要测绘的图片
     * @param paddings    自定义控件的padding值int[]{left,top,right,bottom}
     * @return 宽或高的测量值
     */
    public static int getMeasureSize(int measureSpec, int ratio, String mStr, Bitmap mBitmap, Paint mPaint, int[] paddings) {
        // 声明临时变量保存测量值
        int result = 0;
        /*
         * 获取测量mode和size
         */
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        /*
         * 判断mode的具体值
         */
        switch (mode) {
            case MeasureSpec.EXACTLY:// EXACTLY时直接赋值
                result = size;
                break;
            default:// 默认情况下将UNSPECIFIED和AT_MOST一并处理
                if (ratio == RATION_WIDTH) {
                    if (mStr != null && mBitmap != null) {
                        float textWidth = mPaint.measureText(mStr);
                        result = ((int) (textWidth >= mBitmap.getWidth() ? textWidth : mBitmap.getWidth()));
                    } else if (mBitmap != null) {
                        result = mBitmap.getWidth();
                    } else if (mStr != null) {
                        result = (int) mPaint.measureText(mStr);
                    }
                    if (paddings != null) {
                        result += (paddings[0] + paddings[2]);
                    }
                } else if (ratio == RATION_HEIGHT) {
                    if (mStr != null && mBitmap != null) {
                        result = ((int) ((mPaint.descent() - mPaint.ascent()) * 2 + mBitmap.getHeight()));
                    } else if (mBitmap != null) {
                        result = mBitmap.getHeight();
                    } else if (mStr != null) {
                        result = (int) ((mPaint.descent() - mPaint.ascent()) * 2);
                    }
                    if (paddings != null) {
                        result += (paddings[1] + paddings[3]);
                    }
                }

                /*
                 * AT_MOST时判断size和result的大小取小值
                 */
                if (mode == MeasureSpec.AT_MOST) {
                    result = Math.min(result, size);
                }
                break;
        }
        return result;
    }

    public static Bitmap changeColor(Bitmap src, int keyColor, int replColor, int tolerance) {
        Bitmap copy = src.copy(Bitmap.Config.ARGB_8888, true);
        int width = copy.getWidth();
        int height = copy.getHeight();
        int[] pixels = new int[width * height];
        src.getPixels(pixels, 0, width, 0, 0, width, height);
        int sR = Color.red(keyColor);
        int sG = Color.green(keyColor);
        int sB = Color.blue(keyColor);
        int tR = Color.red(replColor);
        int tG = Color.green(replColor);
        int tB = Color.blue(replColor);
        float[] hsv = new float[3];
        Color.RGBToHSV(tR, tG, tB, hsv);
        float targetHue = hsv[0];
        float targetSat = hsv[1];
        float targetVal = hsv[2];

        for (int i = 0; i < pixels.length; ++i) {
            int pixel = pixels[i];

            if (pixel == keyColor) {
                pixels[i] = replColor;
            } else {
                int pR = Color.red(pixel);
                int pG = Color.green(pixel);
                int pB = Color.blue(pixel);

                int deltaR = Math.abs(pR - sR);
                int deltaG = Math.abs(pG - sG);
                int deltaB = Math.abs(pB - sB);

                if (deltaR <= tolerance && deltaG <= tolerance && deltaB <= tolerance) {
                    Color.RGBToHSV(pR, pG, pB, hsv);
                    hsv[0] = targetHue;
                    hsv[1] = targetSat;
                    hsv[2] *= targetVal;

                    int mixTrgColor = Color.HSVToColor(Color.alpha(pixel), hsv);
                    pixels[i] = mixTrgColor;
                }
            }
        }

        copy.setPixels(pixels, 0, width, 0, 0, width, height);

        return copy;
    }

    public static int dp2px(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static int sp2px(Context context, int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }

    public static int px2dp(Context context, int px) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, context.getResources().getDisplayMetrics());
    }

    /**
     * 测量文字的高度
     *
     * @param textsize px
     * @return
     */
    public static int measureTextHeight(int textsize) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(textsize);
        Paint.FontMetrics metrics = paint.getFontMetrics();
        return (int) Math.ceil(metrics.descent - metrics.ascent);
    }

    /**
     * 测量文字的宽度
     *
     * @param text
     * @param textsize px
     * @return
     */
    public static int measureTextWidth(String text, int textsize) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(textsize);
        return (int) paint.measureText(text);
    }

    public static Bitmap createBitmapSafely(int width, int height, Bitmap.Config config, int retryCount) {
        try {
            return Bitmap.createBitmap(width, height, config);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            if (retryCount > 0) {
                System.gc();
                return createBitmapSafely(width, height, config, retryCount - 1);
            }
            return null;
        }
    }

    public static int getScreenDPI(Context context) {
        if (context == null) {
            return 0;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.densityDpi;
    }

    public static Bitmap compressImage(int targetW, int targetH, String srcPath) {
        if (srcPath == null) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(srcPath, options);
        int srcW = options.outWidth;
        int srcH = options.outHeight;
        int scaleFactor = Math.min(srcW / targetW, srcH / targetH);

        options.inJustDecodeBounds = false;
        options.inSampleSize = scaleFactor;
        options.inPurgeable = true;
        Bitmap destBitmap = BitmapFactory.decodeFile(srcPath, options);
        return destBitmap;
    }

    /**
     *  将Bitmap保存为本地图片
     * @param mBitmap
     * @param savePath 保存的路径
     * @param saveFileName 保存的文件名
     * @return
     */
    public static boolean saveBitmap2jpg(Bitmap mBitmap, String savePath, String saveFileName) {
        // TODO Auto-generated method stub
        if (null == mBitmap || TextUtils.isEmpty(savePath) || TextUtils.isEmpty(saveFileName))
            return false;
        boolean result = false;
        File dir = new File(savePath);
        File f = new File(savePath + saveFileName);
        FileOutputStream fOut = null;
        try {
            if (!dir.exists()) {
                dir.mkdirs();
            }
            f.createNewFile();
            fOut = new FileOutputStream(f);
            result = mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            try {
                fOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
