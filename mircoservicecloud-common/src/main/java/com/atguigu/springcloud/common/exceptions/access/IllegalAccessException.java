package com.atguigu.springcloud.common.exceptions.access;


import com.atguigu.springcloud.common.exceptions.ExecuteFailException;

/**
 * 非法访问异常
 */
public class IllegalAccessException extends ExecuteFailException {
	private static final long serialVersionUID = 2784644359049634472L;

	public IllegalAccessException() {
		this(null);
	}

	public IllegalAccessException(String message) {
		this("IllegalAccess", message);
	}

	protected IllegalAccessException(String error, String message) {
		super(error, message);
	}

}
