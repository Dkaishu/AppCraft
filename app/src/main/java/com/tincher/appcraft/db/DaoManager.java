package com.tincher.appcraft.db;

import android.content.Context;

import com.tincher.appcraft.db.entity.DaoMaster;
import com.tincher.appcraft.db.entity.DaoSession;


/**
 * Created by dks on 2017/11/7.
 */

public class DaoManager {
    private static DaoManager mInstance;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private DaoManager(Context context) {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(
               context, "my-db", null);
        DaoMaster mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoMaster getMaster() {
        return mDaoMaster;
    }

    public DaoSession getSession() {
        return mDaoSession;
    }

    public static DaoManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DaoManager(context);
        }
        return mInstance;
    }
}
