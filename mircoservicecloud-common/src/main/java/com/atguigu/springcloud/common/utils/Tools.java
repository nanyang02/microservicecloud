package com.atguigu.springcloud.common.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.UUID;

public class Tools {
	private final static Log log = LogFactory.getLog(Tools.class);

	public static String generateUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static Byte formatBooleanValue(Boolean value) {
		if (null == value) {
			return null;
		} else if (value) {
			return 1;
		} else {
			return 0;
		}
	}

	public static Boolean formatBinaryValue(Byte value) {
		if (null == value) {
			return null;
		} else if (value == 1) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean equals(String v1, String v2) {
		if (StringUtils.isEmpty(v1)) {
			if (StringUtils.isEmpty(v2)) {
				return true;
			} else {
				return false;
			}
		} else {
			if (StringUtils.isEmpty(v2)) {
				return false;
			} else {
				return v1.equals(v2);
			}
		}
	}

	public static double sum(double num1, double num2) {
		BigDecimal bd1 = new BigDecimal(num1);
		BigDecimal bd2 = new BigDecimal(num2);
		return bd1.add(bd2).doubleValue();
	}

	public static double sum(double num1, double num2, double num3) {
		BigDecimal bd1 = new BigDecimal(num1);
		BigDecimal bd2 = new BigDecimal(num2);
		BigDecimal bd3 = new BigDecimal(num3);
		return bd1.add(bd2).add(bd3).doubleValue();
	}

	public static double sum(double num1, double num2, double num3, double num4) {
		BigDecimal bd1 = new BigDecimal(num1);
		BigDecimal bd2 = new BigDecimal(num2);
		BigDecimal bd3 = new BigDecimal(num3);
		BigDecimal bd4 = new BigDecimal(num4);
		return bd1.add(bd2).add(bd3).add(bd4).doubleValue();
	}

	public static double subtract(double num1, double num2) {
		BigDecimal bd1 = new BigDecimal(num1);
		BigDecimal bd2 = new BigDecimal(num2);
		return bd1.subtract(bd2).doubleValue();
	}

	public static double divide(double num1, double num2) {
		return divide(num1, num2, null);
	}

	public static double divide(double num1, double num2, Integer scale) {
		if (num2 == 0) {
			throw new IllegalArgumentException("The divider must be a non-zero value");
		}
		BigDecimal bd1 = new BigDecimal(num1);
		BigDecimal bd2 = new BigDecimal(num2);
		if (null == scale) {
			return bd1.divide(bd2, BigDecimal.ROUND_HALF_UP).doubleValue();
		} else {
			return bd1.divide(bd2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
		}
	}

	public static double multiply(double num1, double num2) {
		return multiply(num1, num2, -1);
	}

	public static double multiply(double num1, double num2, int scale) {
		BigDecimal bd1 = new BigDecimal(num1);
		BigDecimal bd2 = new BigDecimal(num2);
		if (scale < 0) {
			return bd1.multiply(bd2).doubleValue();
		} else {
			return bd1.multiply(bd2).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
		}
	}

	public static double round(double num, int scale) {
		return new BigDecimal(num).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static Method findMethod(Class<?> OwnerClass, String MethodName, Class<?>[] ArgsClass)
			throws ClassNotFoundException {
		if (null == OwnerClass) {
			return null;
		}
		for (Class<?> objectClass = OwnerClass; objectClass != Object.class; objectClass = objectClass
				.getSuperclass()) {
			try {
				return objectClass.getDeclaredMethod(MethodName, ArgsClass);
			} catch (Exception ex) {
				log.debug(ex);
			}
		}
		throw new ClassNotFoundException();
	}
	
	private final static Base64 mBase64 = new Base64();
	
	public static String base64Encode(String code) {
		if (!StringUtils.isEmpty(code)) {
			try {
				byte[] input = code.getBytes("UTF-8");
				byte[] output = mBase64.encode(input);
				return new String(output, "UTF-8");
			} catch(Exception ex) {
				log.warn(ex);
			}
		}
		return null;
	}
	
	public static String base64Decode(String text) {
		if (!StringUtils.isEmpty(text)) {
			try {
				byte[] input = text.getBytes("UTF-8");
				byte[] output = mBase64.decode(input);
				return new String(output, "UTF-8");
			} catch(Exception ex) {
				log.warn(ex);
			}
		}
		return null;
	}

	private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	public static String MD5(String text) {
		return MD5(text, DEFAULT_CHARSET);
	}

	public static String MD5(String text, Charset charset) {
		return encode("md5", text.getBytes(charset));
	}

	public static String SHA1(String text) {
		return SHA1(text, DEFAULT_CHARSET);
	}

	public static String SHA1(String text, Charset charset) {
		return encode("sha1", text.getBytes(charset));
	}

	private static final char[] HEX_DIGITS = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b',
			'c', 'd', 'e', 'f' };

	private static String encode(String algorithm, byte[] source) {
		try {
			MessageDigest cal = MessageDigest.getInstance(algorithm);
			cal.update(source);
			byte tmp[] = cal.digest();
			StringBuilder result = new StringBuilder(tmp.length * 2);
			for (int i = 0; i < tmp.length; i++) {
				result.append(HEX_DIGITS[tmp[i] >>> 4 & 0xf]);
				result.append(HEX_DIGITS[tmp[i] & 0xf]);
			}
			return result.toString();
		} catch (Exception Ex) {
			return null;
		}
	}

}
