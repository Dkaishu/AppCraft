package com.tincher.appcraft.main.customviewdemo.multi_select;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.tincher.appcraft.R;
import com.tincher.appcraft.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by dks on 2017/12/1.
 */

public class MultiSelectMunuActivity extends BaseActivity {
    @Bind(R.id.bt_select)
    Button btSelect;
    @Bind(R.id.vg_container)
    MultiSelectViewGroup vgContainer;

    @Override
    protected int initLayout() {
        return R.layout.activity_multi_selecte;
    }

    @Override
    protected void initView() {
//        vgContainer.addView(new Button(this));
//        vgContainer.setmSpanMargin(0);
        for (int i = 0; i < 12; i++) {
            Button button = new Button(this);
            button.setText(Math.pow(12, i) + "15615");
            vgContainer.addView(button);

        }
    }

    @OnClick(R.id.bt_select)
    public void onViewClicked() {
        showSelectPopWindow();
    }


    PopupWindow popupWindow;

    private void showSelectPopWindow() {
        if (popupWindow != null && popupWindow.isShowing()) popupWindow.dismiss();
        View view = View.inflate(this, R.layout.view_pop_multi_select, null);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        popupWindow = new PopupWindow(view, RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        List<MultiBase> list = new ArrayList<>();
        list.add(new MultiListType.Type1());
        list.add(new MultiListType.Type1());
        list.add(new MultiListType.Type1());
        list.add(new MultiListType.Type1());
        MultiSelectAdapter adapter = new MultiSelectAdapter(this, list);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

//        WindowManager wm = this.getWindowManager();
//        int width = wm.getDefaultDisplay().getWidth();
//        int height = wm.getDefaultDisplay().getHeight();

        WindowManager manager = this.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
//        int w = (int) (outMetrics.density*300);
        int width = outMetrics.widthPixels;
        int popWidth = (int) (outMetrics.density*300+0.5f);//popwindow 300dp
        int height2 = outMetrics.heightPixels;

        int pan = view.getWidth();
        view.getWidth();
        popupWindow.showAsDropDown(btSelect, (width - popWidth) / 2, 0);
    }


    @Override
    public void onBackPressed() {
        if (popupWindow != null && popupWindow.isShowing()) popupWindow.dismiss();
        else super.onBackPressed();
    }
}
