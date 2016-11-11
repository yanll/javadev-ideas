package com.yanll.framework.web.result;


import java.util.Date;

/**
 * Created by YAN on 2015/10/27.
 */
public class JSON<T> {


    private int code;
    private String desc = "";
    private T data;
    private Date datetime = new Date();

    public JSON() {

    }

    public JSON(int code) {
        this.code = code;
    }

    public JSON(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public JSON(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public JSON(int code, String desc, T data) {
        this.code = code;
        this.desc = desc;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
