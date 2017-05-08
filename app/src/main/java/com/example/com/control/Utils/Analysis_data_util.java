package com.example.com.control.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.com.control.R;
import com.example.com.control.bean.Houses;
import com.example.com.control.bean.Lists;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xiyou3g on 2017/5/8.
 * 解析服务器传过来的json数据
 */

public class Analysis_data_util {

    public static void HomedataAnalysis(String datas,Context context){
        Log.e("HomedataAnalysis","Analysis_data_util");
        if(datas.equals("")||datas==null){
            //获取失败，
            Lists.housesList=new ArrayList<>();
            Houses temp=new Houses();
            temp.setDescribe("好房子就是我，我就是好房子");
            Lists.housesList.add(temp);
        }
        else{
            try {
                Lists.housesList=new ArrayList<>();
                JSONArray jsonArray=new JSONArray(datas);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject= (JSONObject) jsonArray.opt(i);
                    Log.e("isHot",jsonObject.getString("isHot"));
                    Log.e("imageName",jsonObject.getString("imageName"));
                    Houses houses=new Houses();
                    houses.setDescribe(jsonObject.getString("content"));
                    houses.setHouse_img(jsonObject.getString("imageName"));
                    Lists.housesList.add(houses);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
