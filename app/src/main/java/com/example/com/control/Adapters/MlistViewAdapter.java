package com.example.com.control.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.com.control.R;

import java.util.List;

public class MlistViewAdapter extends BaseAdapter{

    private Context context;
    private List<String> mlist;

    public MlistViewAdapter(Context context, List<String> list){
        this.context=context;
        this.mlist=list;
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int i) {
        return mlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Holder holder=null;
        if (view==null){
            holder=new Holder();
            view=View.inflate(context, R.layout.listview_item,null);
            holder.textView1=(TextView)view.findViewById(R.id.list_it);
            view.setTag(holder);
        }
        else{
            holder=(Holder)view.getTag();//不为空直接设置
        }
        holder.textView1.setText(mlist.get(i));

        return view;
    }

    class Holder{  //控件的类
        TextView textView1;
    }

}
