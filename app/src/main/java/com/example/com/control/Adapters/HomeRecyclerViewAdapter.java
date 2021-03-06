package com.example.com.control.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.com.control.R;
import com.example.com.control.ViewHolders.HomeViewHolder;
import com.example.com.control.bean.Houses;

import java.util.List;



public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<Houses> mlist;

    public HomeRecyclerViewAdapter(Context context, List<Houses> list){
        this.context=context;
        this.mlist=list;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context)
                .inflate(R.layout.homerecyclerview_layoutitem,parent,false);
        HomeViewHolder myholder=new HomeViewHolder(view,mOnItemClickListener);
        return myholder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HomeViewHolder holder1=(HomeViewHolder) holder;
        holder1.home_review_item_tv.setText(mlist.get(position).getDescribe());
        Glide.with(context)
                .load(mlist.get(position).getHouse_img())
                .placeholder(R.drawable.temp)
                .crossFade()
                .into(holder1.home_review_item_img);

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
    private OnRecyclerViewItemClickListener mOnItemClickListener=null;


    public interface OnRecyclerViewItemClickListener{
        void onItemClick(View view , int pos);
    }

    public void setHomeRecyclerViewOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
