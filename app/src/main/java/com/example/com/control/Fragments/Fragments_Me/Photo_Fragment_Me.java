package com.example.com.control.Fragments.Fragments_Me;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.com.control.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


import static android.app.Activity.RESULT_OK;

/**
 * Created by xiyou3g on 2017/5/16.
 */

public class Photo_Fragment_Me extends Fragment {
    private LinearLayout setphoto;
    private Dialog mDialog;
    private RelativeLayout takephoto;//拍照
    private RelativeLayout choosephoto;//选择照片
    private Button cancelset;//取消修改照片

    private ImageView xiangpian; //相片

    private Uri Imageuri;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.photofragment_layout, container, false);
        initview(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initEvent();
    }

    private void initEvent() {
        setphoto.setOnClickListener(new OnclickEvent());
    }

    private void initview(View view) {
        setphoto = (LinearLayout) view.findViewById(R.id.setphoto);
        xiangpian = (ImageView) view.findViewById(R.id.xiangpian);
    }

    class OnclickEvent implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.setphoto:
                    mDialog = new Dialog(getActivity(), R.style.ActionSheetDialogStyle);
                    View v = LayoutInflater.from(getContext()).inflate(R.layout.photodialogview, null);
                    takephoto = (RelativeLayout) v.findViewById(R.id.layout_video);
                    choosephoto = (RelativeLayout) v.findViewById(R.id.layout_voice);
                    cancelset = (Button) v.findViewById(R.id.cancel);
                    choosephoto.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            chooseph();
                            Toast.makeText(getContext(), "选择照片", Toast.LENGTH_SHORT).show();
                        }
                    });
                    takephoto.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            takeph();
                            Toast.makeText(getContext(), "不拍了", Toast.LENGTH_SHORT).show();
                        }
                    });

                    cancelset.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(getContext(), "取消", Toast.LENGTH_SHORT).show();
                        }
                    });
                    mDialog.setContentView(v);
                    //获取当前Activity所在的窗体
                    Window dialogWindow = mDialog.getWindow();
                    //设置Dialog从窗体底部弹出
                    dialogWindow.setGravity(Gravity.BOTTOM);
                    //获得窗体的属性
                    WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                    //lp.y= 0;//设置Dialog距离底部的距离
                    //       将属性设置给窗体
                    lp.width = getResources().getDisplayMetrics().widthPixels;
                    dialogWindow.setAttributes(lp);
                    mDialog.show();//显示对话框
                    break;
            }
        }
    }

    /**
     * 调用相册选择照片
     */
    private void chooseph() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else{
            Intent in=new Intent("android.intent.action.GET_CONTENT");
            in.setType("image/*");
            startActivityForResult(in,2);//打开相册
        }
    }


    /**
     * 调用相机拍照
      */
    private void takeph() {
        //创建file对象，用于存储拍照后的照片
        File outputImage = new File(getActivity().getExternalCacheDir(), "output_image.jpg");
        Log.e("outputImage", getActivity().getExternalCacheDir() + "");
        if (outputImage.exists()) {
            outputImage.delete();
        }
        try {
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Build.VERSION.SDK_INT >= 24) {
            Imageuri = FileProvider.getUriForFile(getActivity(), "com.example.com.control", outputImage);

        } else {
            Imageuri = Uri.fromFile(outputImage);
        }
        //启动相机程序
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Imageuri);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getActivity()
                                .getContentResolver().openInputStream(Imageuri));
                        xiangpian.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 2:
                if(resultCode == RESULT_OK){
                    if (Build.VERSION.SDK_INT >= 19){
                        //4.4及以上系统使用这个方法处理图片
                        handleImageOnKitKat(data);
                    }else{
                        //4.4以下系统使用此方法处理
                    }
                }
        }
    }

    private void handleImageOnKitKat(Intent data) {
    }
}
