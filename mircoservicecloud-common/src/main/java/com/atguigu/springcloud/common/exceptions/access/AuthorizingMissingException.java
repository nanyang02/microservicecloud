package com.atguigu.springcloud.common.exceptions.access;

/**
 * 缺少权限异常
 */
public class AuthorizingMissingException extends IllegalAccessException {
	private static final long serialVersionUID = -5102291353372500L;

	public AuthorizingMissingException() {
		this(null);
	}

	public AuthorizingMissingException(String message) {
		this("AuthorizingMissing", message);
	}

	protected AuthorizingMissingException(String error, String message) {
		super(error, message);
	}

}
