package com.yanll.framework.util.exception;

public enum BizCode {

    OK(200, "成功！"),
    CREATED(201, "已经创建！"),
    DEFAULT_BIZ_ERROR(202, "业务异常！（此处应该被具体业务代码重写）"),
    BAD_REQUEST(400, "请求失效，请稍后再试！"),
    UNAUTHORIZED(401, "未经授权的操作，请联系系统管理员！"),
    NOT_FOUND(404, "未找到资源，请稍后再试！"),
    METHOD_NOT_ALLOWED(405, "该方法禁止调用，请联系系统管理员！"),
    CONFLICT(409, "操作冲突，请稍后再试！"),
    SESSION_TIMEOUT(410, "用户信息超时，请重新登录！"),
    LOGIN_FAILD(411, "用户名或密码错误！"),
    INVALID_PARAMETER(450, "参数验证未通过！"),
    PERMISSION_DENIED(452, "权限拒绝，请联系系统管理员！"),
    INTERNAL_SERVER_ERROR(500, "服务器内部异常，请稍后再试！"),
    NO_REPLY(558, "服务器无应答，请稍后再试！！"),
    UNCATCHED_EXCEPTION(553, "未捕获的异常，请稍后再试！"),
    ENTITY_NOT_FOUNT(601, "数据未找到，请稍后再试！");


    private Integer value;
    private String desc;

    private BizCode(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    static {

    }


}
