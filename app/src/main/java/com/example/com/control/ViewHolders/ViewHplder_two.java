package com.example.com.control.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.com.control.R;

/**
 * Created by xiyou3g on 2017/4/16.
 */
public class ViewHplder_two extends RecyclerView.ViewHolder {
    public ImageView goods_img_two;
    public TextView goods_nm_two;
    public ViewHplder_two(View itemView) {
        super(itemView);

        goods_img_two=(ImageView)itemView.findViewById(R.id.goods_img_two);
        goods_nm_two=(TextView)itemView.findViewById(R.id.goods_nm_two);
    }
}
