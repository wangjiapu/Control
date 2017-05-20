package com.example.com.control;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.com.control.Fragments.DesignFrament;
import com.example.com.control.Fragments.HomeFrament;
import com.example.com.control.Fragments.MarketFrament;
import com.example.com.control.Fragments.MeFrament;


public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private Fragment home_page;
    private Fragment design;
    private Fragment market;
    private Fragment me;

    private LinearLayout homelayout;
    private LinearLayout designlayout;
    private LinearLayout marketlayout;
    private LinearLayout melayout;

    private ImageView home_img;
    private ImageView design_img;
    private ImageView market_img;
    private ImageView me_img;

    private TextView home_tv;
    private TextView design_tv;
    private TextView market_tv;
    private TextView me_tv;
    FrameLayout Fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        selectImg_tv(0);
    }

    private void initEvent() {
        homelayout.setOnClickListener(this);
        designlayout.setOnClickListener(this);
        marketlayout.setOnClickListener(this);
        melayout.setOnClickListener(this);
    }

    private void initView() {
        homelayout = (LinearLayout) findViewById(R.id.homelayout);
        designlayout = (LinearLayout) findViewById(R.id.designlayout);
        marketlayout = (LinearLayout) findViewById(R.id.marketlayout);
        melayout = (LinearLayout) findViewById(R.id.melayout);

        home_img = (ImageView) findViewById(R.id.home_img);
        design_img = (ImageView) findViewById(R.id.design_img);
        market_img = (ImageView) findViewById(R.id.market_img);
        me_img = (ImageView) findViewById(R.id.me_img);

        home_tv = (TextView) findViewById(R.id.home_tv);
        design_tv = (TextView) findViewById(R.id.design_tv);
        market_tv = (TextView) findViewById(R.id.market_tv);
        me_tv = (TextView) findViewById(R.id.me_tv);

        Fragment = (FrameLayout) findViewById(R.id.frame);

    }


    @Override
    public void onClick(View v) {
        resetImg_tv();
        switch (v.getId()) {
            case R.id.homelayout:
                selectImg_tv(0);
                break;
            case R.id.designlayout:
                selectImg_tv(1);
                break;
            case R.id.marketlayout:
                selectImg_tv(2);
                break;
            case R.id.melayout:
                selectImg_tv(3);

                break;
        }
    }

    /**
     * 这是图片以及字体颜色成未被点击的颜色
     */

    private void resetImg_tv() {
        home_img.setImageResource(R.drawable.home);
        home_tv.setTextColor(this.getResources().getColor(R.color.colormenu));
        design_img.setImageResource(R.drawable.design1);
        design_tv.setTextColor(this.getResources().getColor(R.color.colormenu));
        market_img.setImageResource(R.drawable.market);
        market_tv.setTextColor(this.getResources().getColor(R.color.colormenu));
        me_img.setImageResource(R.drawable.me);
        me_tv.setTextColor(this.getResources().getColor(R.color.colormenu));
    }

    /**
     * 切换底部状态栏变为点击状态
     *
     * @param pos
     */
    private void selectImg_tv(int pos) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        switch (pos) {
            case 0:
                if (home_page == null) {
                    home_page = new HomeFrament();
                    transaction.add(R.id.frame, home_page);
                } else {
                    transaction.show(home_page);
                }
                home_img.setImageResource(R.drawable.homeclick);
                home_tv.setTextColor(this.getResources().getColor(R.color.colormenu_select));
                break;
            case 1:
                if (design == null) {
                    design = new DesignFrament();
                    transaction.add(R.id.frame, design);
                } else {
                    transaction.show(design);
                }
                design_img.setImageResource(R.drawable.design1click);
                design_tv.setTextColor(this.getResources().getColor(R.color.colormenu_select));
                break;
            case 2:
                if (market == null) {
                    market = new MarketFrament();
                    transaction.add(R.id.frame, market);
                } else {
                    transaction.show(market);
                }
                market_img.setImageResource(R.drawable.marketclick);
                market_tv.setTextColor(this.getResources().getColor(R.color.colormenu_select));
                break;
            case 3:
                if (me == null) {
                    me = new MeFrament();
                    transaction.add(R.id.frame, me);
                } else {
                    transaction.show(me);
                }
                me_img.setImageResource(R.drawable.meclick);
                me_tv.setTextColor(this.getResources().getColor(R.color.colormenu_select));
                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (home_page != null) {
            transaction.hide(home_page);
        }
        if (design != null) {
            transaction.hide(design);
        }
        if (market != null) {
            transaction.hide(market);
        }
        if (me != null) {
            transaction.hide(me);
        }
    }

}
