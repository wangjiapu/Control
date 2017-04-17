package com.example.com.control.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.com.control.Adapters.MyRecyclerViewAdapter;
import com.example.com.control.R;

/**
 * Created by xiyou3g on 2017/4/16.
 */
public class ViewHplder_two extends RecyclerView.ViewHolder {
    public ImageView goods_img_two;
    public TextView goods_nm_two;
    private MyRecyclerViewAdapter.OnRecyclerViewItemClickListener
            mOnItemClickListener=null;
    public ViewHplder_two(View itemView,
                          final MyRecyclerViewAdapter.OnRecyclerViewItemClickListener
            mOnItemClickListener) {
        super(itemView);
        goods_img_two=(ImageView)itemView.findViewById(R.id.goods_img_two);
        goods_nm_two=(TextView)itemView.findViewById(R.id.goods_nm_two);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick(v,getPosition());
                }
            }
        });
    }
}
