package com.example.com.control.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.com.control.R;

/**
 * Created by xiyou3g on 2017/4/23.
 */

public class BillRecyclerViewAdapter_ViewHolder extends RecyclerView.ViewHolder {
    public TextView time_tv;
    public TextView value;
    public TextView doingsthings;
    public BillRecyclerViewAdapter_ViewHolder(View itemView) {
        super(itemView);
        time_tv=(TextView)itemView.findViewById(R.id.time_tv);
        value=(TextView)itemView.findViewById(R.id.value);
        doingsthings=(TextView)itemView.findViewById(R.id.doing_th);
    }
}
