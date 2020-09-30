package com.atguigu.springcloud.common.exceptions.argument;

public class EnumFormatException extends InvalidArgumentException {
	private static final long serialVersionUID = -8426841637723286520L;

	public EnumFormatException(String variable) {
		this(variable, null);
	}

	public EnumFormatException(String variable, String message) {
		this("IllegalEnum", message, variable);
	}

	protected EnumFormatException(String error, String message, String variable) {
		super(error, message, variable);
	}

}
