package com.hgc.processlifecycleownerdemo;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

/**
 * @Description TODO
 * @systemUser gchan2
 * @Author hanguangchuan
 * @Date 09-02-2022 周五 9:37
 */
public class MainApplication extends Application {

    public static final String TAG = "ProcessLifecycleOwner";

    @Override
    public void onCreate() {
        super.onCreate();
        /* 注册APP生命周期观察者 */
        ProcessLifecycleOwner.get().getLifecycle().addObserver(new ApplicationLifecycleObserver());
    }

    private static class ApplicationLifecycleObserver implements DefaultLifecycleObserver {

        @Override
        public void onStart(@NonNull LifecycleOwner owner) {
            DefaultLifecycleObserver.super.onStart(owner);
            Log.w(TAG, "ApplicationObserver: app moved to foreground");
        }

        @Override
        public void onStop(@NonNull LifecycleOwner owner) {
            DefaultLifecycleObserver.super.onStop(owner);
            Log.w(TAG, "ApplicationObserver: app moved to background");
        }
    }
}
