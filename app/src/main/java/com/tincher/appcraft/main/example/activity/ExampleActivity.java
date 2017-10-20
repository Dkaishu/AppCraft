package com.tincher.appcraft.main.example.activity;

import android.widget.TextView;

import com.tincher.appcraft.R;
import com.tincher.appcraft.base.BaseActivity;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/9/19.
 */

public class ExampleActivity extends BaseActivity {

    @Bind(R.id.text)
    TextView text;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

}
