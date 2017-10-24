package com.tincher.appcraft.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by dks on 2017/10/24.
 */

public class PhotoTools {

    private static final String TAG = "PhotoTools";
    private Activity mActivity;
    private ImageView mPhoto;
    private Boolean showChoice = false;//是否提示选取方式：拍照 or 选取文件

    private File takeImageFile;

    private PhotoTools(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public PhotoTools init(Activity mActivity) {
        return new PhotoTools(mActivity);
    }

    public void setShowChoice(Boolean showChoice) {
        this.showChoice = showChoice;
    }

    public void start() {
        if (showChoice) {

        } else {

        }
    }

    /**
     * 调用相机拍照
     */
    public void takePhoto(int requestCode) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePictureIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if (takePictureIntent.resolveActivity(mActivity.getPackageManager()) != null) {
            if (existSDCard())
                takeImageFile = new File(Environment.getExternalStorageDirectory(), "/DCIM/camera/");
            else takeImageFile = Environment.getDataDirectory();
            takeImageFile = createFile(takeImageFile, "IMG_", ".jpg");
            if (takeImageFile != null) {
                // 默认情况下，即不需要指定intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                // 照相机有自己默认的存储路径，拍摄的照片将返回一个缩略图。如果想访问原始图片，
                // 可以通过dat extra能够得到原始图片位置。即，如果指定了目标uri，data就没有数据，
                // 如果没有指定uri，则data就返回有数据！

                Uri uri;
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
                    uri = Uri.fromFile(takeImageFile);
                } else {

                    /**
                     * 7.0 调用系统相机拍照不再允许使用Uri方式，应该替换为FileProvider
                     * 并且这样可以解决MIUI系统上拍照返回size为0的情况
                     */
                    uri = FileProvider.getUriForFile(mActivity, getFileProviderName(mActivity), takeImageFile);
                    //加入uri权限 要不三星手机不能拍照
                    List<ResolveInfo> resInfoList = mActivity.getPackageManager().queryIntentActivities(takePictureIntent, PackageManager.MATCH_DEFAULT_ONLY);
                    for (ResolveInfo resolveInfo : resInfoList) {
                        String packageName = resolveInfo.activityInfo.packageName;
                        mActivity.grantUriPermission(packageName, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    }
                }
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            }
        }
        mActivity.startActivityForResult(takePictureIntent, requestCode);
    }

    /**
     * 用于解决provider冲突
     */
    private static String getFileProviderName(Context context) {
        return context.getPackageName() + ".provider";
    }

    /**
     * 根据系统时间、前缀、后缀产生一个文件
     */
    private static File createFile(File folder, String prefix, String suffix) {
        if (!folder.exists() || !folder.isDirectory()) folder.mkdirs();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA);
        String filename = prefix + dateFormat.format(new Date(System.currentTimeMillis())) + suffix;
        return new File(folder, filename);
    }

    /**
     * 判断SDCard是否可用
     */
    private static boolean existSDCard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 通过文件选取图片
     */
    public void slectPhoto(){
        //Todo 通过文件选取图片
    }


}
