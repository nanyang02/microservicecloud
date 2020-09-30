package com.atguigu.springcloud.common.servlet;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListResponse extends AbsJsonResponse {
	
	private List<?> data;
	
	private int total;
	
	public ListResponse() {
		super();
		total = 0;
	}

	public ListResponse(List<?> data) {
	    this(data, 0);
    }

	public ListResponse(List<?> data, int total) {
	    super();
	    this.data = data;
	    this.total = total;
    }

	public Object getData() {
		return data;
	}

	public ListResponse setData(List<?> data) {
		this.data = data;
		return this;
	}

	public int getTotal() {
		return total;
	}

	public ListResponse setTotal(int total) {
		this.total = total;
		return this;
	}

	public ListResponse useDataSize() {
	    if (null != this.data) {
	        this.total = data.size();
        }
	    return this;
    }

}
