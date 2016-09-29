package com.cmp.test.factory;

/**
 * Created by YLL on 2015/07/28.
 */
public class Dao implements IDao {
    @Override
    public void save(String id) {
        System.out.println("save:" + id);
    }
}
