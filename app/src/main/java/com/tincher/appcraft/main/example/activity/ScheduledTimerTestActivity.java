package com.tincher.appcraft.main.example.activity;

import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.tincher.appcraft.R;
import com.tincher.appcraft.base.BaseActivity;
import com.tincher.appcraft.utils.LogUtils;
import com.tincher.appcraft.widget.ScheduledTimer;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by dks on 2018/3/9.
 */

public class ScheduledTimerTestActivity extends BaseActivity {
    @Bind(R.id.bt_start)
    Button   btStart;
    @Bind(R.id.tv_content)
    TextView tvContent;
    @Bind(R.id.bt_shut_down)
    Button   btShutDown;

    @Override
    protected int initLayout() {
        return R.layout.activity_test_scheduled_timer;
    }

    @Override
    protected void initView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    private ScheduledTimer scheduledTimer = null;

    @OnClick({R.id.bt_start, R.id.bt_shut_down})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_start:
                tvContent.setText("马上");
                scheduledTimer = new ScheduledTimer(new Runnable() {
                    @Override
                    public void run() {
//                        finish();
                        LogUtils.e("6666666666666666666666");
                        tvContent.post(new Runnable() {
                            @Override
                            public void run() {
                                tvContent.setText("wan");
                            }
                        });
                    }
                }, 10, TimeUnit.SECONDS);
                scheduledTimer.schedule();

                break;
            case R.id.bt_shut_down:
                if (scheduledTimer != null) {
                    scheduledTimer.shutdown();
                    tvContent.setText("10");
                }
                new ScheduledTimer(new myRun(tvContent),10,TimeUnit.SECONDS).schedule();
                new ScheduledTimer(new myRun(tvContent),0,TimeUnit.SECONDS);
                break;
        }
    }

    final class myRun implements Runnable {
        private View mView;

        public myRun(View view) {
            this.mView = view;
        }

        @Override
        public void run() {
            mView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mView.setVisibility(View.GONE);
                }
            },0);
        }
    }
}
