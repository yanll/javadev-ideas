package com.yanll.framework.web.filter;

import com.yanll.framework.util.exception.BizCode;
import com.yanll.framework.util.exception.BizException;
import com.yanll.framework.web.result.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author
 * @Description: 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BizException.class)
    @ResponseBody
    public JSON bizExceptionHandler(BizException bizException) {
        JSON result = new JSON();
        result.setCode(bizException.getCode());
        if (bizException.getMsg() != null && bizException.getMsg().length() != 0) {
            result.setDesc(bizException.getMsg());
        }
        logger.error("[" + bizException.getCode() + "," + bizException.getMsg() + "]");
        return result;
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public JSON runtimeExceptionHandler(RuntimeException runtimeException) {
        JSON result = new JSON();
        result.setCode(BizCode.INTERNAL_SERVER_ERROR.getValue());
        result.setDesc(BizCode.INTERNAL_SERVER_ERROR.getDesc());
        String runtime_err = BizCode.INTERNAL_SERVER_ERROR.getDesc();
        if (runtimeException.getMessage() != null && runtimeException.getMessage().length() > 0) {
            runtime_err = runtimeException.getMessage();
        }
        logger.error(BizCode.INTERNAL_SERVER_ERROR.getDesc() + " >> " + runtime_err, runtimeException);
        return result;
    }

}
