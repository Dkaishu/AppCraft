package com.tincher.appcraft.main.main;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tincher.appcraft.R;
import com.tincher.appcraft.base.BaseActivity;

import java.text.DecimalFormat;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by dks on 2018/4/9.
 */

public class TempTestActivity extends BaseActivity {
    @Bind(R.id.tv_1)
    TextView tv1;
    @Bind(R.id.bt_1)
    Button   bt1;
    @Bind(R.id.tv_2)
    TextView tv2;
    @Bind(R.id.bt_2)
    Button   bt2;

    String dirtyString = "12345.18545641565115";
    double dirtyLong = 12345.10551565115;
    String patten1 = "000000.0000%";
    String patten2 = "#.0000";

    @Override
    protected int initLayout() {
        return R.layout.activity_temp_test;
    }

    @Override
    protected void initView() {
        bt1.setText(patten1);
        bt2.setText(patten2);
        tv2.setText(String.valueOf(dirtyLong));

    }

    @OnClick({R.id.bt_1, R.id.bt_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_1:
                DecimalFormat format1 = new DecimalFormat(patten1);
                tv1.setText(format1.format(dirtyLong));
                break;
            case R.id.bt_2:
                DecimalFormat format2 = new DecimalFormat(patten2);
                tv1.setText(format2.format(dirtyLong));
                break;
        }
    }
}
