package com.atguigu.springcloud.common.exceptions.data;

/**
 * 存在关联数据异常
 */
public class RelationExistException extends InvalidDataException {
	private static final long serialVersionUID = 8752131021451913751L;

	public RelationExistException() {
		this(null);
	}

	public RelationExistException(String message) {
		this("RelationExist", message);
	}

	protected RelationExistException(String error, String message) {
		super(error, message);
	}

}
