package com.atguigu.springcloud.common.exceptions.data;

public class InvalidVerifyException extends InvalidDataException {
	private static final long serialVersionUID = 6003059045043262147L;

	public InvalidVerifyException() {
		this(null);
	}

	public InvalidVerifyException(String message) {
		this("InvalidVerify", message);
	}

	protected InvalidVerifyException(String error, String message) {
		super(error, message);
	}

}
