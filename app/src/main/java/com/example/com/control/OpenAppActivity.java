package com.example.com.control;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.WindowManager;
import android.widget.ImageView;


public class OpenAppActivity extends Activity {
    private ImageView open;

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==1){
                Intent intent=new Intent(OpenAppActivity.this,MainActivity.class);
                OpenAppActivity.this.startActivity(intent);
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        setContentView(R.layout.layout_openapp);

        open=(ImageView)findViewById(R.id.open);
        AnimatorSet animator=new AnimatorSet();
        ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(open,"alpha",0f,1f);
        ObjectAnimator objectAnimator1=ObjectAnimator.ofFloat(open,"scaleX",1f,1.1f);
        ObjectAnimator objectAnimator2=ObjectAnimator.ofFloat(open,"scaleY",1f,1.1f);
        animator.setDuration(2000);
        animator.play(objectAnimator).with(objectAnimator1).with(objectAnimator2);
        animator.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message=new Message();
                try {
                    Thread.sleep(2000);
                    message.what=1;
                    mHandler.sendMessage(message);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
