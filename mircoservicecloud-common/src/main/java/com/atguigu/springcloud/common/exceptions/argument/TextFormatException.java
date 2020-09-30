package com.atguigu.springcloud.common.exceptions.argument;

public class TextFormatException extends InvalidArgumentException {
	private static final long serialVersionUID = 650094010018379488L;

	public TextFormatException(String variable) {
		this(variable, null);
	}

	public TextFormatException(String variable, String message) {
		this("IllegalText", message, variable);
	}

	protected TextFormatException(String error, String message, String variable) {
		super(error, message, variable);
	}

}
