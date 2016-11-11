package com.yanll.framework.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: YAN
 * Date: 14-3-15
 * Time: 下午5:57
 * To change this template use File | Settings | File Templates.
 */
public class SQLBuilder {


    private List<Object> args = null;
    private StringBuffer sql = null;

    public SQLBuilder(String sql) {
        this.sql = new StringBuffer(sql);
    }

    public void append(String exp) {
        this.sql.append(exp);
    }

    public void append(String exp, Object value) {
        if (this.args == null) {
            this.args = new ArrayList<>();
        }
        this.sql.append(exp);
        this.args.add(value);
    }

    public void append(String exp, Object... values) {
        if (this.args == null) {
            this.args = new ArrayList<Object>();
        }
        this.sql.append(exp);
        for (Object v : values) {
            this.args.add(v);
        }
    }

    @Override
    public String toString() {
        return this.sql.toString();
    }

    public <T extends Object> T[] args() {
        if (args == null) {
            return null;
        }
        return (T[]) this.args.toArray();
    }


}
