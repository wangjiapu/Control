package com.example.com.control.Fragments.Fragments_Me;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.com.control.Adapters.BillRecyclerViewAdapter;
import com.example.com.control.R;
import com.example.com.control.bean.Billinfo;

import java.util.ArrayList;
import java.util.List;


public class Bill_Fragment_Me extends Fragment {

    private BillRecyclerViewAdapter myBill_Adapter;
    private RecyclerView billRecyclerview;
    private ImageView back_image;

    private List<Billinfo> mlist;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.billfragment_layout, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        mlist=new ArrayList<>();

        /**
         * 数据从服务器端获取
         */
        for(int i=0;i<30;i++){
            Billinfo temp=new Billinfo();
            temp.setTime("2017年4月");
           if(i%3==0||i%5==0){
               temp.setValue((float) -12.3);temp.setDoing("**转账给你**元");
           }
           else{
               temp.setValue((float) 33.0); temp.setDoing("**给你转账**元");
           }

            mlist.add(temp);
        }
       LinearLayoutManager lm=new LinearLayoutManager(getContext());
        billRecyclerview.setLayoutManager(lm);
        myBill_Adapter=new BillRecyclerViewAdapter(getContext(),mlist);
        billRecyclerview.setAdapter(myBill_Adapter);
    }

    private void initView(View view) {
        billRecyclerview=(RecyclerView)view.findViewById(R.id.bill_recyclerView);
        back_image=(ImageView)view.findViewById(R.id.bill_back);
        back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }

}
