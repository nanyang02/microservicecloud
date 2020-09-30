package com.atguigu.springcloud.common.exceptions.argument;

import com.atguigu.springcloud.common.exceptions.ExecuteFailException;

/**
 * 非法参数异常
 */
public class InvalidArgumentException extends ExecuteFailException {
	private static final long serialVersionUID = 8792603874302258856L;
	
	private String mVariable;

	public InvalidArgumentException(String variable) {
		this(variable, null);
	}

	public InvalidArgumentException(String variable, String message) {
		this("InvalidArgument", message, variable);
	}

	protected InvalidArgumentException(String error, String message, String variable) {
		super(error, message);
		mVariable = variable;
	}

	@Override
	public String getError() {
		return super.getError() + " [" + mVariable + "]";
	}

}
