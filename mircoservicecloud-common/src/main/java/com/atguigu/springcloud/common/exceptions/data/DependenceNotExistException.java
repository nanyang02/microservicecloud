package com.atguigu.springcloud.common.exceptions.data;

/**
 * 缺少依赖数据异常
 */
public class DependenceNotExistException extends InvalidDataException {
	private static final long serialVersionUID = -3887381183745177279L;

	public DependenceNotExistException() {
		this(null);
	}

	public DependenceNotExistException(String message) {
		this("DependenceNotExist", message);
	}

	protected DependenceNotExistException(String error, String message) {
		super(error, message);
	}

}
