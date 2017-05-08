package com.example.com.control.Utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by xiyou3g on 2017/5/8.
 */

public class OkHttpUtils {

    private static OkHttpClient mOkHttpClient=new OkHttpClient();
    private static final String HomeInfo_Url="http://www.fqybz.cn/Decoration/decoration?action=list";

    /**
     * 返回首页请求信息；
     * @return
     */
    public static Request getHomeInfo_Request(){
        return new Request.Builder().url(HomeInfo_Url).build();
    }

    public static OkHttpClient getmOkHttpClient() {
        return mOkHttpClient;
    }

    public static Request getHomeImg_Request(String img_Url){
        return new Request.Builder().url(img_Url).build();
    }
}
