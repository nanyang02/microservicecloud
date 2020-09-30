package com.atguigu.springcloud.common.web;

import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class TokenUtilImpl implements TokenUtil {
	
	private String mTokenKey = "token";

    TokenUtilImpl () {
        super();
    }

    /**
     * 可以是自定义token关键字的
     * @param token
     */
    TokenUtilImpl (String token) {
        super();
    }

	@Override
	public String getTokenKey() {
		return mTokenKey;
	}

	public void setTokenKey(String tokenKey) {
		mTokenKey = tokenKey;
	}

	@Override
	public String readToken(HttpServletRequest request) {
		if (null == request) {
			return null;
		}
		String result = request.getHeader(mTokenKey);
		String sessionId = null;
		if (StringUtils.isEmpty(result)) {
			Cookie[] cookies = request.getCookies();
			if (null != cookies && cookies.length > 0) {
				for (int i = 0; i < cookies.length; i++) {
					Cookie cookie = cookies[i];
					if (mTokenKey.equals(cookie.getName())) {
						result = cookie.getValue();
						// jsessionid就是用来判断当前用户对应于哪个session。换句话说服务器识别session的方法是通过jsessionid来告诉服务器该客户端的session在内存的什么地方
					} else if ("JSESSIONID".equalsIgnoreCase(cookie.getName())) {
						sessionId = cookie.getValue();
					}
				}
			}
		}
		if (StringUtils.isEmpty(result)) {
			if (StringUtils.isEmpty(sessionId)) {
				return request.getSession().getId();
			} else {
				return sessionId;
			}
		} else {
			return result;
		}
	}

}
