package jamin.github.com.switchpager.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * 屏幕设置相关类
 * Created by jaminchanks on 2016/9/25.
 */
public class ScreenUtils {
    private static int mScreenWidth = 0;
    private static int mScreenHeight = 0;

    private ScreenUtils() {
        /** cannot be instantiated **/
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public static void setBackgroundAlpha(Activity activity, float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        activity.getWindow().setAttributes(lp);
    }

    /**
     * 获取屏幕宽度
     */
    public static int getScreenWidth(Context context) {
        if (mScreenWidth == 0) {
            DisplayMetrics metrics = context.getResources().getDisplayMetrics();
            mScreenWidth =  metrics.widthPixels;
        }
        return mScreenWidth;
    }

    /**
     * 获取屏幕高度
     */
    public static int getScreenHeight(Context context) {
        if (mScreenHeight == 0) {
            DisplayMetrics metrics = context.getResources().getDisplayMetrics();
            mScreenHeight = metrics.heightPixels;
        }
        return mScreenHeight;
    }
}
