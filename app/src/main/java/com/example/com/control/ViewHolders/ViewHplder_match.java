package com.example.com.control.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.com.control.Adapters.MyRecyclerViewAdapter;
import com.example.com.control.R;

public class ViewHplder_match extends RecyclerView.ViewHolder {


    public ImageView goods_img_one;
    public MyRecyclerViewAdapter.OnRecyclerViewItemClickListener mOnItemClickListener=null;

    public ViewHplder_match(View itemView,
                            final MyRecyclerViewAdapter.OnRecyclerViewItemClickListener
                                    mOnItemClickListener) {
        super(itemView);
        goods_img_one=(ImageView)itemView.findViewById(R.id.goods_img_one);
        this.mOnItemClickListener=mOnItemClickListener;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick(v,getPosition());
                }
            }
        });
    }
}
