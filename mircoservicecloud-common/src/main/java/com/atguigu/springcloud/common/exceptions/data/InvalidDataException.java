package com.atguigu.springcloud.common.exceptions.data;

import com.atguigu.springcloud.common.exceptions.ExecuteFailException;

/**
 * 数据异常
 */
public class InvalidDataException extends ExecuteFailException {
	private static final long serialVersionUID = 3442957236313332798L;

	public InvalidDataException() {
		this(null);
	}

	public InvalidDataException(String message) {
		this("InvalidData", message);
	}

	protected InvalidDataException(String error, String message) {
		super(error, message);
	}

}
