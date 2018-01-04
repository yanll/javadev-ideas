package com.cmp.test.rmi.facade;

import java.io.Serializable;

/**
 * Created by YANLL on 2017/12/27.
 */
public class UserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
