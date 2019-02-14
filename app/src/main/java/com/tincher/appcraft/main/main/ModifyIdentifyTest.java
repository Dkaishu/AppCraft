package com.tincher.appcraft.main.main;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.tincher.appcraft.R;
import com.tincher.appcraft.base.BaseActivity;
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
    Button   btID;

    @Bind(R.id.tv_wifi_mac_default)
    TextView tvWifiMacDefault;
    @Bind(R.id.tv_wifi_mac_modify)
    TextView tvWifiMacModify;
    @Bind(R.id.bt_wifi_mac)
    Button   btWifiMac;

    @Bind(R.id.tv_wifi_ssid_default)
    TextView tvWifiSsidDefault;
    @Bind(R.id.tv_wifi_ssid_modify)
    TextView tvWifiSsidModify;
    @Bind(R.id.bt_wifi_ssid)
    Button   btWifiSsid;

    @Bind(R.id.tv_phone_default)
    TextView tvPhoneDefault;
    @Bind(R.id.tv_phone_modify)
    TextView tvPhoneModify;
    @Bind(R.id.bt_phone)
    Button   btPhone;

    @Bind(R.id.tv_sim_iccid_default)
    TextView tvSimIccidDefault;
    @Bind(R.id.tv_sim_iccid_modify)
    TextView tvSimIccidModify;
    @Bind(R.id.bt_sim_iccid)
    Button   btSimIccid;

    @Bind(R.id.tv_sim_IMSI_default)
    TextView tvSimIMSIDefault;
    @Bind(R.id.tv_sim_IMSI_modify)
    TextView tvSimIMSIModify;
    @Bind(R.id.bt_sim_IMSI)
    Button   btSimIMSI;

    @Bind(R.id.tv_LocalWIFIIpAdress)
    TextView tvLocalWIFIIpAdress;
    @Bind(R.id.tv_LocalWIFIIpAdress_modify)
    TextView tvLocalWIFIIpAdressModify;
    @Bind(R.id.bt_LocalWIFIIpAdress)
    Button   btLocalWIFIIpAdress;

    @Bind(R.id.tv_GPRSLocalIpAddress)
    TextView tvGPRSLocalIpAddress;
    @Bind(R.id.tv_GPRSLocalIpAddress_modify)
    TextView tvGPRSLocalIpAddressModify;
    @Bind(R.id.bt_GPRSLocalIpAddress)
    Button   btGPRSLocalIpAddress;

    @Bind(R.id.tv_other_default)
    TextView tvOtherDefault;
    @Bind(R.id.tv_other_modify)
    TextView tvOtherModify;
    @Bind(R.id.bt_other)
    Button   btOther;

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
        tvLocalWIFIIpAdress.setText(getLocalWIFIIpAdress());
        tvGPRSLocalIpAddress.setText(getGPRSLocalIpAddress());

        log2();
    }


/*    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        setContentView(R.layout.activity_test_modify_indentify);
        tvImeiDefault.setText(getImei(this));
        tvIDDefault.setText(getID());
        tvWifiMacDefault.setText(getWifiMac());
        tvWifiSsidDefault.setText(getMacSSID());
        tvPhoneDefault.setText(getPhoneNumber());
        tvSimIccidDefault.setText(getSimIccid());
        tvSimIMSIDefault.setText(getSimIMSI());
        tvLocalWIFIIpAdress.setText(getLocalWIFIIpAdress());
        tvGPRSLocalIpAddress.setText(getGPRSLocalIpAddress());
    }*/

/*    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }*/

    private String getImei(Context context) {
        return DeviceUtils.getIMEI(context).get(0);
    }

    private String getID() {
        return DeviceUtils.getAndroidID();
    }

    private String getWifiMac() {
        return DeviceUtils.getMacAddress();
    }

    private String getMacSSID() {
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

    private String getLocalWIFIIpAdress() {
        return DeviceUtils.getWIFILocalIpAdress();
    }

    private String getGPRSLocalIpAddress() {
        return DeviceUtils.getGPRSLocalIpAddress();
    }


    @OnClick({R.id.bt_imei, R.id.bt_ID, R.id.bt_wifi_mac, R.id.bt_wifi_ssid, R.id.bt_phone, R.id.bt_sim_iccid, R.id.bt_sim_IMSI})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_imei:
                tvImeiDefault.setText(getImei(this));

                break;
            case R.id.bt_ID:
                tvIDDefault.setText(getID());

                break;
            case R.id.bt_wifi_mac:
                tvWifiMacDefault.setText(getWifiMac());

                break;
            case R.id.bt_wifi_ssid:
                tvWifiSsidDefault.setText(getMacSSID());

                break;
            case R.id.bt_phone:
                tvPhoneDefault.setText(getPhoneNumber());

                break;
            case R.id.bt_sim_iccid:
                tvSimIccidDefault.setText(getSimIccid());

                break;
            case R.id.bt_sim_IMSI:
                tvSimIMSIDefault.setText(getSimIMSI());

                break;
            case R.id.bt_LocalWIFIIpAdress:
                tvLocalWIFIIpAdress.setText(getLocalWIFIIpAdress());
                break;
            case R.id.bt_GPRSLocalIpAddress:
                tvGPRSLocalIpAddress.setText(getGPRSLocalIpAddress());
                break;
            case R.id.bt_other:
                break;
        }
    }

    private void log2(){
        AppUtils.isAppDebug();
        AppUtils.isAppRoot();

    }

}
