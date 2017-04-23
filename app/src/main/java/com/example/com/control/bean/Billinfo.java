package com.example.com.control.bean;

/**
 * Created by xiyou3g on 2017/4/23.
 */

public class Billinfo {
    private Float value;//金钱的支出与收入
    private String doing;//消费事项
    private String time;//事件发生的时间

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDoing() {
        return doing;
    }



    public void setDoing(String doing) {
        this.doing = doing;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }
}
