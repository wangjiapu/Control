package com.example.com.control.Fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.com.control.activitys.MeSetting;
import com.example.com.control.activitys.Me_MoreContext;
import com.example.com.control.R;

/**
 * Created by 蒲家旺 on 2017/4/11.
 *
 */
public class MeFrament extends Fragment{
    private static final String MEFRAMENT="REGISTERBORAD";
    private String user="";
    private String password="";
    private TextView setting_tv;
    private TextView outlogin;
    private ImageView message,message2;
    private ImageView userPhoto;//用户照片

    private TextView myname,mynum;

    private LinearLayout bill,wallet,mydesign,publish,friend,inviet;
    private LinearLayout login;//用户未登录时的界面呈现效果
    private LinearLayout person;//用户登录成功时的界面呈现效果

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    download_data();
                    break;
            }
        }
    };

    private BroadcastReceiver mBroadcastReceiver=new BroadcastReceiver(){

        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getStringExtra("result");
            if(action.equals("跟新ui")){
                //发送handler
                Message m=new Message();
                m.what=1;
                mHandler.sendMessage(m);
            }else{
                //不更新
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.melayout,container,false);
        init_Me_View(view);
        registerBoradcastReceiver();//注册广播
        download_data();//加载用户信息；
        return view;
    }



    private void download_data() {
        SharedPreferences pref=getActivity()
                .getSharedPreferences("userData",Context.MODE_PRIVATE);
           user=pref.getString("username","");
        password=pref.getString("password","");
        Log.e("password",password);
        if(user.equals("")||password.equals("")){
            login.setVisibility(View.VISIBLE);
            person.setVisibility(View.GONE);
        }else{
            myname.setText(user);
            mynum.setText(String.valueOf("账号："+password));
            person.setVisibility(View.VISIBLE);
            login.setVisibility(View.GONE);
        }
    }

    private void registerBoradcastReceiver() {
        IntentFilter mIntentFilter=new IntentFilter();
        mIntentFilter.addAction(MEFRAMENT);
        getActivity().registerReceiver(mBroadcastReceiver,mIntentFilter);//注册
    }

    private void init_Me_Event() {
        setting_tv.setOnClickListener(new onClickEvent());
        message.setOnClickListener(new onClickEvent());
        outlogin.setOnClickListener(new onClickEvent());

        bill.setOnClickListener(new onClickEvent());
        wallet.setOnClickListener(new onClickEvent());
        mydesign.setOnClickListener(new onClickEvent());
        publish.setOnClickListener(new onClickEvent());
        friend.setOnClickListener(new onClickEvent());
        inviet.setOnClickListener(new onClickEvent());
        login.setOnClickListener(new onClickEvent());
        person.setOnClickListener(new onClickEvent());
        userPhoto.setOnClickListener(new onClickEvent());
    }

    private void init_Me_View(View view) {
        setting_tv=(TextView)view.findViewById(R.id.setting);
        outlogin=(TextView)view.findViewById(R.id.outlogin);
        myname=(TextView)view.findViewById(R.id.myname);
        mynum=(TextView)view.findViewById(R.id.mynum);
        message=(ImageView)view.findViewById(R.id.message);
        message2=(ImageView)view.findViewById(R.id.message2);//消息来了时，切换到有消息的view

        bill=(LinearLayout)view.findViewById(R.id.bill);//账单
        wallet=(LinearLayout)view.findViewById(R.id.wallet);//钱包
        mydesign=(LinearLayout)view.findViewById(R.id.mydesign);//我的设计
        publish=(LinearLayout)view.findViewById(R.id.publish);//我的发布
        friend=(LinearLayout)view.findViewById(R.id.friend);//我的关注
        inviet=(LinearLayout)view.findViewById(R.id.inviet);//邀请好友

        login=(LinearLayout)view.findViewById(R.id.nologin);//未登录状态
        person=(LinearLayout)view.findViewById(R.id.person);//登录状态
        userPhoto=(ImageView)view.findViewById(R.id.userphoto);
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init_Me_Event();
    }


    /**
     * 处理事件的点击
     */
    class onClickEvent implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.setting:
                    Intent setting_intent=new Intent(getContext(),MeSetting.class);
                    getContext().startActivity(setting_intent);
                    break;
                case R.id.message:
                    Toast.makeText(getContext(),"消息没有来",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.inviet:

                    /**
                     * 实现分享功能，分享我们的app给好友
                     */
                    Intent intent_share=new Intent(Intent.ACTION_SEND);
                    intent_share.setType("image/*");
                    intent_share.putExtra(Intent.EXTRA_SUBJECT, "Share");
                    intent_share.putExtra(Intent.EXTRA_TEXT, "掌空app是一个很好的应用，你可以试试");
                    intent_share.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(Intent.createChooser(intent_share,"掌空app" ));
                    break;

                default:
                    Intent intent=new Intent(getContext(),Me_MoreContext.class);
                    switch (v.getId()){
                        case R.id.bill:
                            intent.putExtra("content","bill");
                            break;
                        case R.id.wallet:
                            intent.putExtra("content","wallet");
                            break;
                        case R.id.mydesign:
                            intent.putExtra("content","mydesign");
                            break;
                        case R.id.publish:
                            intent.putExtra("content","publish");
                            break;
                        case R.id.friend:
                            intent.putExtra("content","friend");
                            break;
                        case R.id.nologin:
                        case R.id.outlogin:
                            intent.putExtra("content","login");
                            break;
                        case R.id.person:
                            intent.putExtra("content","photo");
                            break;

                    }
                    getContext().startActivity(intent);

            }

        }
    }
}
