package com.tincher.appcraft.net.networkstate;

public  interface NetworkStateListener {


    /**
     * 网络状态回调方法
     * @param isNetworkAvailable 网络是否可用
     * @param netInfo 网络信息
     */
    public void onNetworkState(boolean isNetworkAvailable, NetInfo netInfo);

}
