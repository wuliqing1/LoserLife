package com.example.y.mvp;

import android.app.Application;

/**
 * Created by 10172915 on 2016/8/1.
 */
public class MyApplication extends Application {
    private static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }

    public static Application getInstance() {
        return myApplication;
    }
}
