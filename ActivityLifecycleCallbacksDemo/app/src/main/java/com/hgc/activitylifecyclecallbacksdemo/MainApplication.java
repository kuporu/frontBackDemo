package com.hgc.activitylifecyclecallbacksdemo;

import android.app.Application;
import android.util.Log;

import com.hgc.activitylifecyclecallbacksdemo.helper.AppFrontBackHelper;

/**
 * @Description TODO
 * @systemUser gchan2
 * @Author hanguangchuan
 * @Date 09-02-2022 周五 10:36
 */
public class MainApplication extends Application {

    public static final String TAG = "FrontBack";

    @Override
    public void onCreate() {
        super.onCreate();

        AppFrontBackHelper mAppFrontBackHelper = new AppFrontBackHelper();
        mAppFrontBackHelper.register(MainApplication.this, new AppFrontBackHelper.OnAppStatusListener() {
            @Override
            public void onFront() {
                Log.w(TAG, "应用程序切换到前台");
            }

            @Override
            public void onBack() {
                Log.w(TAG, "应用程序切换到后台");
            }
        });
    }
}
