package com.tincher.appcraft.main.main;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tincher.appcraft.R;
import com.tincher.appcraft.base.BaseActivity;
import com.tincher.appcraft.update.VersionUpdateHelper;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    @Bind(R.id.text)
    TextView text;
    @Bind(R.id.bt_download)
    Button btDownload;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.text, R.id.bt_download})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text:
//                startActivity(new Intent(MainActivity.this, DownloadBreakpointsActivity.class));
//                startService(new Intent(MainActivity.this, DownloadService.class));
//                UpdateManager updateManager = new UpdateManager();
//                updateManager.update(this);
//                sta();
                break;
            case R.id.bt_download:
//                startActivity(new Intent(MainActivity.this, DownloadActivity.class));
                VersionUpdateHelper helper= new VersionUpdateHelper(this);
//                helper.startForceUpdateVersion();
                helper.startOptionalUpdateVersion();
                break;
        }
    }

}