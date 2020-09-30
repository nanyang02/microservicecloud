package com.atguigu.springcloud.common.exceptions.data;

public class InvalidDataStateException extends InvalidDataException {
	private static final long serialVersionUID = -3187104478785870086L;

	public InvalidDataStateException() {
		this(null);
	}

	public InvalidDataStateException(String message) {
		this("InvalidDataState", message);
	}

	protected InvalidDataStateException(String error, String message) {
		super(error, message);
	}

}
