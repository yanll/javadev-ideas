package com.cmp.common.filter;

import org.apache.commons.lang.time.StopWatch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;

/**
 * Created by YAN on 2015/11/11.
 */
@Aspect
@Component
public class APIExecuteTimeAdvice {
    private static final Log logger = LogFactory.getLog(APIExecuteTimeAdvice.class);

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch clock = new StopWatch();
        clock.start();
        Object result = null;
        String description = null;
        String methodName = null;
        try {
            MethodSignature signature = (MethodSignature) pjp.getSignature();
            Method method = signature.getMethod();
            RequestMapping methodExecTime = method.getAnnotation(RequestMapping.class);
            description = methodExecTime.name();
            String className = method.getDeclaringClass().getName();
            methodName = className + "." + method.getName();
            result = pjp.proceed();
        } catch (Exception e) {
            logger.error(String.format("Log Method Exec Failed.[%s]", e.getMessage()));
        }
        clock.stop();
        logger.info(String.format("%s 执行时间：%s ms [%s]", description, clock.getTime(), methodName));
        return result;
    }


}
