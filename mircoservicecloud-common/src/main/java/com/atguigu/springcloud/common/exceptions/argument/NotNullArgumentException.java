package com.atguigu.springcloud.common.exceptions.argument;

/**
 * 缺少必要参数异常
 */
public class NotNullArgumentException extends InvalidArgumentException {
	private static final long serialVersionUID = -4625737669516581556L;

	public NotNullArgumentException(String variable) {
		this(variable, null);
	}

	public NotNullArgumentException(String variable, String message) {
		this("NotNullArgument", message, variable);
	}

	protected NotNullArgumentException(String error, String message, String variable) {
		super(error, message, variable);
	}

}
