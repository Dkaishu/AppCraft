package com.tincher.appcraft.utils;

import android.widget.ImageView;

/**
 * Created by dks on 2017/10/24.
 */

public class PhotoLoadTools {

    private static final String TAG = "PhotoLoadTools";
    private ImageView mPhoto;
    private Boolean showChoice = false;//是否提示选取方式：拍照 or 选取文件

    private PhotoLoadTools(ImageView mPhoto) {
        this.mPhoto = mPhoto;
    }
    public PhotoLoadTools init(ImageView mPhoto){
        return new PhotoLoadTools(mPhoto);
    }

    public void setShowChoice(Boolean showChoice) {
        this.showChoice = showChoice;
    }

    public  void start(){
        if (showChoice){

        }else {

        }
    }

    private void takePhoto(){

    }

}
