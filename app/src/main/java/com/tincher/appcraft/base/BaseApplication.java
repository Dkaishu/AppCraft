package com.tincher.appcraft.base;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.multidex.MultiDex;

import com.dkaishu.zxinglib.activity.ZXingLib;
import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.app.DefaultApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tincher.appcraft.app.AppContext;
import com.tincher.appcraft.utils.Utils;

@SuppressWarnings("unused")
@DefaultLifeCycle(application = ".FakeBaseApplication",             //application name to generate
        flags = ShareConstants.TINKER_ENABLE_ALL, loadVerifyFlag = false)
public class BaseApplication extends DefaultApplicationLike {
//    public static BaseApplication baseApplication;
//
//    public static BaseApplication getApplication() {
//        return baseApplication;
//    }

//    public BaseApplication() {
//        super(
//                //tinkerFlags, which types is supported
//                //dex only, library only, all support
//                ShareConstants.TINKER_ENABLE_ALL,
//                // This is passed as a string so the shell application does not
//                // have a binary dependency on your ApplicationLifeCycle class.
//                "com.tincher.appcraft.base.BaseApplication");
//    }

    public BaseApplication(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag,
                                 long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }

    //tinkerFlags above

    @Override
    public void onCreate() {
        super.onCreate();
//        baseApplication = this;
        //LeakCanary.install(this);
        //        DBManager.init(this);
        ZXingLib.initDisplayOpinion(getApplication());
        Utils.init(getApplication());
    }

    /**
     * install multiDex before install tinker
     * so we don't need to put the tinker lib classes in the main dex
     *
     * @param base
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        //you must install multiDex whatever tinker is installed!
        MultiDex.install(base);

       AppContext.application = getApplication();
        AppContext.context = getApplication();
        /*

        TinkerManager.setTinkerApplicationLike(this);

        TinkerManager.initFastCrashProtect();
        //should set before tinker is installed
        TinkerManager.setUpgradeRetryEnable(true);

        //optional set logIml, or you can use default debug log
        TinkerInstaller.setLogIml(new MyLogImp());

        //installTinker after load multiDex
        //or you can put com.tencent.tinker.** to main dex
        TinkerManager.installTinker(this);
        Tinker tinker = Tinker.with(getApplication());*/

        TinkerInstaller.install(this);

    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks callback) {
        getApplication().registerActivityLifecycleCallbacks(callback);
    }

}
