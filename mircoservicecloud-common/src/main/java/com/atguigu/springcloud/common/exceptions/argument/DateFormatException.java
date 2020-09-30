package com.atguigu.springcloud.common.exceptions.argument;

public class DateFormatException extends InvalidArgumentException {
	private static final long serialVersionUID = 5678239279330714477L;

	public DateFormatException(String variable) {
		this(variable, null);
	}

	public DateFormatException(String variable, String message) {
		this("IllegalDate", message, variable);
	}

	protected DateFormatException(String error, String message, String variable) {
		super(error, message, variable);
	}

}
