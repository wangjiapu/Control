package com.example.com.control.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by xiyou3g on 2017/5/22.
 */

public class ScrollView_Parent extends ViewGroup {
    My_Content_ScrollView top_ScrollView, bottom_ScrollView;
    VelocityTracker velocityTracker=VelocityTracker.obtain();
    Scroller scroller=new Scroller(getContext());
    private Boolean bottomView2Top = false;
    private Boolean topView2bottom = false;

    int position1Y;
    int minScroll;
    Boolean  isIntercept = false;
    int currPosition=0;
    int speed=200;

    int lasty;//上次滑动到的距离

    public ScrollView_Parent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public ScrollView_Parent(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScrollView_Parent(Context context) {
        super(context);
        init();
    }

    private void init() {
        post(new Runnable() {
            @Override
            public void run() {
                top_ScrollView = (My_Content_ScrollView) getChildAt(0);
                bottom_ScrollView = (My_Content_ScrollView) getChildAt(1);
                top_ScrollView.setScrollListenter(new My_Content_ScrollView.ScrollLister() {
                    @Override
                    public void onScrollToBottom() {
                        topView2bottom = true;
                    }

                    @Override
                    public void onScrollToTop() {

                    }

                    @Override
                    public void onScroll(int scrollY) {

                    }

                    @Override
                    public void notBottom() {
                        bottomView2Top = false;
                    }
                });
                bottom_ScrollView.setScrollListenter(new My_Content_ScrollView.ScrollLister() {
                    @Override
                    public void onScrollToBottom() {

                    }

                    @Override
                    public void onScrollToTop() {

                    }

                    @Override
                    public void onScroll(int scrollY) {
                        if (scrollY==0){
                            bottomView2Top=true;
                        }else{
                            bottomView2Top=false;
                        }

                    }

                    @Override
                    public void notBottom() {

                    }
                });
                position1Y=top_ScrollView.getBottom();
                minScroll= ViewConfiguration.get(getContext()).getScaledTouchSlop();
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //防止子View禁止父view拦截事件
        this.requestDisallowInterceptTouchEvent(false);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int y = (int) ev.getY();

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lasty = y;
                break;
            case MotionEvent.ACTION_MOVE:
                //判断是否已经滚动到了底部
                if (topView2bottom) {
                    int dy = lasty- y;

                    //判断是否是向上滑动和是否在第一屏
                    if (dy > 0 && currPosition == 0) {
                        if (dy >= minScroll) {
                            isIntercept = true;//拦截事件
                            lasty=y;
                        }
                    }
                }

                if (bottomView2Top) {
                    int dy = lasty - y;

                    //判断是否是向下滑动和是否在第二屏
                    if (dy < 0 && currPosition == 1) {
                        if (Math.abs(dy) >= minScroll) {
                            isIntercept = true;
                        }
                    }
                }

                break;
        }
        return isIntercept;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y=(int)event.getY();
        velocityTracker.addMovement(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int dy=lasty-y;
                if (dy+getScrollY()<0){
                    //向上滑动
                    dy=getScrollY()+dy+Math.abs(getScrollY()+dy);
                }
                if (getScrollY() + dy + getHeight() > bottom_ScrollView.getBottom()) {
                    dy = dy - (getScrollY() + dy - (bottom_ScrollView.getBottom() - getHeight()));
                }
                scrollBy(0,dy);
                break;
            case MotionEvent.ACTION_UP:
                isIntercept = false;

                velocityTracker.computeCurrentVelocity(1000);
                float yVelocity = velocityTracker.getYVelocity();

                if (currPosition == 0) {
                    if (yVelocity < 0 && yVelocity < -speed) {
                        smoothScroll(position1Y);
                        currPosition = 1;
                    } else {
                        smoothScroll(0);
                    }
                } else {
                    if (yVelocity > 0 && yVelocity > speed) {
                        smoothScroll(0);
                        currPosition = 0;
                    } else {
                        smoothScroll(position1Y);
                    }
                }
                break;
        }
        lasty=y;
        return true;
    }

    private void smoothScroll(int position1Y) {
        int dy=position1Y-getScrollY();
        scroller.startScroll(getScrollX(),getScrollY(),0,dy);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(),scroller.getCurrY());
            postInvalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        int childnum = getChildCount();
        int childTop = i1;
        for (int j = 0; j < childnum; j++) {
            View view = getChildAt(j);
            view.layout(i, childTop, i2, childTop + view.getMeasuredHeight());
            childTop += view.getMeasuredHeight();
        }
    }
}
