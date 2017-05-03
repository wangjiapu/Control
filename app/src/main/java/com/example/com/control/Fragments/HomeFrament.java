package com.example.com.control.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.com.control.Adapters.HomeRecyclerViewAdapter;
import com.example.com.control.R;
import com.example.com.control.Views.Carousel3DSwitchView;
import com.example.com.control.bean.Houses;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 蒲家旺 on 2017/4/11.
 *
 */
public class HomeFrament extends Fragment{
    private static final Integer START=1;
    private Carousel3DSwitchView carousel3DSwitchView;
    private Timer timer=new Timer();//初始话定时器  方便轮播图；

    private RecyclerView homeRecyclerView;
    private List<Houses> housesList;
    private HomeRecyclerViewAdapter madapter;


    private Button label_hot;//热门标签
    private Button label_discount;//促销标签
    private Button label_near;//附近标签
    private Button label_buy;  //预购

    private Button label_beautiful;//最美
    private Button label_convenient;//便利
    private Button label_suburb;//郊区
    private Button label_town;//市区



    private Handler mhandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    carousel3DSwitchView.scrollToNext();
                    break;
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.homelayout,container,false);

        initView(view);
        initDate();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initEvent();
    }

    private void initDate() {
        housesList=new ArrayList<>();
        for(int i=0;i<100;i++){
            /**
             * 初始化数据，从服务器获取
             */
            Houses temp=new Houses();
            if(i%3==1){
                temp.setHouse_img(getResources().getDrawable(R.drawable.imag1));
            }
            if(i%3==2){
                temp.setHouse_img(getResources().getDrawable(R.drawable.imag2));
            }else {
                temp.setHouse_img(getResources().getDrawable(R.drawable.imag3));
            }
            temp.setDescribe("好房子就是我，我就是好房子");
            housesList.add(temp);
        }
        GridLayoutManager gm=new GridLayoutManager(getContext(),2);
        homeRecyclerView.setLayoutManager(gm);
        madapter=new HomeRecyclerViewAdapter(getContext(),housesList);
        homeRecyclerView.setAdapter(madapter);

    }

    private void initEvent() {
        carousel3DSwitchView.setOnImageOnclickLister(new Carousel3DSwitchView.OnImageonclickLister() {
            @Override
            public void onImageOnclick(int currentImage) {
                /**
                 * 点击事件的书写；
                 * 点击以后进入详细信息页面；
                 */

                Toast.makeText(getActivity(),
                        "这是第"+currentImage+"条信息",Toast.LENGTH_SHORT).show();
            }
        });
        label_hot.setOnClickListener(new MyonClick());
        label_discount.setOnClickListener(new MyonClick());
        label_near.setOnClickListener(new MyonClick());
        label_beautiful.setOnClickListener(new MyonClick());
        label_convenient.setOnClickListener(new MyonClick());
        label_suburb.setOnClickListener(new MyonClick());
        label_town.setOnClickListener(new MyonClick());
        label_buy.setOnClickListener(new MyonClick());
    }

    private void initView(View view) {
        carousel3DSwitchView=(Carousel3DSwitchView)view.findViewById(R.id.image_switch_view);

        label_hot=(Button)view.findViewById(R.id.hot);
        label_discount=(Button)view.findViewById(R.id.discount);
        label_near=(Button)view.findViewById(R.id.near);
        label_buy=(Button)view.findViewById(R.id.buy);
        label_beautiful=(Button)view.findViewById(R.id.beautiful);
        label_convenient=(Button)view.findViewById(R.id.convenient);
        label_suburb=(Button)view.findViewById(R.id.suburb);
        label_town=(Button)view.findViewById(R.id.town);

        label_hot.setBackground(getResources().getDrawable(R.drawable.homelabel_bgcolor2));
        label_hot.setTextColor(getResources().getColor(R.color.WHITE));
        /**
         * 初始化计时器，让轮播图开始播放
         */
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message=new Message();
                message.what=START;
                message.arg1=(carousel3DSwitchView.getmCurrentImage()+1)%
                        (carousel3DSwitchView.getmCount());
                mhandler.sendMessage(message);
            }
        },2000,6000);

        homeRecyclerView=(RecyclerView)view.findViewById(R.id.home_recyclerview);

    }

    class MyonClick implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.hot:
                    hide_coler();
                    label_hot.setBackground(getResources().getDrawable(R.drawable.homelabel_bgcolor2));
                    label_hot.setTextColor(getResources().getColor(R.color.WHITE));
                    break;
                case R.id.discount:
                    hide_coler();
                    label_discount.setBackground(getResources().getDrawable(R.drawable.homelabel_bgcolor2));
                    label_discount.setTextColor(getResources().getColor(R.color.WHITE));
                    break;
                case R.id.near:
                    hide_coler();
                    label_near.setBackground(getResources().getDrawable(R.drawable.homelabel_bgcolor2));
                    label_near.setTextColor(getResources().getColor(R.color.WHITE));
                    break;
                case R.id.buy:
                    hide_coler();
                    label_buy.setBackground(getResources().getDrawable(R.drawable.homelabel_bgcolor2));
                    label_buy.setTextColor(getResources().getColor(R.color.WHITE));
                    break;
                case R.id.beautiful:
                    hide_coler();
                    label_beautiful.setBackground(getResources().getDrawable(R.drawable.homelabel_bgcolor2));
                    label_beautiful.setTextColor(getResources().getColor(R.color.WHITE));
                    break;
                case R.id.convenient:
                    hide_coler();
                    label_convenient.setBackground(getResources().getDrawable(R.drawable.homelabel_bgcolor2));
                    label_convenient.setTextColor(getResources().getColor(R.color.WHITE));
                    break;
                case R.id.suburb:
                    hide_coler();
                    label_suburb.setBackground(getResources().getDrawable(R.drawable.homelabel_bgcolor2));
                    label_suburb.setTextColor(getResources().getColor(R.color.WHITE));
                    break;
                case R.id.town:
                    hide_coler();
                    label_town.setBackground(getResources().getDrawable(R.drawable.homelabel_bgcolor2));
                    label_town.setTextColor(getResources().getColor(R.color.WHITE));
                    break;

            }
        }
    }



    /**
     * 隐藏所有标签的颜色
     */
    private void hide_coler() {
        label_hot.setBackground(getResources().getDrawable(R.drawable.homelabel_bgcoloc));
        label_discount.setBackground(getResources().getDrawable(R.drawable.homelabel_bgcoloc));
        label_near.setBackground(getResources().getDrawable(R.drawable.homelabel_bgcoloc));
        label_buy.setBackground(getResources().getDrawable(R.drawable.homelabel_bgcoloc));
        label_beautiful.setBackground(getResources().getDrawable(R.drawable.homelabel_bgcoloc));
        label_convenient.setBackground(getResources().getDrawable(R.drawable.homelabel_bgcoloc));
        label_suburb.setBackground(getResources().getDrawable(R.drawable.homelabel_bgcoloc));
        label_town.setBackground(getResources().getDrawable(R.drawable.homelabel_bgcoloc));
        label_hot.setTextColor(getResources().getColor(R.color.label_bg_no));
        label_discount.setTextColor(getResources().getColor(R.color.label_bg_no));
        label_near.setTextColor(getResources().getColor(R.color.label_bg_no));
        label_buy.setTextColor(getResources().getColor(R.color.label_bg_no));
        label_beautiful.setTextColor(getResources().getColor(R.color.label_bg_no));
        label_convenient.setTextColor(getResources().getColor(R.color.label_bg_no));
        label_suburb.setTextColor(getResources().getColor(R.color.label_bg_no));
        label_town.setTextColor(getResources().getColor(R.color.label_bg_no));
    }


}
