package com.example.com.control.activitys;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.com.control.R;
import com.example.com.control.Utils.Analysis_data_util;
import com.example.com.control.Utils.NetworkUtil;
import com.example.com.control.Utils.OkHttpUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;


public class OpenAppActivity extends Activity {

    private static final int REQUEST_OK = 1;//请求成功
    private static final int REQUEST_NO = 0;//请求失败

    private ImageView open;
    private Context mcontext;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Toast.makeText(mcontext, "网络连接有误", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        setContentView(R.layout.layout_openapp);
        mcontext = this;
        if (NetworkUtil.isNetworkConnection(getApplicationContext())) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final Request request = OkHttpUtils.getHomeInfo_Request();

                    Call call_home = OkHttpUtils.getmOkHttpClient().newCall(request);
                    try {
                        Response response = call_home.execute();
                        Analysis_data_util.HomedataAnalysis(response.body().string(), mcontext);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } else {
            loadData();
        }

        open = (ImageView) findViewById(R.id.open);
        AnimatorSet animator = new AnimatorSet();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(open, "alpha", 0f, 1f);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(open, "scaleX", 1f, 1.1f);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(open, "scaleY", 1f, 1.1f);
        animator.setDuration(2000);
        animator.play(objectAnimator).with(objectAnimator1).with(objectAnimator2);
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Intent intent = new Intent(OpenAppActivity.this, MainActivity.class);
                OpenAppActivity.this.startActivity(intent);
                onDestroy();

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animator.start();
    }

    /**
     * 加载上次浏览时存储在手机中的数据
     */
    private void loadData() {


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
