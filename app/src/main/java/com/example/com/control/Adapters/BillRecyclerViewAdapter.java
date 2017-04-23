package com.example.com.control.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.com.control.R;
import com.example.com.control.ViewHolders.BillRecyclerViewAdapter_ViewHolder;
import com.example.com.control.bean.Billinfo;

import java.util.List;

/**
 * Created by xiyou3g on 2017/4/23.
 */

public class BillRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mcontext;
    private List<Billinfo> mlist;

    public BillRecyclerViewAdapter(Context context, List<Billinfo> list) {
        this.mcontext = context;
        this.mlist = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View billview = null;
        BillRecyclerViewAdapter_ViewHolder billholder = null;
        if (viewType == 0) {
            billview = LayoutInflater.from(mcontext).inflate(R.layout.bill_item, parent, false);
            billholder = new BillRecyclerViewAdapter_ViewHolder(billview);
        } else {
            billview = LayoutInflater.from(mcontext).inflate(R.layout.bill_item2, parent, false);
            billholder = new BillRecyclerViewAdapter_ViewHolder(billview);
        }
        return billholder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemtype=getItemViewType(position);
        switch (itemtype){
            case 0:
                BillRecyclerViewAdapter_ViewHolder holder1 = (BillRecyclerViewAdapter_ViewHolder) holder;
                holder1.time_tv.setText(mlist.get(position).getTime());
                holder1.value.setText(String.valueOf(mlist.get(position).getValue()));
                holder1.doingsthings.setText(mlist.get(position).getDoing());
                break;
            case 1:
                BillRecyclerViewAdapter_ViewHolder holder2 = (BillRecyclerViewAdapter_ViewHolder) holder;
                holder2.time_tv.setText(mlist.get(position).getTime());
                holder2.value.setText(String.valueOf(mlist.get(position).getValue()));
                holder2.doingsthings.setText(mlist.get(position).getDoing());
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(mlist.get(position).getValue()<0){
            return 1;
        }
       else{
            return 0;
        }
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
}
