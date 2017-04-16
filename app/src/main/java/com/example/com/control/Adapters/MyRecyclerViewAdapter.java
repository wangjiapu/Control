package com.example.com.control.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.com.control.R;
import com.example.com.control.ViewHolders.ViewHplder_four;
import com.example.com.control.ViewHolders.ViewHplder_match;
import com.example.com.control.ViewHolders.ViewHplder_two;
import com.example.com.control.bean.Goods;

import java.util.List;



public class MyRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Goods> list;

    public MyRecyclerViewAdapter(Context context,List<Goods> mlist){
        this.context=context;
        this.list=mlist;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=null;
        RecyclerView.ViewHolder holder=null;


        switch (viewType){
            case 0:
                view= LayoutInflater.from(context).inflate(R.layout.holder_lauout_match,null);
                holder=new ViewHplder_match(view);
                break;
            case 1:
                view=LayoutInflater.from(context).inflate(R.layout.holder_lauout_two,null);
                holder=new ViewHplder_two(view);
                break;
            case 2:
                view=LayoutInflater.from(context).inflate(R.layout.holder_lauout_four,null);
                holder=new ViewHplder_four(view);
                break;
        }

       
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        int itemtype=getItemViewType(position);
        switch (itemtype){
            case 0:
                ViewHplder_match vh1= (ViewHplder_match) holder;
                vh1.goods_img_one.setImageDrawable(list.get(position).getGoodsimage());
                break;
            case 1:
                ViewHplder_two vh2= (ViewHplder_two) holder;
                vh2.goods_img_two.setImageDrawable(list.get(position).getGoodsimage());
                vh2.goods_nm_two.setText(list.get(position).getGoodsname());
                break;
            case 2:
                ViewHplder_four vh3= (ViewHplder_four) holder;
                vh3.goods_image.setImageDrawable(list.get(position).getGoodsimage());
                vh3.goods_name.setText(list.get(position).getGoodsname());
                vh3.goods_value.setText(list.get(position).getGoodsvalue());
                break;
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position < 1) {
            return 0;
        } else if (3 <= position && position < 7) {
            return 1;
        } else return 2;
    }

    /**
     * 下面是有关点击事件的一些东西
     */

    private OnRecyclerViewItemClickListener mOnItemClickListener=null;




    public static interface OnRecyclerViewItemClickListener{
        void onItemClick(View view , int pos);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
