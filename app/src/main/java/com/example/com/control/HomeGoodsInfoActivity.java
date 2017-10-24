package com.example.com.control;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.com.control.bean.Lists;

/**
 * Created by xiyou3g on 2017/5/20.
 * 物品详细信息活动
 *  用户可以在次活动中实现自己的购买，了解的商品的功能；
 */

public class HomeGoodsInfoActivity extends Activity implements View.OnClickListener{
    private int GOODSPOS;
    private ImageView iv_good_detai_back;
    private ImageView iv_good_detai_img;
    private RelativeLayout rl_good_detail_jin;
    private ImageView iv_good_detai_collect_select;
    private ImageView iv_good_detai_collect_unselect;
    private Context mcontext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hgia_layout);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        GOODSPOS=getIntent().getIntExtra("data",0);
        mcontext=this;
        initView();
        initevents();
        Glide.with(this).load(Lists.housesList.get(GOODSPOS).getHouse_img())
                .placeholder(R.drawable.temp).crossFade().into(iv_good_detai_img);

    }

    private void initevents() {

        iv_good_detai_back.setOnClickListener(this);
        rl_good_detail_jin.setOnClickListener(this);
        iv_good_detai_collect_select.setOnClickListener(this);
        iv_good_detai_collect_unselect.setOnClickListener(this);
    }

    private void initView() {
        iv_good_detai_back=(ImageView)findViewById(R.id.iv_good_detai_back);
        iv_good_detai_img=(ImageView)findViewById(R.id.iv_good_detai_img);
        rl_good_detail_jin=(RelativeLayout)findViewById(R.id.rl_good_detail_jin);
        iv_good_detai_collect_unselect=(ImageView)findViewById(R.id.iv_good_detai_collect_unselect);
        iv_good_detai_collect_select=(ImageView)findViewById(R.id.iv_good_detai_collect_select);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_good_detai_back:
                finish();
                break;
            case R.id.rl_good_detail_jin:
                Toast.makeText(mcontext,"朝向",Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_good_detai_collect_select:
                iv_good_detai_collect_unselect.setVisibility(View.VISIBLE);
                iv_good_detai_collect_select.setVisibility(View.GONE);
                break;
            case R.id.iv_good_detai_collect_unselect:
                iv_good_detai_collect_select.setVisibility(View.VISIBLE);
                iv_good_detai_collect_unselect.setVisibility(View.GONE);
                break;
        }
    }
}
