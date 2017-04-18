package com.example.com.control.bean;

import android.graphics.drawable.Drawable;



public class Houses {
    private Drawable house_img;
    private String describe;

    public Drawable getHouse_img() {
        return house_img;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public void setHouse_img(Drawable house_img) {
        this.house_img = house_img;
    }
}
