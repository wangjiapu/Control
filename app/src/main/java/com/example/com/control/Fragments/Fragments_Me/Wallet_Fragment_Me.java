package com.example.com.control.Fragments.Fragments_Me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.com.control.R;

/**
 * Created by xiyou3g on 2017/4/15.
 *  实现用户的账目管理
 */
public class Wallet_Fragment_Me extends Fragment{
    private LinearLayout backlinear; //返回上一层
    private TextView mingxi;    //账目明细
    private LinearLayout chongzhi;   //充值
    private LinearLayout tixian;     //提现
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View walletview=inflater.inflate(R.layout.walletfragment_layout,container,false);
        initview(walletview);
        return walletview;
    }

    private void initview(View view) {
        backlinear=(LinearLayout)view.findViewById(R.id.wallet_back);
        mingxi=(TextView)view.findViewById(R.id.mingxi);
        chongzhi=(LinearLayout)view.findViewById(R.id.chongzhi);
        tixian=(LinearLayout)view.findViewById(R.id.tixian);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init_Wallet_Event();
    }

    private void init_Wallet_Event() {
        backlinear.setOnClickListener(new onclickevent());
        mingxi.setOnClickListener(new onclickevent());
        chongzhi.setOnClickListener(new onclickevent());
        tixian.setOnClickListener(new onclickevent());
    }

    class onclickevent implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.mingxi:
                    Toast.makeText(getContext(),"明细",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.chongzhi:
                    Toast.makeText(getContext(),"充值",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tixian:
                    Toast.makeText(getContext(),"提现",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.wallet_back:
                    getActivity().finish();
                    break;

            }
        }
    }
}
