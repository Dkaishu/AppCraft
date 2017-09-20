package com.tincher.appcraft.base;

import android.app.Application;

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
    }

}
