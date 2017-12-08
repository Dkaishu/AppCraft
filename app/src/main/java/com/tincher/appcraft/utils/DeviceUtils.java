package com.tincher.appcraft.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/8/1
 *     desc  : 设备相关工具类
 * </pre>
 */
public final class DeviceUtils {

    private DeviceUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 判断设备是否root
     *
     * @return the boolean{@code true}: 是<br>{@code false}: 否
     */
    public static boolean isDeviceRooted() {
        String su = "su";
        String[] locations = {"/system/bin/", "/system/xbin/", "/sbin/", "/system/sd/xbin/", "/system/bin/failsafe/",
                "/data/local/xbin/", "/data/local/bin/", "/data/local/"};
        for (String location : locations) {
            if (new File(location + su).exists()) {
                return true;
            }
        }
        return false;
    }

//    private String getMacAddress(Context context) {
//        String result = "";
//        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService(Context.WIFI_SERVICE);
//        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
//        result = wifiInfo.getMacAddress();
//        // Log.i(TAG, "macAdd:" + result);
//        return result;
//    }

    /**
     * IMEI是International Mobile Equipment Identity （国际移动设备标识）的简称
     * IMEI由15位数字组成的”电子串号”，它与每台手机一一对应，而且该码是全世界唯一的
     * 其组成为：
     * 1. 前6位数(TAC)是”型号核准号码”，一般代表机型
     * 2. 接着的2位数(FAC)是”最后装配号”，一般代表产地
     * 3. 之后的6位数(SNR)是”串号”，一般代表生产顺序号
     * 4. 最后1位数(SP)通常是”0″，为检验码，目前暂备用
     *
     * @param context
     * @return
     */
    @TargetApi(Build.VERSION_CODES.M)
    public static List<String> getIMEI(Context context) {
        List<String> imeiList = new ArrayList<>();
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        for (int slot = 0; slot < telephonyManager.getPhoneCount(); slot++) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                return null;
            }
            imeiList.add(telephonyManager.getDeviceId(slot));
        }
        return imeiList;
    }

    public static void setIMEI(String imei) {
        try {
            Class<?> clazz = Class.forName("android.telephony.MzTelephonyManager");
            Method method = clazz.getMethod("getDeviceId");
            Object temp = method.invoke(null);
            Field Field = clazz.getDeclaredField("666");
            //Object temp = ReflectHelper.invokeStatic("android.telephony.MzTelephonyManager", "getDeviceId", null);
            if (null != temp && temp instanceof String) {
                String i = (String) temp;

            }
        } catch (Exception e) {
            e.printStackTrace();
            // 兼容低版本和非FlymeOS
        }

    }

    /**
     * 获取设备系统版本号
     *
     * @return 设备系统版本号
     */
    public static int getSDKVersion() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * IMEI(International Mobile Equipment Identity)
     * 是国际移动设备身份码的缩写，国际移动装备辨识码，是由15位数字组成的"电子串号"，
     * 它与每台手机一一对应，而且该码是全世界唯一的。每一只手机在组装完成后都将被赋予一个
     * 全球唯一的一组号码，这个号码从生产到交付使用都将被制造生产的厂商所记录。
     * <p>
     * <p>
     * （IMSI：International Mobile SubscriberIdentification Number）
     * 是国际移动用户识别码的缩写，是区别移动用户的标志，储存在SIM卡中，可用于区别移动用户的有效信息。
     * 其总长度不超过15位，同样使用0～9的数字，结构为：MCC+MNC+MSIN。其中MCC是移动用户所属国家代号，占3位数字，
     * 中国的MCC规定为460；MNC是移动网号码，最多由两位数字组成，用于识别移动用户所归属的移动通信网；
     * MSIN是移动用户识别码，用以识别某一移动通信网中的移动用户。
     * <p>
     * <p>
     * ICCID：Integrate circuit card identity 集成电路卡识别码（固化在手机SIM卡中）
     * ICCID为IC卡的唯一识别号码，共有20位数字组成，其编码格式为：XXXXXX 0MFSS YYGXX XXXXX。
     * 分别介绍如下： 前六位运营商代码：中国移动的为：898600；中国联通的为：898601。
     * <p>
     * <p>
     * IMSI：国际移动用户号码标识；
     * MSISDN:mobile subscriber ISDN用户号码，这个是我们说的139，136那个号码，说白了就是手机号；
     * ICCID:ICC identity集成电路卡标识，这个是唯一标识一张卡片物理号码的；
     * IMEI：international mobile Equipment identity手机唯一标识码；
     * <p>
     * 需要权限：<uses-permission android:name="android.permission.READ_PHONE_STATE" />
     */
    public static String getPhoneNumber() {
        StringBuffer sb = new StringBuffer();
        TelephonyManager tm = (TelephonyManager) Utils.getApp().getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(Utils.getApp(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        String IMEI = tm.getDeviceId();
        String TEL = tm.getLine1Number();
        String ICCID = tm.getSimSerialNumber();
        String IMSI = tm.getSubscriberId();

        sb.append("IMEI:");
        sb.append(IMEI);
        sb.append("\nTEL:");
        sb.append(TEL);
        sb.append("\nICCID:");
        sb.append(ICCID);
        sb.append("\nIMSI:");
        sb.append(IMSI);
        if (TEL != null) return TEL;
        Cursor myCursor = Utils.getApp().getContentResolver().query(Uri.parse("content://sms"),
                new String[]{/*"msg_id", "contact_id", */
                        "(select address from addr where type = 151) as address"},
                null, null, "date desc");
        if (myCursor != null) {
            myCursor.moveToFirst();
//            Log.d("number", "number="+myCursor.getString(myCursor.getColumnIndex("address")));
            TEL = myCursor.getString(myCursor.getColumnIndex("address"));
            myCursor.close();
        }

        LogUtil.e(sb.toString());
        return TEL;
    }

    public static String getSimIccid() {
        TelephonyManager tm = (TelephonyManager) Utils.getApp().getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(Utils.getApp(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        String ICCID = tm.getSimSerialNumber();
//        String IMSI = tm.getSubscriberId();

        return ICCID;
    }

    public static String getSimIMSI() {
        TelephonyManager tm = (TelephonyManager) Utils.getApp().getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(Utils.getApp(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        String IMSI = tm.getSubscriberId();

        return IMSI;
    }

    /**
     * 获取设备AndroidID
     *
     * @return AndroidID
     */
    @SuppressLint("HardwareIds")
    public static String getAndroidID() {
        return Settings.Secure.getString(Utils.getApp().getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    /**
     * 获取设备MAC地址
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>}</p>
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.INTERNET"/>}</p>
     *
     * @return MAC地址
     */
    public static String getMacAddress() {
        String macAddress = getMacAddressByWifiInfo();
        if (!"02:00:00:00:00:00".equals(macAddress)) {
            return macAddress;
        }
        macAddress = getMacAddressByNetworkInterface();
        if (!"02:00:00:00:00:00".equals(macAddress)) {
            return macAddress;
        }
        macAddress = getMacAddressByFile();
        if (!"02:00:00:00:00:00".equals(macAddress)) {
            return macAddress;
        }
        return "please open wifi";
    }

    public static String getMacSSID() {
        String macSSID = getMacSSIDByWifiInfo();
        if (!"02:00:00:00:00:00".equals(macSSID)) {
            return macSSID;
        }
        return "please open wifi";
    }

    /**
     * 获取设备MAC地址
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>}</p>
     *
     * @return MAC地址
     */
    @SuppressLint("HardwareIds")
    private static String getMacAddressByWifiInfo() {
        try {
            @SuppressLint("WifiManagerLeak")
            WifiManager wifi = (WifiManager) Utils.getApp().getSystemService(Context.WIFI_SERVICE);
            if (wifi != null) {
                WifiInfo info = wifi.getConnectionInfo();
                if (info != null) return info.getMacAddress();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "02:00:00:00:00:00";
    }

    @SuppressLint("HardwareIds")
    private static String getMacSSIDByWifiInfo() {
        try {
            @SuppressLint("WifiManagerLeak")
            WifiManager wifi = (WifiManager) Utils.getApp().getSystemService(Context.WIFI_SERVICE);
            if (wifi != null) {
                WifiInfo info = wifi.getConnectionInfo();
                if (info != null) return info.getSSID();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "02:00:00:00:00:00";
    }

    /**
     * 获取设备MAC地址
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.INTERNET"/>}</p>
     *
     * @return MAC地址
     */
    private static String getMacAddressByNetworkInterface() {
        try {
            List<NetworkInterface> nis = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface ni : nis) {
                if (!ni.getName().equalsIgnoreCase("wlan0")) continue;
                byte[] macBytes = ni.getHardwareAddress();
                if (macBytes != null && macBytes.length > 0) {
                    StringBuilder res1 = new StringBuilder();
                    for (byte b : macBytes) {
                        res1.append(String.format("%02x:", b));
                    }
                    return res1.deleteCharAt(res1.length() - 1).toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "02:00:00:00:00:00";
    }

    /**
     * 获取设备MAC地址
     *
     * @return MAC地址
     */
    private static String getMacAddressByFile() {
        ShellUtils.CommandResult result = ShellUtils.execCmd("getprop wifi.interface", false);
        if (result.result == 0) {
            String name = result.successMsg;
            if (name != null) {
                result = ShellUtils.execCmd("cat /sys/class/net/" + name + "/address", false);
                if (result.result == 0) {
                    if (result.successMsg != null) {
                        return result.successMsg;
                    }
                }
            }
        }
        return "02:00:00:00:00:00";
    }

    /**
     * 获取设备厂商
     * <p>如Xiaomi</p>
     *
     * @return 设备厂商
     */

    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    /**
     * 获取设备型号
     * <p>如MI2SC</p>
     *
     * @return 设备型号
     */
    public static String getModel() {
        String model = Build.MODEL;
        if (model != null) {
            model = model.trim().replaceAll("\\s*", "");
        } else {
            model = "";
        }
        return model;
    }

    /**
     * 关机
     * <p>需要root权限或者系统权限 {@code <android:sharedUserId="android.uid.system"/>}</p>
     */
    public static void shutdown() {
        ShellUtils.execCmd("reboot -p", true);
        Intent intent = new Intent("android.intent.action.ACTION_REQUEST_SHUTDOWN");
        intent.putExtra("android.intent.extra.KEY_CONFIRM", false);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Utils.getApp().startActivity(intent);
    }

    /**
     * 重启
     * <p>需要root权限或者系统权限 {@code <android:sharedUserId="android.uid.system"/>}</p>
     */
    public static void reboot() {
        ShellUtils.execCmd("reboot", true);
        Intent intent = new Intent(Intent.ACTION_REBOOT);
        intent.putExtra("nowait", 1);
        intent.putExtra("interval", 1);
        intent.putExtra("window", 0);
        Utils.getApp().sendBroadcast(intent);
    }

    /**
     * 重启
     * <p>需系统权限 {@code <android:sharedUserId="android.uid.system"/>}</p>
     *
     * @param reason 传递给内核来请求特殊的引导模式，如"recovery"
     */
    public static void reboot(final String reason) {
        PowerManager mPowerManager = (PowerManager) Utils.getApp().getSystemService(Context.POWER_SERVICE);
        try {
            mPowerManager.reboot(reason);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 重启到recovery
     * <p>需要root权限</p>
     */
    public static void reboot2Recovery() {
        ShellUtils.execCmd("reboot recovery", true);
    }

    /**
     * 重启到bootloader
     * <p>需要root权限</p>
     */
    public static void reboot2Bootloader() {
        ShellUtils.execCmd("reboot bootloader", true);
    }


    /**
     * 使用WIFI时，获取本机IP地址
     */

    public static String getWIFILocalIpAdress() {

        //获取wifi服务
        @SuppressLint("WifiManagerLeak")
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService(Context.WIFI_SERVICE);

//        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService(Context.WIFI_SERVICE);
        //判断wifi是否开启
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        String BSSID = wifiInfo.getBSSID();
        int NetworkId = wifiInfo.getNetworkId();
        boolean HiddenSSID = wifiInfo.getHiddenSSID();
        int Rssi = wifiInfo.getRssi();

        String ip = formatIpAddress(ipAddress);
        return ip;
    }

    private static String formatIpAddress(int ipAdress) {

        return (ipAdress & 0xFF) + "." +
                ((ipAdress >> 8) & 0xFF) + "." +
                ((ipAdress >> 16) & 0xFF) + "." +
                (ipAdress >> 24 & 0xFF);
    }

    /**
     * 使用GPRS时，获取本机IP地址
     *
     * @return
     */
    public static String getGPRSLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()&&!inetAddress.isLinkLocalAddress()) {
                        return inetAddress.getHostAddress().toString();//Ipv4
                    }
                    if (!inetAddress.isLoopbackAddress()) {
                        String Ipv6 =  inetAddress.getHostAddress().toString();//Ipv6
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
            Log.e("Wifi IpAddress", ex.toString());
        }
        return null;
    }
}
