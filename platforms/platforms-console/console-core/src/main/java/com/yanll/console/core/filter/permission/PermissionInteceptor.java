package com.yanll.console.core.filter.permission;

import com.yanll.framework.util.exception.BizCode;
import com.yanll.framework.util.exception.BizException;
import com.yanll.framework.util.jackson.UtilJackson;
import com.yanll.framework.web.annotation.Permission;
import com.yanll.framework.web.result.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by YANLL on 14-2-23.
 * 权限控制拦截器（基于Springmvc的URL请求有效）
 * <p>
 * <p>
 * Permission注解默认不配置（受控）
 * 权限定义在b_opertion表，控制器定义在IPermissionEnum枚举
 * <p>
 * <p>
 * <p>
 * 拦截规则：
 * 1、若Controller类注解@Permission(controlled = false)，则该控制器下所有请求放行。
 * 2、若Controller方法注解@Permission(controlled = false)，则该方法对应的请求放行。
 * 3、除1、2外全部拦截。
 */

public class PermissionInteceptor extends HandlerInterceptorAdapter {

    private final Log logger = LogFactory.getLog(PermissionInteceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*if (true) return true;*/
        String controller = "";
        String operation = "";
        String method_type = "";

        if (handler instanceof DefaultServletHttpRequestHandler) return true;//默认静态资源，放行。

        HandlerMethod handler_method = (HandlerMethod) handler;
        if (handler_method.getBean() instanceof BasicErrorController) return true;//默认错误页面，放行。

        Class<?> class_controller = handler_method.getBeanType();
        Permission class_permission = class_controller.getAnnotation(Permission.class);
        Permission method_permission = handler_method.getMethodAnnotation(Permission.class);

        if (null != class_permission && !class_permission.controlled()) return true;//控制器标注非受控，放行。
        if (null != method_permission && !method_permission.controlled()) return true;//方法标注非受控，放行。

        RequestMapping class_request_mapping = class_controller.getAnnotation(RequestMapping.class);
        RequestMapping method_request_mapping = handler_method.getMethodAnnotation(RequestMapping.class);

        if (class_request_mapping.value().length > 0) controller = class_request_mapping.value()[0];
        if (method_request_mapping.value().length > 0) operation = method_request_mapping.value()[0];
        if (method_request_mapping.method().length > 0) method_type = method_request_mapping.method()[0].name();

        String url = controller + operation + ":" + method_type;

        String login_user = (String) request.getSession().getAttribute("user");//登录用户的session信息
        if (login_user != null) {
            Object p = request.getSession().getAttribute("user_permission");//登录用户的权限信息
            if (p != null) {
                Map<String, String> login_user_permissions = (Map<String, String>) p;
                if (login_user_permissions != null && login_user_permissions.get(url) != null) return true;
            }
        }
        throw new BizException(BizCode.PERMISSION_DENIED.getValue(), BizCode.PERMISSION_DENIED.getDesc());
        /*return refuse(response, url);*/
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //记录操作日志
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }


    private boolean refuse(HttpServletResponse response, String url) throws Exception {
        logger.info("PERMISSION_DENIED! target=" + url);
        output(response, UtilJackson.toJSON(new JSON(BizCode.PERMISSION_DENIED.getValue(), BizCode.PERMISSION_DENIED.getDesc())));
        return false;
    }


    private void output(HttpServletResponse response, String msg) throws Exception {
        ServletOutputStream sos = response.getOutputStream();
        sos.write(msg.getBytes());
        sos.close();
    }

}
