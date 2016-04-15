package com.bishwajit.transactions;

import android.app.Application;
import android.content.Context;

/**
 * Created by bishwajit on 4/14/2016.
 */
public class MyApplication extends Application {

    private static MyApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static MyApplication getsInstance()
    {
        return sInstance;
    }

    public static Context getContext()
    {
        return sInstance.getApplicationContext();
    }
}
