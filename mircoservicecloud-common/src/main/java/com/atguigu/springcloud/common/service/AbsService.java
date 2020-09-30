package com.atguigu.springcloud.common.service;

import com.atguigu.springcloud.common.exceptions.ExecuteFailException;
import com.atguigu.springcloud.common.exceptions.argument.NotNullArgumentException;
import com.atguigu.springcloud.common.exceptions.argument.StringOutOfMaxLengthException;
import org.springframework.util.StringUtils;

/**
 * @最后修改人 杨南
 * @最后修改时间 2020/9/30-11:47
 */
public abstract class AbsService {
    protected static void checkNotNull(Object param, String variable) {
        if (null == param) {
            throw new NotNullArgumentException(variable);
        }
    }

    protected static void checkNotNull(Object param, String variable, String message) {
        if (null == param) {
            throw new NotNullArgumentException(variable, message);
        }
    }

    protected static void checkStringNotEmpty(String param, String variable) {
        if (StringUtils.isEmpty(param)) {
            throw new NotNullArgumentException(variable);
        }
    }

    protected static void checkStringNotEmpty(String param, String variable, String message) {
        if (StringUtils.isEmpty(param)) {
            throw new NotNullArgumentException(variable, message);
        }
    }

    protected static void checkStringMaxLength(String param, int length, String variable) {
        if (!StringUtils.isEmpty(param) && param.length() > length) {
            throw new StringOutOfMaxLengthException(variable);
        }
    }

    protected static void checkStringMaxLength(String param, int length, String variable, String message) {
        if (!StringUtils.isEmpty(param) && param.length() > length) {
            throw new StringOutOfMaxLengthException(variable, message);
        }
    }

    protected static void checkStringNotEmptyMaxLength(String param, int length, String variable) {
        if (StringUtils.isEmpty(param)) {
            throw new NotNullArgumentException(variable);
        }
        if (param.length() > length) {
            throw new StringOutOfMaxLengthException(variable);
        }
    }

    protected static void execute(boolean result) {
        if (!result) throw new ExecuteFailException();
    }

    protected static void execute(boolean result, String message) {
        if (!result) throw new ExecuteFailException(message);
    }
}
