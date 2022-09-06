package com.amap.androidobackgroundlocation;

import android.content.Context;
import android.content.pm.ApplicationInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author lake
 * create by 2021/8/6 5:48 下午
 */
public class Constants {

    public static final long DEFAULT_TIME = 5; // 分钟
   
    // 初始化一次即可
    private static volatile Boolean isDebugVersion = null;

    public static boolean isDebug(Context context) {
        if (isDebugVersion != null) {
            return isDebugVersion;
        }

        try {
            ApplicationInfo info = context.getApplicationInfo();
            //true 是debug版本
            boolean debug = (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
            if (isDebugVersion == null) {
                isDebugVersion = debug;
            }
            return debug;
        } catch (Exception ex) {
            return false;
        }
    }

    public static String getDateStrNow() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        return sdf.format(new Date());
    }

    public static String getFileNameForNow() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HHmmss", Locale.US);
        return sdf.format(new Date());
    }

}
