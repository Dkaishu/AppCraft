package com.tincher.appcraft.main.main;

import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tincher.appcraft.R;
import com.tincher.appcraft.base.BaseActivity;
import com.tincher.appcraft.update.UpdateManager;
import com.tincher.appcraft.utils.LogUtils;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    @Bind(R.id.text)
    TextView text;
    @Bind(R.id.bt_download)
    Button   btDownload;
    @Bind(R.id.bt_other)
    Button   btOther;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        text.setText("加载后");

    }

    @OnClick({R.id.text, R.id.bt_download, R.id.bt_other})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text:
//                startActivity(new Intent(StartVpnActivity.this, DownloadBreakpointsActivity.class));
//                startService(new Intent(StartVpnActivity.this, DownloadService.class));
//                UpdateManager updateManager = new UpdateManager();
//                updateManager.update(this);
//                sta();
                UpdateManager.create().optionalUpdate();
                break;
            case R.id.bt_download:
//                startActivity(new Intent(StartVpnActivity.this, DownloadActivity.class));
//                VersionUpdateHelper helper= new VersionUpdateHelper(this);
//                helper.startForceUpdateVersion();
//                VersionUpdateHelper.create(this).startOptionalUpdateVersion();
                TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), Environment.getExternalStorageDirectory().getAbsolutePath() + "/patch_signed_7zip.apk");
                LogUtils.e(Environment.getExternalStorageDirectory().getAbsolutePath());

                break;
            case R.id.bt_other:
//                startActivity(new Intent(this, DaoActivity.class));
                Tinker.with(getApplicationContext()).cleanPatch();

                break;
        }
    }

}
