package com.atguigu.springcloud.common.exceptions.access;

/**
 * 访问超时异常
 */
public class AccessTimoutException extends IllegalAccessException {
	private static final long serialVersionUID = -7486817660410563559L;

	public AccessTimoutException() {
		this(null);
	}

	public AccessTimoutException(String message) {
		this("AccessTimout", message);
	}

	protected AccessTimoutException(String error, String message) {
		super(error, message);
	}

}
