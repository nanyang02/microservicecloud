package com.atguigu.springcloud.common.exceptions;

import com.atguigu.springcloud.common.exceptions.access.AccessTimoutException;
import com.atguigu.springcloud.common.exceptions.access.AuthorizingMissingException;
import com.atguigu.springcloud.common.exceptions.access.InvalidIdentificationException;
import com.atguigu.springcloud.common.exceptions.access.NetworkBlockExcetion;
import com.atguigu.springcloud.common.exceptions.access.IllegalAccessException;
import com.atguigu.springcloud.common.exceptions.argument.*;
import com.atguigu.springcloud.common.exceptions.data.*;

/**
 * 运行异常基类
 */
public class ExecuteFailException extends RuntimeException {
	private static final long serialVersionUID = 2346146212351847974L;
	
	private String mError;

	public ExecuteFailException() {
		this(null);
	}

	public ExecuteFailException(String message) {
		this("ExecuteFail", message);
	}

	protected ExecuteFailException(String error, String message) {
		super(message);
		mError = error;
	}
	
	public String getError() {
		return mError;
	}
	
	public final static ExecuteFailException getInstance(String error, String message) {
		if (null != error) {
			switch (error) {
			
			case "InvalidData": return new InvalidDataException(message);
			case "DependenceNotExist": return new DependenceNotExistException(message);
			case "InvalidDataState": return new InvalidDataStateException(message);
			case "InvalidVerify": return new InvalidVerifyException(message);
			case "RecordAllreadyExist": return new RecordAllreadyExistException(message);
			case "RecordNotExist": return new RecordNotExistException(message);
			case "RelationExist": return new RelationExistException(message);
			
			case "IllegalAccess": return new IllegalAccessException(message);
			case "AccessTimout": return new AccessTimoutException(message);
			case "AuthorizingMissing": return new AuthorizingMissingException(message);
			case "InvalidIdentification": return new InvalidIdentificationException(message);
			case "NetworkBlock": return new NetworkBlockExcetion(message);
			
			default:
				int start = error.indexOf("[");
				if (start > 0) {
					int end = error.lastIndexOf("]");
					if (end > start + 1) {
						String type = error.substring(0, start - 1);
						String variable = error.substring(start + 1, end);
						switch (type) {
						case "InvalidArgument": return new InvalidArgumentException(variable, message);
						case "IllegalDate": return new DateFormatException(variable, message);
						case "IllegalEnum": return new EnumFormatException(variable, message);
						case "IllegalJson": return new JsonFormatException(variable, message);
						case "IllegalNumber": return new MyNumberFormatException(variable, message);
						case "NotNullArgument": return new NotNullArgumentException(variable, message);
						case "OutOfMaxLength": return new StringOutOfMaxLengthException(variable, message);
						case "IllegalText": return new TextFormatException(variable, message);
						default:
						}
					}
				}
			}
		}
		return new ExecuteFailException(message);
	}

}
