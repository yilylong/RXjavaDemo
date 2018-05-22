package com.zhl.rx.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;


/**
 * 描述：Android6.0授权工具类
 * Created by zhaohl on 2016-3-30.
 */
public class PermissionUtil {
    /**
     * 是否需要授权
     * @return
     */
    public static boolean needGrantPermission(Activity activity, String premission){
        return activity!=null&& Build.VERSION.SDK_INT >= Build.VERSION_CODES.M&& activity.checkSelfPermission(premission) != PackageManager.PERMISSION_GRANTED;
    }


    @TargetApi(value = 23)
    public static void requestPermission(final Activity activity, final String permission){
        if(needGrantPermission(activity,permission)){
            grantedPermission(activity,permission);
        }
    }

    private static void grantedPermission(Activity activity, String premission) {
        int requestCode = 1001;
        ActivityCompat.requestPermissions(activity,
                new String[]{premission},
                requestCode);
    }

    /**
     * 检查某项权限是否已经授权
     * @param premission
     * @return
     */
    public static boolean hasGrantedPermission(Context context, String premission){
        if(context==null){
            return false;
        }
        return ContextCompat.checkSelfPermission(context,premission)== PackageManager.PERMISSION_GRANTED;
    }

}
