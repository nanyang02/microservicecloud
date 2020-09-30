package com.atguigu.springcloud.common.utils;

public class ObjectUtil {

	public static Byte parseByte(Object Value) {
		if (null == Value) {
			return null;
		} else if (Value instanceof Byte) {
			return (Byte) Value;
		} else if (Value instanceof Number) {
			return ((Number) Value).byteValue();
		} else {
			return Byte.parseByte(parseString(Value));
		}
	}

	public static Short parseShort(Object Value) {
		if (null == Value) {
			return null;
		} else if (Value instanceof Short) {
			return (Short) Value;
		} else if (Value instanceof Number) {
			return ((Number) Value).shortValue();
		} else {
			return Short.parseShort(parseString(Value));
		}
	}

	public static Integer parseInt(Object Value) {
		if (null == Value) {
			return null;
		} else if (Value instanceof Integer) {
			return (Integer) Value;
		} else if (Value instanceof Number) {
			return ((Number) Value).intValue();
		} else {
			return Integer.parseInt(parseString(Value));
		}
	}

	public static Long parseLong(Object Value) {
		if (null == Value) {
			return null;
		} else if (Value instanceof Long) {
			return (Long) Value;
		} else if (Value instanceof Number) {
			return ((Number) Value).longValue();
		} else {
			return Long.parseLong(parseString(Value));
		}
	}

	public static Float parseFloat(Object Value) {
		if (null == Value) {
			return null;
		} else if (Value instanceof Float) {
			return (Float) Value;
		} else if (Value instanceof Number) {
			return ((Number) Value).floatValue();
		} else {
			return Float.parseFloat(parseString(Value));
		}
	}

	public static Double parseDouble(Object Value) {
		if (null == Value) {
			return null;
		} else if (Value instanceof Double) {
			return (Double) Value;
		} else if (Value instanceof Number) {
			return ((Number) Value).doubleValue();
		} else {
			return Double.parseDouble(parseString(Value));
		}
	}

	public static String parseString(Object Value) {
		if (null == Value) {
			return null;
		} else if (Value instanceof String) {
			return (String) Value;
		} else {
			return String.valueOf(Value);
		}
	}

	public static Boolean parseBoolean(Object Value) {
		if (null == Value) {
			return null;
		} else if (Value instanceof Boolean) {
			return (Boolean) Value;
		} else {
			try {
				String strValue = parseString(Value);
				if ("true".equalsIgnoreCase(strValue) || "1".equals(strValue)) {
					return true;
				} else {
					return false;
				}
			} catch (Exception ex) {
				return null;
			}
		}
	}

}
