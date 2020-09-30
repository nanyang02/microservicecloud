package com.atguigu.springcloud.common.utils;

import org.springframework.util.StringUtils;

import java.util.*;

public class SecretUtil {
	
	private final static Comparator<String> mComparator = new Comparator<String>() {

		@Override
		public int compare(String v1, String v2) {
			return v1.toLowerCase().compareTo(v2.toLowerCase());
		}
		
	};
	
	public static String getSign(String security, Map<String, Object> params, long time) {
		StringBuilder result = new StringBuilder();
		result.append(time);

		String content = formatContent(params);
		if (!StringUtils.isEmpty(content)) {
			result.append(content);
		}
		
		result.append(security);
		
		return Tools.SHA1(result.toString());
	}

	@SuppressWarnings("unchecked")
	private static String formatContent(Map<String, Object> params) {
		StringBuilder result = new StringBuilder();
		
		if (null != params && params.size() > 0) {
			ArrayList<String> keys = new ArrayList<String>();
			
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				if (!StringUtils.isEmpty(entry.getValue())) {
					keys.add(entry.getKey());
				}
			}
			
			Collections.sort(keys, mComparator);

			for (int i = 0, s = keys.size(); i < s; i++) {
				String key = keys.get(i);
				Object value = params.get(key);
				if (null == value) {
					continue;
				}
				String content;
				if (value instanceof Map) {
					content = formatContent((Map<String, Object>) value);
				} else if (value instanceof List) {
					content = formatContent((List<Object>) value);
				} else {
					content = String.valueOf(value);
				}
				if (StringUtils.isEmpty(content)) {
					continue;
				}
				result.append(content);
			}
		}
		
		return result.toString();
	}
	
	@SuppressWarnings("unchecked")
	private static String formatContent(List<Object> list) {
		StringBuilder result = new StringBuilder();
		
		if (null != list && list.size() > 0) {
			for (int i = 0, s = list.size(); i < s; i++) {
				Object item = list.get(i);
				if (null == item) {
					continue;
				}
				String content;
				if (item instanceof Map) {
					content = formatContent((Map<String, Object>) item);
				} else if (item instanceof List) {
					content = formatContent((List<Object>) item);
				} else {
					content = String.valueOf(item);
				}
				if (StringUtils.isEmpty(content)) {
					continue;
				}
				result.append(content);
			}
		}
		
		return result.toString();
	}

}
