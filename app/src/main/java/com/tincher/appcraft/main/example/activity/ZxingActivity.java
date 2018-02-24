package com.tincher.appcraft.main.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dkaishu.zxinglib.activity.CaptureActivity;
import com.dkaishu.zxinglib.activity.CodeUtils;
import com.tincher.appcraft.R;
import com.tincher.appcraft.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by dks on 2017/11/13.
 */

public class ZxingActivity extends BaseActivity {
    @Bind(R.id.bt_start)
    Button btStart;
    @Bind(R.id.tv_content)
    TextView tvContent;

    private static final int REQUEST_CODE_SIMPLE= 1;
    @Override
    protected int initLayout() {
        return R.layout.activity_zxing;
    }

    @Override
    protected void initView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }


    @OnClick({R.id.bt_start, R.id.tv_content})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_start:
               Intent intent = new Intent(ZxingActivity.this, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SIMPLE);
                break;
            case R.id.tv_content:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_SIMPLE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
//                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                    tvContent.setText(result);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(ZxingActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
