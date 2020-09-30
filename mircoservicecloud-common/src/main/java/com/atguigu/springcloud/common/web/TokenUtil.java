package com.atguigu.springcloud.common.web;

import javax.servlet.http.HttpServletRequest;

/**
 * 对于web端,需要从servletRequets中获取客户端的tokan的公共接口
 */
public interface TokenUtil {

    /**
     * 获得当前使用的token令牌的key
     * @return
     */
	public String getTokenKey();

    /**
     * 读取token客户端的请求中
     * @param request
     * @return
     */
	public String readToken(HttpServletRequest request);

}
