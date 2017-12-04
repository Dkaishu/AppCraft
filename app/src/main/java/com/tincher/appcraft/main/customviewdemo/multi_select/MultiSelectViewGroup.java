package com.tincher.appcraft.main.customviewdemo.multi_select;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dks on 2017/12/1.
 */

public class MultiSelectViewGroup extends ViewGroup {

    private final static int VIEW_MARGIN = 0;//默认VIew之间的最短间隔，斧子之间,子View之间

    public MultiSelectViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MultiSelectViewGroup(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int stages = 1;
        int stageHeight = 0;
        int stageWidth = 0;

        int wholeWidth = MeasureSpec.getSize(widthMeasureSpec);

        for (int i = 0; i < getChildCount(); i++) {
            final View child = getChildAt(i);
            // measure
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            stageWidth += (child.getMeasuredWidth() + VIEW_MARGIN);
            stageHeight = child.getMeasuredHeight();
            if (stageWidth >= wholeWidth) {
                stages++;
                //reset stageWidth
                stageWidth = child.getMeasuredWidth();
            }
        }

        int wholeHeight = (stageHeight + VIEW_MARGIN) * stages;

        // report this final dimension
        setMeasuredDimension(resolveSize(wholeWidth, widthMeasureSpec),
                resolveSize(wholeHeight, heightMeasureSpec));
    }

    private int mSpanMargin = 10;//按钮之间的间隔

    /**
     * @param mSpanMargin 按钮选项之间的间隔 default 10+10
     */
    public void setmSpanMargin(int mSpanMargin) {
        this.mSpanMargin = mSpanMargin;
    }

    @Override
    protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) {

        final int count = getChildCount();
        int row = 0;// which row lay you view relative to parent
        int lengthX = arg1;    // right position of child relative to parent
        int lengthY = arg2;    // bottom position of child relative to parent
        for (int i = 0; i < count; i++) {

            final View child = this.getChildAt(i);
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            if (i == 0) {
                lengthX += width + VIEW_MARGIN;//第一个的时候不需要加
            } else {
                lengthX += width + VIEW_MARGIN + mSpanMargin;//按钮之间的间隔
            }

//            lengthY = row * (height + VIEW_MARGIN) + VIEW_MARGIN + height + arg2;
            lengthY = row * (height + VIEW_MARGIN) + VIEW_MARGIN + height;
            //if it can't drawing on a same line , skip to next line
            if (lengthX > arg3) {
                lengthX = width + VIEW_MARGIN + arg1;
                row++;
//                lengthY = row * (height + VIEW_MARGIN) + VIEW_MARGIN + height + arg2;
                lengthY = row * (height + VIEW_MARGIN) + VIEW_MARGIN + height ;
            }
            child.layout(lengthX - width, lengthY - height, lengthX, lengthY);
        }
    }
}
