package com.example.com.control;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.com.control.bean.Lists;

/**
 * Created by xiyou3g on 2017/5/20.
 * 物品详细信息活动
 *  用户可以在次活动中实现自己的购买，了解的商品的功能；
 */

public class HomeGoodsInfoActivity extends Activity {
    private int GOODSPOS;
    //private ImageView test;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hgia_layout);
        GOODSPOS=getIntent().getIntExtra("data",0);
       /* test=(ImageView) findViewById(R.id.test);
        Glide.with(this).load(Lists.housesList.get(GOODSPOS).getHouse_img())
                .placeholder(R.drawable.temp).crossFade().into(test);*/

    }
}
