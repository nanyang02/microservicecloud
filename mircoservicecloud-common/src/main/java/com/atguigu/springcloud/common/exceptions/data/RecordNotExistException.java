package com.atguigu.springcloud.common.exceptions.data;

/**
 * 请求的数据未找到异常
 */
public class RecordNotExistException extends InvalidDataException {
	private static final long serialVersionUID = 5492931384044151022L;

	public RecordNotExistException() {
		this(null);
	}

	public RecordNotExistException(String message) {
		this("RecordNotExist", message);
	}

	protected RecordNotExistException(String error, String message) {
		super(error, message);
	}

}
