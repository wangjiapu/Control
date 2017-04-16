package com.example.com.control.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.com.control.Adapters.MlistViewAdapter;
import com.example.com.control.Adapters.MyRecyclerViewAdapter;
import com.example.com.control.R;
import com.example.com.control.bean.Goods;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 蒲家旺 on 2017/4/11.
 *
 */
public class MarketFrament extends Fragment {

    private ListView mlistView;
    private List<String> mlist;//存放主要的类别菜单
    private MlistViewAdapter madapter;//listview 适配器

    private RecyclerView marketRecycerView;
    private List<Goods> goodsinfo;//商品信息列表
    private MyRecyclerViewAdapter radapter;//商品信息适配器；

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
        Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.marketlayout,container,false);

        initview(view);//初始化布局
        initData();//初始化数据
        initEvent();//初始化事件
        return view;
    }

    private void initData() {
        mlist=new ArrayList<>();
        /**
         * 获取物品的分类
         *   暂时使用假数据
         */
        mlist.add("热门推荐");mlist.add("床铺类");mlist.add("桌椅类");mlist.add("文件类");mlist.add("点缀类");
        mlist.add("建材类");mlist.add("电器类");mlist.add("洁具类");mlist.add("贴纸类");mlist.add("贴纸类");
        mlist.add("贴纸类");mlist.add("贴纸类");mlist.add("贴纸类");mlist.add("贴纸类");mlist.add("贴纸类");
        mlist.add("贴纸类");
        mlist.add("贴纸类");mlist.add("贴纸类");mlist.add("贴纸类");mlist.add("贴纸类");mlist.add("贴纸类");

        madapter=new MlistViewAdapter(getContext(),mlist);
        mlistView.setAdapter(madapter);

        goodsinfo=new ArrayList<>();
        /**
         * 获取物品的信息
         * 暂时使用假数据
         */

        for(int i=0;i<31;i++){
            Goods  temp=new Goods();
            temp.setGoodsimage(getResources().getDrawable(R.drawable.markat_back));
            temp.setGoodsname("程序员，如何从平庸走向理想！！！");
            temp.setGoodsvalue("13.14");
            goodsinfo.add(temp);
        }

        GridLayoutManager gm=new GridLayoutManager(getContext(),4);//一行有4个目录
        gm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){

            @Override
            public int getSpanSize(int position) {

                if(position<1){
                    return 4;
                }
                else if(position>2&&position<7){
                    return 1;
                }else{
                    return 2;
                }
            }
        });
        marketRecycerView.setLayoutManager(gm);
        radapter=new MyRecyclerViewAdapter(getContext(),goodsinfo);
        marketRecycerView.setAdapter(radapter);
    }

    private void initEvent() {

        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(),mlist.get(i),Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void initview(View view) {
        mlistView=(ListView)view.findViewById(R.id.listview);
        marketRecycerView=(RecyclerView)view.findViewById(R.id.marketrecycer);
    }

}
