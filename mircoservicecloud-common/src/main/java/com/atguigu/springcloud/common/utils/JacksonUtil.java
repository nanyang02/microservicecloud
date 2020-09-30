package com.atguigu.springcloud.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * json格式转换工具类
 * @当前版本 v1.0
 * @最后修改人 颜黎哲
 * @最后修改时间 2018年11月9日
 */
public class JacksonUtil {
	private static final ObjectMapper mMapper = new ObjectMapper();
	
	private static final TypeReference<Map<String, Object>> mTypeMap = new TypeReference<Map<String, Object>>() {};
	private static final TypeReference<List<Map<String, Object>>> mTypeList = new TypeReference<List<Map<String, Object>>>() {};
	
	static {
		mMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
	}
	
	/**
	 * 格式化一个对象
	 * @param obj 需要格式化的对象
	 * @return 格式化后的文本
	 * @throws JsonProcessingException json格式化异常
	 */
	public static String format(Object obj) throws JsonProcessingException {
		return mMapper.writeValueAsString(obj);
	}

    /**
     * 简化版本的转成json的方法
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        try {
            return mMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	/**
	 * 将一段json内容解析为map格式
	 * @param json json内容
	 * @return map对象
	 * @throws JsonParseException json解析异常
	 * @throws JsonMappingException 对象转换异常
	 * @throws IOException IO异常
	 */
	public static Map<String, Object> parseMap(String json) 
			throws JsonParseException, JsonMappingException, IOException {
		return mMapper.readValue(json, mTypeMap);
	}

     /**
     * 将一段json内容解析为列表格式
     * @param json json内容
     * @return 列表
     * @throws JsonParseException json解析异常
     * @throws JsonMappingException 对象转换异常
     * @throws IOException IO异常
     */
    public static List<Map<String, Object>> parseList(String json)
            throws JsonParseException, JsonMappingException, IOException {
        return mMapper.readValue(json, mTypeList);
    }

    /**
	 * 将一段json内容解析为指定类型的列表
	 * @param json json内容
	 * @param itemType 类型
	 * @return 指定类型的列表
	 * @throws JsonParseException json解析异常
	 * @throws JsonMappingException 对象转换异常
	 * @throws IOException IO异常
	 */
	public static <T> List<T> parseList(String json, Class<T> itemType) 
			throws JsonParseException, JsonMappingException, IOException {
		return mMapper.readValue(json, mMapper.getTypeFactory().constructParametricType(List.class, itemType));
	}
	
	/**
	 * 将一段json内容解析为指定类型
	 * @param json json内容
	 * @param valueType 类型
	 * @return 指定类型的对象
	 * @throws JsonParseException json解析异常
	 * @throws JsonMappingException 对象转换异常
	 * @throws IOException IO异常
	 */
	public static <T> T parse(String json, Class<T> valueType) 
			throws JsonParseException, JsonMappingException, IOException {
		return mMapper.readValue(json, valueType);
	}

}
