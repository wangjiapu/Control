package com.example.com.control;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by 蒲家旺 on 2017/4/13.
 *
 * 处理设置事件  一些关于账号安全等问题
 * 清除缓存
 */
public class MeSetting extends Activity implements View.OnClickListener {
    private ImageView back_image;

    private LinearLayout acount_security; //账号与安全
    private LinearLayout news_alerts;    //新消息通知
    private LinearLayout privacy; //隐私
    private LinearLayout common_use; //通用
    private LinearLayout help_feebback; //帮助与反馈
    private LinearLayout about; //关于掌空

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.setting_layout);

        initView();
    }


    private void initView() {

        back_image = (ImageView) findViewById(R.id.back_image);

        acount_security = (LinearLayout) findViewById(R.id.acount_security);
        news_alerts = (LinearLayout) findViewById(R.id.news_alerts);
        privacy = (LinearLayout) findViewById(R.id.privacy);
        common_use = (LinearLayout) findViewById(R.id.common_use);
        help_feebback = (LinearLayout) findViewById(R.id.help_feebback);
        about = (LinearLayout) findViewById(R.id.about);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_image:
                this.finish();
                break;
            case R.id.acount_security:
                Toast.makeText(this, "账号与安全", Toast.LENGTH_SHORT).show();
                break;
            case R.id.news_alerts:
                Toast.makeText(this, "新消息通知", Toast.LENGTH_SHORT).show();
                break;
            case R.id.privacy:
                Toast.makeText(this, "隐私", Toast.LENGTH_SHORT).show();
                break;
            case R.id.common_use:
                Toast.makeText(this, "通用", Toast.LENGTH_SHORT).show();
                break;
            case R.id.help_feebback:
                Toast.makeText(this, "反馈与帮助", Toast.LENGTH_SHORT).show();
                break;
            case R.id.about:
                Toast.makeText(this, "关于掌空", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
