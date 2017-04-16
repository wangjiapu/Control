package com.example.com.control.bean;

import android.graphics.drawable.Drawable;

/**
 * Created by xiyou3g on 2017/4/16.
 */
public class Goods {

    private Drawable goodsimage;//商品图片
    private String goodsname;//商品名字
    private String goodsvalue;//商品价格；

    public Drawable getGoodsimage() {
        return goodsimage;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public String getGoodsvalue() {
        return goodsvalue;
    }

    public void setGoodsimage(Drawable goodsimage) {
        this.goodsimage = goodsimage;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public void setGoodsvalue(String goodsvalue) {
        this.goodsvalue = goodsvalue;
    }
}
