package com.example.com.control.Fragments.Fragments_Me;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.session.MediaController;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.com.control.R;

import java.io.File;


public class Login_Fragment_Me extends Fragment {


    private VideoView videoView;
    private EditText username;//用户名
    private EditText pwd;//用户密码
    private Button loginbt;//登录按钮

    private TextView forgetpwd;//忘记密码
    private TextView registered;//注册新用户

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.loginfragment_layout,container,false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initEvent();
    }

    private void initEvent() {

        String path=getContext().getExternalCacheDir().getAbsolutePath();
        path=path.split("Android")[0];
        File file=new File(path+"DCIM/Video/1223.MP4");

        final Uri uri = Uri.parse(file.getAbsolutePath());
        if (!file.exists()) {
            Toast.makeText(getContext(), "视频文件路径错误", Toast.LENGTH_SHORT).show();

        }else {

            final android.widget.MediaController mp=new android.widget.MediaController(getContext());
            mp.setVisibility(View.INVISIBLE);
            videoView.setMediaController(mp);

            videoView.setClickable(false);
            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    videoView.setVideoURI(uri);
                    videoView.start();
                }
            });
            videoView.setVideoURI(uri);
            videoView.start();
        }
        //username.setOnClickListener(new OnclickEvent_login());
       // pwd.setOnClickListener(new OnclickEvent_login());
        loginbt.setOnClickListener(new OnclickEvent_login());
        forgetpwd.setOnClickListener(new OnclickEvent_login());
        registered.setOnClickListener(new OnclickEvent_login());
    }

    private void initView(View view) {
        videoView=(VideoView)view.findViewById(R.id.videoview);
        username=(EditText)view.findViewById(R.id.username);
        pwd=(EditText)view.findViewById(R.id.password);
        loginbt=(Button)view.findViewById(R.id.loginbt);
        forgetpwd=(TextView)view.findViewById(R.id.forgetpwd);
        registered=(TextView)view.findViewById(R.id.registered);
    }

    class OnclickEvent_login implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.loginbt:
                    /**
                     * 判断用户输入密码是否为name:test，pwd:123456
                     */
                    String userStr=username.getText().toString().trim();
                    String pwdStr=pwd.getText().toString().trim();
                    if(userStr==null||userStr.equals("")){
                        Toast.makeText(getContext(),"用户名不能为空",Toast.LENGTH_SHORT).show();
                    }else if(pwdStr==null||pwdStr.equals("")){
                        Toast.makeText(getContext(),"请输入密码",Toast.LENGTH_SHORT).show();
                    }else{
                        if(userStr.equals("test")&& pwdStr.equals("123456")){
                           //登录成功，保存数据并退出此界面
                            SharedPreferences.Editor editor=getActivity()
                                    .getSharedPreferences("userData", Context.MODE_PRIVATE).edit();
                            editor.putString("username",userStr);
                            editor.putString("password",pwdStr);

                            editor.apply();
                            //通知主界面刷新ui
                            Intent mIntent=new Intent("REGISTERBORAD");
                            mIntent.putExtra("result","跟新ui");
                            getActivity().sendBroadcast(mIntent);
                            getActivity().finish();

                        }else{
                            Toast.makeText(getContext(),"输入有误！",Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                case R.id.forgetpwd:
                    /**
                     * 忘记密码时使用
                     */
                    Toast.makeText(getContext(),"请继续关注！",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.registered:
                    /**
                     * 注册新用户时使用；
                     */
                    Toast.makeText(getContext(),"请不要着急！",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
