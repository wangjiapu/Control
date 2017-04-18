package com.example.com.control.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.com.control.R;

/**
 * Created by 蒲家旺 on 2017/4/18.
 */

public class HomeViewHolder extends RecyclerView.ViewHolder {
    public ImageView home_review_item_img;
    public TextView home_review_item_tv;
    public HomeViewHolder(View itemView) {
        super(itemView);
        home_review_item_img=(ImageView)itemView.findViewById(R.id.home_review_item_img);
        home_review_item_tv=(TextView)itemView.findViewById(R.id.home_review_item_tv);
    }
}
