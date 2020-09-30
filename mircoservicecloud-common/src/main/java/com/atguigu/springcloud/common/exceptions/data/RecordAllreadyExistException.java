package com.atguigu.springcloud.common.exceptions.data;

/**
 * 记录已存在异常
 */
public class RecordAllreadyExistException extends InvalidDataException {
	private static final long serialVersionUID = 6382580233259120255L;

	public RecordAllreadyExistException() {
		this(null);
	}

	public RecordAllreadyExistException(String message) {
		this("RecordAllreadyExist", message);
	}

	protected RecordAllreadyExistException(String error, String message) {
		super(error, message);
	}

}
