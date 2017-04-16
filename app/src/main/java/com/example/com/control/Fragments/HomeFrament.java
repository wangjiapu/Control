package com.example.com.control.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.com.control.R;
import com.example.com.control.Views.Carousel3DSwitchView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 蒲家旺 on 2017/4/11.
 *
 */
public class HomeFrament extends Fragment {
    private static final Integer START=1;
    private Carousel3DSwitchView carousel3DSwitchView;
    private Timer timer=new Timer();//初始话定时器  方便轮播图；


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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.homelayout,container,false);

        initView(view);
        initEvent(view);
        return view;
    }

    private void initEvent(View view) {
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
    }

    private void initView(View view) {
        carousel3DSwitchView=(Carousel3DSwitchView)view.findViewById(R.id.image_switch_view);

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
    }
}
