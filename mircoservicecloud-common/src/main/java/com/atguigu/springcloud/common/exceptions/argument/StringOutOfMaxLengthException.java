package com.atguigu.springcloud.common.exceptions.argument;

public class StringOutOfMaxLengthException extends InvalidArgumentException {
	private static final long serialVersionUID = -6888676084113382738L;

	public StringOutOfMaxLengthException(String variable) {
		this(variable, null);
	}

	public StringOutOfMaxLengthException(String variable, String message) {
		this("OutOfMaxLength", message, variable);
	}

	protected StringOutOfMaxLengthException(String error, String message, String variable) {
		super(error, message, variable);
	}

}
