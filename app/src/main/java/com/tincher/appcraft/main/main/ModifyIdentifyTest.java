package com.tincher.appcraft.main.main;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tincher.appcraft.R;
import com.tincher.appcraft.base.BaseActivity;
import com.tincher.appcraft.main.phoneinfotest.MyPhone;
import com.tincher.appcraft.utils.DeviceUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by dks on 2017/11/29.
 */

public class ModifyIdentifyTest extends BaseActivity {
    @Bind(R.id.tv_imei_default)
    TextView tvImeiDefault;
    @Bind(R.id.tv_imei_modify)
    TextView tvImeiModify;
    @Bind(R.id.bt_imei)
    Button btImei;

    @Bind(R.id.tv_ID_default)
    TextView tvIDDefault;
    @Bind(R.id.tv_ID_modify)
    TextView tvIDModify;
    @Bind(R.id.bt_ID)
    Button btID;

    @Bind(R.id.tv_wifi_mac_default)
    TextView tvWifiMacDefault;
    @Bind(R.id.tv_wifi_mac_modify)
    TextView tvWifiMacModify;
    @Bind(R.id.bt_wifi_mac)
    Button btWifiMac;

    @Bind(R.id.tv_wifi_ssid_default)
    TextView tvWifiSsidDefault;
    @Bind(R.id.tv_wifi_ssid_modify)
    TextView tvWifiSsidModify;
    @Bind(R.id.bt_wifi_ssid)
    Button btWifiSsid;

    @Bind(R.id.tv_phone_default)
    TextView tvPhoneDefault;
    @Bind(R.id.tv_phone_modify)
    TextView tvPhoneModify;
    @Bind(R.id.bt_phone)
    Button btPhone;

    @Bind(R.id.tv_sim_iccid_default)
    TextView tvSimIccidDefault;
    @Bind(R.id.tv_sim_iccid_modify)
    TextView tvSimIccidModify;
    @Bind(R.id.bt_sim_iccid)
    Button btSimIccid;

    @Bind(R.id.tv_sim_IMSI_default)
    TextView tvSimIMSIDefault;
    @Bind(R.id.tv_sim_IMSI_modify)
    TextView tvSimIMSIModify;
    @Bind(R.id.bt_sim_IMSI)
    Button btSimIMSI;

    @Override
    protected int initLayout() {
        return R.layout.activity_test_modify_indentify;
    }

    @Override
    protected void initView() {
        tvImeiDefault.setText(getImei(this));
        tvIDDefault.setText(getID());
        tvWifiMacDefault.setText(getWifiMac());
        tvWifiSsidDefault.setText(getMacSSID());
        tvPhoneDefault.setText(getPhoneNumber());
        tvSimIccidDefault.setText(getSimIccid());
        tvSimIMSIDefault.setText(getSimIMSI());
    }



    private String getImei(Context context) {
        return DeviceUtils.getIMEI(context).get(0);
    }
    private String getID() {
        return DeviceUtils.getAndroidID();
    }

    private String getWifiMac() {
        return DeviceUtils.getMacAddress();
    }
    private String getMacSSID(){
        return DeviceUtils.getMacSSID();
    }

    private String getPhoneNumber() {
        return DeviceUtils.getPhoneNumber();
    }
    private String getSimIccid() {
        return DeviceUtils.getSimIccid();
    }
    private String getSimIMSI() {
        return DeviceUtils.getSimIMSI();
    }



    @OnClick({R.id.bt_imei, R.id.bt_ID, R.id.bt_wifi_mac, R.id.bt_wifi_ssid, R.id.bt_phone, R.id.bt_sim_iccid, R.id.bt_sim_IMSI})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_imei:
                MyPhone myPhone = new MyPhone();
                myPhone.MyPhoneIn();
                break;
            case R.id.bt_ID:
                break;
            case R.id.bt_wifi_mac:
                break;
            case R.id.bt_wifi_ssid:
                break;
            case R.id.bt_phone:
                break;
            case R.id.bt_sim_iccid:
                break;
            case R.id.bt_sim_IMSI:
                break;
        }
    }
}
