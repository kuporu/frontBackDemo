package com.hgc.activitylifecyclecallbacksdemo.helper;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Description 判断前后台工具类，仅在 Application 中使用
 * @systemUser gchan2
 * @Author hanguangchuan
 * @Date 09-02-2022 周五 10:21
 */
public class AppFrontBackHelper {

    private OnAppStatusListener mOnAppStatusListener;

    public AppFrontBackHelper () {

    }

    public void register (Application application, OnAppStatusListener listener) {
        mOnAppStatusListener = listener;
        application.registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks);
    }

    private Application.ActivityLifecycleCallbacks mActivityLifecycleCallbacks = new Application.ActivityLifecycleCallbacks() {

        /* 打开Activity数量统计 */
        private int activityStartCount = 0;

        @Override
        public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

        }

        @Override
        public void onActivityStarted(@NonNull Activity activity) {
            activityStartCount++;
            /* 数值从0变道1说明是从后台切换到前台 */
            if (activityStartCount == 1) {
                if (mOnAppStatusListener != null) {
                    mOnAppStatusListener.onFront();
                }
            }
        }

        @Override
        public void onActivityResumed(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPaused(@NonNull Activity activity) {

        }

        @Override
        public void onActivityStopped(@NonNull Activity activity) {
            activityStartCount--;
            /* 数值从1到0说明是从前台切换到后台 */
            if (activityStartCount == 0) {
                if (mOnAppStatusListener != null) {
                    mOnAppStatusListener.onBack();
                }
            }
        }

        @Override
        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(@NonNull Activity activity) {

        }
    };

    /* 回调接口 */
    public interface OnAppStatusListener {
        /* 前台 */
        void onFront();
        /* 后台 */
        void onBack();
    }
}
