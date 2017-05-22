package com.example.com.control.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by xiyou3g on 2017/5/22.
 *
 */

public class ScrollView_Parent extends ViewGroup {

    public ScrollView_Parent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public ScrollView_Parent(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public ScrollView_Parent(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        int childnum=getChildCount();
        int childTop=i2;
        for(int j=0;j<childnum;j++){
            View view=getChildAt(j);
            view.layout(i,childTop,i2,childTop+view.getMeasuredHeight());
            childTop+=view.getMeasuredHeight();
        }
    }
}
