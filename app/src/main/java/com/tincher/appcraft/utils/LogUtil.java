package com.tincher.appcraft.utils;

import android.util.Log;

import com.tincher.appcraft.BuildConfig;

public final class LogUtil {
    public static Boolean isDebug = BuildConfig.SHOWLOG;
    public static String Debug = "debug_";

    public static void v(String tagName, String message) {
        if (isDebug) {
            Log.v(Debug + tagName, message);
        }
    }

    public static void v(String tagName, Exception e) {
        if (isDebug) {
            Log.v(Debug + tagName, e.getMessage(), e);
        }
    }

    public static void d(String tagName, String message) {
        if (isDebug) {
            Log.d(Debug + tagName, message);
        }
    }

    public static void d(String tagName, Exception e) {
        if (isDebug) {
            Log.i(Debug + tagName, e.getMessage(), e);
        }
    }

    public static void i(String tagName, String message) {
        if (isDebug) {
            Log.i(Debug + tagName, message);
        }
    }

    public static void i(String tagName, Exception e) {
        if (isDebug) {
            Log.i(Debug + tagName, e.getMessage(), e);
        }
    }

    public static void w(String tagName, String message) {
        if (isDebug) {
            Log.w(Debug + tagName, message);
        }
    }

    public static void w(String tagName, Exception e) {
        if (isDebug) {
            Log.w(Debug + tagName, e.getMessage(), e);
        }
    }

    public static void e(String tagName, String message) {
        if (isDebug) {
            Log.e(Debug + tagName, message);
        }
    }

    public static void e(String tagName, Exception e) {
        if (isDebug) {
            Log.e(Debug + tagName, e.getMessage(), e);
        }
    }
}