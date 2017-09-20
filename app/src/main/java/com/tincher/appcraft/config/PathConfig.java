package com.tincher.appcraft.config;

import android.os.Environment;

/**
 * Created by Administrator on 2017/9/18.
 */

public class PathConfig {

    public static final String PREF_NAME = "com.tincher.appcraft";
    public static final String DOWNLOAD_FILE_DIR = Environment.getExternalStorageDirectory().getPath()+"/okHttp_download/";
    public static final String CACHE_DIR = Environment.getExternalStorageDirectory().getPath()+"/okHttp_cache";

}
