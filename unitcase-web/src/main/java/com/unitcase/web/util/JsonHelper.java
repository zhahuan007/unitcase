package com.unitcase.web.util;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHelper {
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	static {
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// 默认序列化成该格式，如果有其它格式，请在对应属性上添加注解@JsonFormat(pattern="yyyy-MM-dd")
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	}
	
	private JsonHelper() {
	}
	
	public static String toJson(Object obj) {
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static <T> T fromJson(String s, Class<T> clazz) {
		if (s == null || s.length() == 0) return null;
		try {
			return (T) objectMapper.readValue(s, clazz);
		} catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static<T> T fromJson(String s, TypeReference<T> typeReference) {
		if (s == null || s.length() == 0) return null;
		try {
			return objectMapper.readValue(s, typeReference);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}