package com.atguigu.springcloud.common.exceptions.access;

/**
 * 非法身份信息异常
 */
public class InvalidIdentificationException extends IllegalAccessException {
	private static final long serialVersionUID = 8967715331782762237L;

	public InvalidIdentificationException() {
		this(null);
	}

	public InvalidIdentificationException(String message) {
		this("InvalidIdentification", message);
	}

	protected InvalidIdentificationException(String error, String message) {
		super(error, message);
	}

}
