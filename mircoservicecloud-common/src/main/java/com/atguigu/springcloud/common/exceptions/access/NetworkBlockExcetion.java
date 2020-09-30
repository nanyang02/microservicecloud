package com.atguigu.springcloud.common.exceptions.access;

/**
 * 链路异常
 */
public class NetworkBlockExcetion extends IllegalAccessException {
	private static final long serialVersionUID = -7009817844221313019L;

	public NetworkBlockExcetion() {
		this(null);
	}

	public NetworkBlockExcetion(String message) {
		this("NetworkBlock", message);
	}

	protected NetworkBlockExcetion(String error, String message) {
		super(error, message);
	}

}
