package com.example.com.control.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.com.control.R;

public class ViewHplder_match extends RecyclerView.ViewHolder {


    public ImageView goods_img_one;

    public ViewHplder_match(View itemView) {
        super(itemView);
        goods_img_one=(ImageView)itemView.findViewById(R.id.goods_img_one);
    }
}
