package com.example.com.control.Fragments.Fragments_Me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.com.control.R;

/**
 * Created by xiyou3g on 2017/4/15.
 */
public class Mydesign_Fragment_Me extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mydesignfragment_layout,container,false);
    }
}
