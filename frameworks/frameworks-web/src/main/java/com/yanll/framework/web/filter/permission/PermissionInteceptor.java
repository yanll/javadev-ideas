package com.yanll.framework.web.filter.permission;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
 * 3、若Controller类或方法没有配置RequestMapping，说明非Springmvc的URL请求，放行。
 */

public class PermissionInteceptor extends HandlerInterceptorAdapter {

    private final Log logger = LogFactory.getLog(PermissionInteceptor.class);

//    @Autowired
//    IPermissionService permissionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*if (true) return true;*/
        HttpSession session = request.getSession();
        String controller = "";
        String operation = "";
        //默认静态资源，放行。
        if (handler instanceof DefaultServletHttpRequestHandler) return true;
        HandlerMethod handler_method = (HandlerMethod) handler;
        Permission method_permission = handler_method.getMethodAnnotation(Permission.class);
        if (null != method_permission) {
            //方法标注非受控，放行。
            if (!method_permission.controlled()) return true;
        }
        Class<?> class_controller = handler_method.getMethod().getDeclaringClass();
        Permission class_permission = class_controller.getAnnotation(Permission.class);
        if (null != class_permission) {
            //控制器标注非受控，放行。
            if (!class_permission.controlled()) return true;
        }
        RequestMapping class_request_mapping = class_controller.getAnnotation(RequestMapping.class);
        //控制器未定义RequestMapping，放行。
        if (null == class_request_mapping) return true;
        if (class_request_mapping.value().length > 0) {
            controller = class_request_mapping.value()[0];
        }
        RequestMapping method_request_mapping = handler_method.getMethodAnnotation(RequestMapping.class);
        //方法未定义RequestMapping，放行。
        if (null == method_request_mapping) return true;
        if (method_request_mapping.value().length > 0) {
            operation = method_request_mapping.value()[0];
        }
        String url = controller + operation;
        if ("/account/to_login".equals(url)) return true;
        String BASE_PATH = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

        Object res = session.getAttribute(PermissionResource.Const.LOGIN_USER);
        if (res == null) {
            response.sendRedirect(BASE_PATH + "/account/to_login");
            return false;
        }
//        Map<String, String> user_permissions = (Map<String, String>) session.getAttribute(PermissionResource.Const.USER_PERMISSIONS);
//        if (user_permissions != null) {
//            if (user_permissions.get(url) != null) {
//                //记录操作日志
//                if (!Strings.isNullOrEmpty(url)) {
//                    UserBean user = (UserBean) res;
//                    permissionService.savePv(user, url, UtilHttp.serializeParams(request));
//                }
//                return true;
//            }
//        }
//        logger.info("Access refused! target=" + url);
//        //Ajax请求输出错误信息
//        String XRequestedWith = request.getHeader("X-Requested-With");
//        if ("XMLHttpRequest".equals(XRequestedWith)) {
//            output(response, UtilJackson.toJSON(new JSON(7002)));
//            return false;
//        }
//        response.sendRedirect(BASE_PATH + "/permission/permission_refused?target=" + url);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }


    private void output(HttpServletResponse response, String msg) throws Exception {
        ServletOutputStream sos = response.getOutputStream();
        sos.write(msg.getBytes());
        sos.close();
    }

}
