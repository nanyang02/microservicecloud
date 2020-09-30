package com.atguigu.springcloud.common.servlet;

import com.atguigu.springcloud.common.exceptions.argument.JsonFormatException;
import com.atguigu.springcloud.common.utils.JacksonUtil;
import com.atguigu.springcloud.common.utils.Tools;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RequestData {
	private final static Log log = LogFactory.getLog(RequestData.class);

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	private boolean decode = false;
	
	public void setDecode(boolean decode) {
		this.decode = decode;
	}

	private String data;

	public Map<String, Object> getData() {
		String data;
		if (StringUtils.isEmpty(this.data)) {
			data = null;
		} else {
			if (decode) {
				data = Tools.base64Decode(this.data);
			} else {
				data = this.data;
			}
		}
		if(StringUtils.isEmpty(data)) {
			return null;
		} else {
			try {
				return JacksonUtil.parseMap(data);
			} catch(Exception ex) {
				log.debug(ex);
				throw new JsonFormatException("data");
			}
		}
	}

	public <T> T getData(Class<T> type) {
		String data;
		if (StringUtils.isEmpty(this.data)) {
			data = null;
		} else {
			if (decode) {
				data = Tools.base64Decode(this.data);
			} else {
				data = this.data;
			}
		}
		if(StringUtils.isEmpty(data)) {
			return null;
		} else {
			try {
				return JacksonUtil.parse(data, type);
			} catch(Exception ex) {
				log.debug(ex);
				throw new JsonFormatException("data");
			}
		}
	}

	public void setData(String data) {
		this.data = data;
	}

}
