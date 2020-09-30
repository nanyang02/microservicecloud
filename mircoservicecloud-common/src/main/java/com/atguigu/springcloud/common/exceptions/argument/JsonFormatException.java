package com.atguigu.springcloud.common.exceptions.argument;

/**
 * Json格式解析异常
 */
public class JsonFormatException extends InvalidArgumentException {
	private static final long serialVersionUID = 8811750313673002436L;

	public JsonFormatException(String variable) {
		this(variable, null);
	}

	public JsonFormatException(String variable, String message) {
		this("IllegalJson", message, variable);
	}

	protected JsonFormatException(String error, String message, String variable) {
		super(error, message, variable);
	}

}
