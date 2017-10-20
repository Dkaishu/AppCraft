package com.tincher.appcraft.main.splash.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tincher.appcraft.R;
import com.tincher.appcraft.base.BaseActivity;
import com.tincher.appcraft.main.main.MainActivity;
import com.tincher.appcraft.utils.PrefUtil;

import cn.bingoogolapple.bgabanner.BGABanner;

public class SplashActivity extends BaseActivity {
    BGABanner mBackgroundBanner;
    BGABanner mForegroundBanner;
    TextView tvGuideSkip;
    Button btnGuideEnter;

    ImageView mIVEntry;

    private static final String TAG = "SplashActivity";
    private Boolean SplashSkip;
    private static final int ANIM_TIME = 1300;
    private static final float SCALE_END = 1.25F;

    @Override
    protected int initLayout() {
        SplashSkip = PrefUtil.getBoolean(SplashActivity.this, "SplashSkip", false);
        return SplashSkip ? R.layout.activity_welcome : R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        if (SplashSkip) {
            mIVEntry = findViewById(R.id.iv_entry);

            new CountDownTimer(200, 1000) {
                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {
                    startAnim();
                }
            }.start();
        } else {
            mBackgroundBanner = findViewById(R.id.banner_guide_background);
            mForegroundBanner = findViewById(R.id.banner_guide_foreground);
            tvGuideSkip = findViewById(R.id.tv_guide_skip);
            btnGuideEnter = findViewById(R.id.btn_guide_enter);
            setListener();
            processLogic();
        }
    }

    private void setListener() {
        /**
         * 设置进入按钮和跳过按钮控件资源 id 及其点击事件
         * 如果进入按钮和跳过按钮有一个不存在的话就传 0
         * 在 BGABanner 里已经帮开发者处理了防止重复点击事件
         * 在 BGABanner 里已经帮开发者处理了「跳过按钮」和「进入按钮」的显示与隐藏
         */
        mForegroundBanner.setEnterSkipViewIdAndDelegate(R.id.btn_guide_enter, R.id.tv_guide_skip, new BGABanner.GuideDelegate() {
            @Override
            public void onClickEnterOrSkip() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
                PrefUtil.setBoolean(SplashActivity.this, "SplashSkip", true);
            }
        });
    }

    private void processLogic() {
        // 设置数据源
        mBackgroundBanner.setData(R.mipmap.uoko_guide_background_1, R.mipmap.uoko_guide_background_2, R.mipmap.uoko_guide_background_3);
        mForegroundBanner.setData(R.mipmap.uoko_guide_foreground_1, R.mipmap.uoko_guide_foreground_2, R.mipmap.uoko_guide_foreground_3);
    }

    private void startAnim() {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(mIVEntry, "scaleX", 1f, SCALE_END);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(mIVEntry, "scaleY", 1f, SCALE_END);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(ANIM_TIME).play(animatorX).with(animatorY);
        set.start();

        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                SplashActivity.this.finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 如果开发者的引导页主题是透明的，需要在界面可见时给背景 Banner 设置一个白色背景，
        // 避免滑动过程中两个 Banner 都设置透明度后能看到 Launcher
        if (!SplashSkip)mBackgroundBanner.setBackgroundResource(android.R.color.white);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //屏蔽 back 键
        return keyCode == KeyEvent.KEYCODE_BACK || super.onKeyDown(keyCode, event);
    }
}