package com.atguigu.springcloud.common.servlet;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MapResponse extends AbsJsonResponse {
	
	private Map<String, Object> data;
	
	public MapResponse() {
		super();
		data = new HashMap<String, Object>();
	}

	public Map<String, Object> getData() {
		return data;
	}

	public MapResponse putData(String key, Object value) {
		data.put(key, value);
        return this;
	}
}
