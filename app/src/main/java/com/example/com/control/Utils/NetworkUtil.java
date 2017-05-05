package com.example.com.control.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by xiyou3g on 2017/5/5.
 */

public class NetworkUtil {
    public static boolean isNetworkConnection(Context context){
        if(context!=null){
            ConnectivityManager connectivityManager= (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
            if (networkInfo!=null){
                return networkInfo.isAvailable();
            }
        }
        return false;
    }
}
