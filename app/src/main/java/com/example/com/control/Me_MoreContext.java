package com.example.com.control;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.example.com.control.Fragments.Fragments_Me.Bill_Fragment_Me;
import com.example.com.control.Fragments.Fragments_Me.Friend_Fragment_Me;
import com.example.com.control.Fragments.Fragments_Me.Login_Fragment_Me;
import com.example.com.control.Fragments.Fragments_Me.Mydesign_Fragment_Me;
import com.example.com.control.Fragments.Fragments_Me.Publish_Fragment_Me;
import com.example.com.control.Fragments.Fragments_Me.Wallet_Fragment_Me;


public class Me_MoreContext extends FragmentActivity {

    private FrameLayout form_mecontent;

    private Fragment bfm;
    private Fragment wfm;
    private Fragment mfm;
    private Fragment pfm;
    private Fragment ffm;
    private Fragment lfm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.tome_morecontent);

        initview();

        switch (getIntent().getStringExtra("content")){
            case "bill":selectFragment(0);
               break;
            case "wallet":selectFragment(1);
                break;
            case "mydesign":selectFragment(2);
                break;
            case "publish":selectFragment(3);
                break;
            case "friend":selectFragment(4);
                break;
            case "login":selectFragment(5);
                break;


        }
    }

    private void initview() {
        form_mecontent=(FrameLayout)findViewById(R.id.form_me_content);

    }

    private void selectFragment(int pos){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hidefragment(transaction);
        switch (pos){
            case 0:
                if (bfm == null) {
                    bfm = new Bill_Fragment_Me();
                    transaction.add(R.id.form_me_content, bfm);
                } else {
                    transaction.show(bfm);
                }
                break;
            case 1:
                if (wfm == null) {
                    wfm = new Wallet_Fragment_Me();
                    transaction.add(R.id.form_me_content, wfm);
                } else {
                    transaction.show(wfm);
                }
                break;
            case 2:
                if (mfm == null) {
                    mfm = new Mydesign_Fragment_Me();
                    transaction.add(R.id.form_me_content, mfm);
                } else {
                    transaction.show(mfm);
                }
                break;
            case 3:
                if (pfm == null) {
                    pfm = new Publish_Fragment_Me();
                    transaction.add(R.id.form_me_content, pfm);
                } else {
                    transaction.show(pfm);
                }
                break;
            case 4:
                if (ffm == null) {
                    ffm = new Friend_Fragment_Me();
                    transaction.add(R.id.form_me_content, ffm);
                } else {
                    transaction.show(ffm);
                }
                break;
            case 5:
                if (lfm == null) {
                    lfm = new Login_Fragment_Me();
                    transaction.add(R.id.form_me_content, lfm);
                } else {
                    transaction.show(lfm);
                }
                break;

        }
       transaction.commit();
    }

    private void hidefragment(FragmentTransaction transaction) {
        if(bfm!=null){
            transaction.hide(bfm);
        }
        if(wfm!=null){
            transaction.hide(wfm);
        }
        if(mfm!=null){
            transaction.hide(mfm);
        }
        if(pfm!=null){
            transaction.hide(pfm);
        }
        if(ffm!=null){
            transaction.hide(ffm);
        }
        if(lfm!=null){
            transaction.hide(lfm);
        }

    }
}
