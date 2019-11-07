package com.jinribeidou.material_design;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jason on 2019/11/6.
 */
public class MainApp extends Application {
    private static MainApp mainApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mainApp = this;

    }

    /**
     * 获取MainApp单例
     *
     * @return
     */
    public static MainApp getInstance() {
        return mainApp;
    }

    /**
     * 用list来存储activity，以便退出app的时候可以退出全部的activity
     */
    private List<Activity> mList = new LinkedList<Activity>();

    /**
     * 添加activity
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        mList.add(activity);
    }

    /**
     * 获取栈顶activity
     *
     * @return
     */
    public Activity getTopActivity() {
        return mList.get(mList.size() - 1);
    }

    /**
     * 移除activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        mList.remove(activity);
    }

    /**
     * 结束某个activity
     *
     * @param activityName
     */
    public void finishActivity(String activityName) {
        try {
            for (Activity mActivity : mList) {
                if (mActivity.getClass().toString().equals(activityName)) {
                    mActivity.finish();
                }
            }
        } catch (Exception e) {
        }
    }

    /**
     * 清空activity列表以退出应用
     */
    public void exit() {
        try {
            for (Activity activity : mList) {
                if (activity != null) {
                    activity.finish();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }

    /**
     * 修改状态栏颜色，支持4.4以上版本
     *
     * @param activity
     * @param colorId
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor(Activity activity, int colorId) {
        Window window = activity.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(activity.getResources().getColor(colorId));
    }
}
