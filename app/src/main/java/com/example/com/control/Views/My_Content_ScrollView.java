package com.example.com.control.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by xiyou3g on 2017/5/22.
 */

public class My_Content_ScrollView extends ScrollView {

    private ScrollLister mScrollLister;
    //监听事件
    public void setScrollListenter(ScrollLister scrollListenter){
        this.mScrollLister=scrollListenter;
    }
    public My_Content_ScrollView(Context context) {
        super(context);
    }
    public My_Content_ScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public My_Content_ScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_MOVE:
                if(mScrollLister!=null){

                }
                break;
        }
        return false;
    }

    interface ScrollLister{

        void onScrollToBottom();
        void onScrollToTop();
        void onScroll(int scrollY);
        void notBottom();
    }
}
