package com.example.com.control.Fragments.Fragments_Me;

import android.media.MediaPlayer;
import android.media.session.MediaController;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.com.control.R;

import java.io.File;


public class Login_Fragment_Me extends Fragment {
    private VideoView videoView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.loginfragment_layout,container,false);
        initView(view);
        initEvent();
        return view;
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
    }

    private void initView(View view) {
        videoView=(VideoView)view.findViewById(R.id.videoview);
    }
}
