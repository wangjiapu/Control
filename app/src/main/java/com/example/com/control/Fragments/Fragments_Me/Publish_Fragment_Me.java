package com.example.com.control.Fragments.Fragments_Me;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.com.control.Adapters.MyPagerAdapter;
import com.example.com.control.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;



public class Publish_Fragment_Me extends Fragment {
    private TabLayout tab;
    private ViewPager mviewPager;
    private List<View> mViewList = new ArrayList<>();//页卡视图集合
    private List<String> mTitleList = new ArrayList<>();//页卡标题集合

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.publishfragment_layout, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        tab = (TabLayout) view.findViewById(R.id.tabs);
        mviewPager = (ViewPager) view.findViewById(R.id.vp);
        View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.publish_context, null);
        View view2 = LayoutInflater.from(getActivity()).inflate(R.layout.publish_context_p, null);
        mViewList.add(view1);
        mViewList.add(view2);
        mTitleList.add("宝贝");
        mTitleList.add("问答");

        tab.setTabMode(TabLayout.MODE_FIXED);

        tab.addTab(tab.newTab().setText(mTitleList.get(0)));
        tab.addTab(tab.newTab().setText(mTitleList.get(1)));

        MyPagerAdapter mpa = new MyPagerAdapter(mViewList, mTitleList);
        mviewPager.setAdapter(mpa);
        tab.setupWithViewPager(mviewPager);//将tablayout和viewpager关联起来
        tab.setTabsFromPagerAdapter(mpa);//给tab设置适配器
        tab.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(tab,40,40);
            }
        });
    }

    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip,
                Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip,
                Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0,
                    LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }
}
