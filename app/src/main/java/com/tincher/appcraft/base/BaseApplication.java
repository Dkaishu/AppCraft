package com.tincher.appcraft.base;

import android.app.Application;

import com.dkaishu.zxinglib.activity.ZXingLib;

public class BaseApplication extends Application {
    public static BaseApplication baseApplication;

    public static BaseApplication getApplication() {
        return baseApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        //LeakCanary.install(this);
        //        DBManager.init(this);
        ZXingLib.initDisplayOpinion(this);
    }

}
