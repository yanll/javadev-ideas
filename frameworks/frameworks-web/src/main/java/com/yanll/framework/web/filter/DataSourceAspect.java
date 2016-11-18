package com.yanll.framework.web.filter;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by YAN on 2015/12/13.
 */

@Aspect
@Component
public class DataSourceAspect {


    private static final Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);

    /*
    @Before("execution(* com.cmp..mappers.*.*(..))")
    public void before(JoinPoint point) {
        Object target = point.getTarget();
        String method = point.getSignature().getName();
        Class<?>[] classz = target.getClass().getInterfaces();
        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
        try {
            Method m = classz[0].getMethod(method, parameterTypes);
            String methodName = m.getName();
            if (m != null && m.isAnnotationPresent(DataSource.class)) {
                DataSource data = m.getAnnotation(DataSource.class);
                HandleDataSource.putDataSource(data.value());
            } else {
                if (methodName.startsWith("w")) {
                    HandleDataSource.putDataSource("w");
                }
            }
        } catch (Exception e) {
            logger.error("DynamicDataSource set up error while HandleDataSource.putDataSource...", e);
        }
    }
    */
}