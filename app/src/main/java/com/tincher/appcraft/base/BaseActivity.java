package com.tincher.appcraft.base;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.tincher.appcraft.R;
import com.tincher.appcraft.app.PermissionsChecker;
import com.tincher.appcraft.config.PermissionConfig;
import com.tincher.appcraft.net.networkstate.NetInfo;
import com.tincher.appcraft.net.networkstate.NetworkStateListener;
import com.tincher.appcraft.net.networkstate.NetworkStateReceiver;
import com.tincher.appcraft.utils.ToastUtils;

import butterknife.ButterKnife;

import static com.tincher.appcraft.app.PermissionsChecker.verifyPermissions;

public abstract class BaseActivity extends AppCompatActivity {
//    protected LayoutInflater mInflater;
//    protected ActionBar mActionBar;

    /**
     * 网络状态监听器
     **/
    private NetworkStateListener networkStateListener;

    protected abstract int initLayout();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        ButterKnife.bind(this);
        initNetworkStateListener();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isNeedCheck){
            PermissionsChecker.checkPermissions(this,PERMISSION_REQUEST_CODE,PermissionConfig.permissions);
        }

    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        //移除网络状态监听
        if (null != networkStateListener) {
            NetworkStateReceiver.removeNetworkStateListener(networkStateListener);
            NetworkStateReceiver.unRegisterNetworkStateReceiver(this);
        }
        super.onDestroy();
    }

    /**
     * ****************************************************************************************
     * 初始化网络状态监听器
     */
    private void initNetworkStateListener() {
        NetworkStateReceiver.registerNetworkStateReceiver(this);
        networkStateListener = new NetworkStateListener() {
            @Override
            public void onNetworkState(boolean isNetworkAvailable, NetInfo netInfo) {
                BaseActivity.this.onNetworkState(isNetworkAvailable, netInfo);
            }
        };
        //添加网络状态监听
        NetworkStateReceiver.addNetworkStateListener(networkStateListener);
    }

    /**
     * 网络状态
     *
     * @param isNetworkAvailable 网络是否可用
     * @param netInfo            网络信息
     */
    public void onNetworkState(boolean isNetworkAvailable, NetInfo netInfo) {
        //Todo 网络状态
        if (!isNetworkAvailable)ToastUtils.showShort("Network is not available");
    }

    /**
     * ***************************************************************************************
     * 权限检测
     */

    private static final int PERMISSION_REQUEST_CODE = 0;

    /**
     * 判断是否需要检测，防止不停的弹框
     */
    private boolean isNeedCheck = true;

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] paramArrayOfInt) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (!verifyPermissions(paramArrayOfInt)) {
                showMissingPermissionDialog();
                isNeedCheck = false;
            }
        }
    }

    /**
     * 显示提示信息
     */
    private void showMissingPermissionDialog() {
//        ToastUtils.showShort("Permissions Need to Check");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.ok_notifyTitle);
        builder.setMessage(R.string.ok_notifyMsg);
        // 拒绝, 退出应用
        builder.setNegativeButton(R.string.ok_cancel,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

        builder.setPositiveButton(R.string.ok_setting,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                    }
                });
        builder.setCancelable(false);
        builder.show();
    }

    /**
     *  启动应用的设置
     */
    private void startAppSettings() {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }
}
