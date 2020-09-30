package com.atguigu.springcloud.common.exceptions.argument;

public class MyNumberFormatException extends InvalidArgumentException {
	private static final long serialVersionUID = 5190282833704823438L;

	public MyNumberFormatException(String variable) {
		this(variable, null);
	}

	public MyNumberFormatException(String variable, String message) {
		this("IllegalNumber", message, variable);
	}

	protected MyNumberFormatException(String error, String message, String variable) {
		super(error, message, variable);
	}

}
