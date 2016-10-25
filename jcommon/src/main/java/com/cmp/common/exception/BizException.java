package com.cmp.common.exception;

public class BizException extends RuntimeException {

    private static final long serialVersionUID = -4752674736396848193L;

    private int code;
    private String msg;

    public BizException(int code) {
        super();
        this.code = code;
    }

    public BizException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public BizException(int code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
