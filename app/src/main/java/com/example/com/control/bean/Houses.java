package com.example.com.control.bean;

public class Houses {
    private String house_imgURL;
    private String describe;

    public String getHouse_img() {
        return house_imgURL;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public void setHouse_img(String house_img) {
        this.house_imgURL = house_img;
    }
}
