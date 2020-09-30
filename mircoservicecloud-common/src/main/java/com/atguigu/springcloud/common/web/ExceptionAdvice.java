package com.atguigu.springcloud.common.web;

import com.atguigu.springcloud.common.exceptions.ExecuteFailException;
import com.atguigu.springcloud.common.utils.JacksonUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.NestedRuntimeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.HashMap;

// 增强controller,这个能够实现全局的异常的捕获的处理
// 我们的返回的响应中装填值,特征关键字等都是可以在这里面处理和扩充.
@ControllerAdvice
public class ExceptionAdvice {
    private static final Log log = LogFactory.getLog(ExceptionAdvice.class);

    @ResponseBody
    // Spring的@ExceptionHandler可以用来统一处理方法抛出的异常
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception ex) {

        // 最终的数据map集
        HashMap<String, Object> result = new HashMap<String, Object>();

        // 设置异常时返回的信息的状态为false
        result.put("success", false);

        //
        Exception cause = ex;

        // 原因分析,根据不同原因抛出的cause不同
        if (cause instanceof UndeclaredThrowableException) {
            cause = (Exception) ((UndeclaredThrowableException) cause).getUndeclaredThrowable();
        }

        if (cause instanceof NestedRuntimeException) {
            cause = (Exception) ((NestedRuntimeException) cause).getRootCause();
        }

        // 如果是执行失败的异常,则会专门的处理,这个会提示错误的消息的原因,其实这个ExecuteFailException就是我们自定义异常的
        // 总的入口,所以,对于出现自定义抛出的异常,统一到这里进行处理,设置错误状态,设置状态的消息
        if (cause instanceof ExecuteFailException) {
            result.put("error", ((ExecuteFailException) cause).getError());
            result.put("message", cause.getMessage());
        } else {

            // 非自定义异常抛出的情况
            result.put("error", ex.getMessage());
            log.warn(ex);
        }

        // 最后的打印一下日志
        log.info("Result >>" + JacksonUtil.toJson(result));
        return result;
    }

}