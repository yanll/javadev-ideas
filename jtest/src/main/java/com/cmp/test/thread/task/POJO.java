package com.cmp.test.thread.task;

/**
 * Created by YANLL on 2018/02/08.
 */
public class POJO {


    private Integer index;
    private Integer value;
    private String memo;

    public POJO() {

    }

    public POJO(Integer index, Integer value, String memo) {
        this.index = index;
        this.value = value;
        this.memo = memo;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
