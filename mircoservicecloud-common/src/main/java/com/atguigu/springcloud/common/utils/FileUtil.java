package com.atguigu.springcloud.common.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;

/**
 * 文件公用方法
 * @最后修改人 颜黎哲
 * @最后修改时间 2018年11月7日
 */
public class FileUtil {
	private static final Log log = LogFactory.getLog(FileUtil.class);
	
	public static final int BUFFER_SIZE = 1024 * 1024;
	
	public static String getFileName(String path) {
		int index1 = path.lastIndexOf("\\");
		int index2 = path.lastIndexOf("/");
		if(index1 < 0 && index2 < 0) {
			return path.trim();
		} else if(index1 > index2) {
			return path.substring(index1 + 1).trim();
		} else {
			return path.substring(index2 + 1).trim();
		}
	}
	
	public static String getFileExtention(String name) {
		int index = name.lastIndexOf(".");
		if(index < 0) {
			return "";
		} else {
			return name.substring(index).toLowerCase();
		}
	}

	/**
	 * 将数据流保存到文件中
	 * @param stream 数据流
	 * @param file 将要存储到的文件
	 * @return 保存结果，成功或失败
	 */
	public static boolean saveFile(InputStream stream, File file) {
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		try {
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			
			byte[] buffer = new byte[BUFFER_SIZE];
			
			int bytes = stream.read(buffer);
			while(bytes != -1) {
				bos.write(buffer, 0, bytes);
				bytes = stream.read(buffer);
			}
			bos.flush();
			return true;
		} catch(IOException ex) {
			log.warn(ex);
		} finally {
			try {
				if(null != fos) {
					fos.close();
				}
			} catch(Exception ex) {
				log.debug(ex);
			}
			try {
				if(null != bos) {
					bos.close();
				}
			} catch(IOException ex) {
				log.debug(ex);
			}
		}
		return false;
	}

	/**
	 * 从文件中读取数据流
	 * @param source 文件源
	 * @return 数据流
	 */
	public static InputStream readFile(String source) {
		try {
			File file = new File(source);
			if (file.exists()) {
				FileInputStream fis = new FileInputStream(file);
				BufferedInputStream bis = new BufferedInputStream(fis);
				
				return bis;
			}
		} catch(Exception ex) {
			log.warn(ex);
		}
		return null;
	}

	/**
	 * 删除文件
	 * @param source 文件源
	 * @return 删除结果，成功或失败
	 */
	public static boolean deleteFile(String source) {
		try {
			File file = new File(source);
			if (file.exists()) {
				return file.delete();
			}
		} catch(Exception ex) {
			log.warn(ex);
		}
		return false;
	}
	
}
