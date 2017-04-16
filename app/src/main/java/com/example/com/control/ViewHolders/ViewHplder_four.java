package com.example.com.control.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.com.control.R;


public class ViewHplder_four extends RecyclerView.ViewHolder {
    public ImageView goods_image;
    public TextView goods_name;
    public TextView goods_value;
    public ViewHplder_four(View itemView) {
        super(itemView);
        goods_image= (ImageView) itemView.findViewById(R.id.goods_img);
        goods_name=(TextView)itemView.findViewById(R.id.goods_nm);
        goods_value=(TextView)itemView.findViewById(R.id.goods_vl);
    }
}
