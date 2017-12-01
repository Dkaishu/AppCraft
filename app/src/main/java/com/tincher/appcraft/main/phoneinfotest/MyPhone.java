package com.tincher.appcraft.main.phoneinfotest;

import com.tincher.appcraft.utils.LogUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by dks on 2017/11/30.
 */

public class MyPhone {

    public void MyPhoneIn() {
        Class<?> clazz = null;
        try {
            clazz = Class.forName("com.android.internal.telephony.Phone");
            Method method = clazz.getMethod("getDeviceId");
            Object temp = method.invoke(null);
            Field Field = clazz.getDeclaredField("666");

        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e(e);
        }

//        com.android.internal.telephony.Phone  phone;
//
//        this.phone = phone;
    }
}
