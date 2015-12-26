package com.cnacex.eshop.util;

import java.util.Hashtable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cnacex.comm.util.StringUtil;

public class ActTypeUtil extends KVUtil {
	
	private static Logger logger = LoggerFactory.getLogger(AcctTrUtil.class);

	public static Hashtable<String, String> hashtable = new Hashtable<String, String>();

	static {
		loadXmlFile("acttype", hashtable);
		
		logger.info("加载账户类型映射数据个数 {}", hashtable.size());
	}


	/**
	 * 根据键得到值
	 * 
	 * @author kereny
	 * @date 2015-6-13 下午1:40:10
	 * @param key
	 * @return String
	 * 
	 */
	public static String getValue(String key) {
		if (StringUtil.nullOrBlank(key))
			return null;
		return hashtable.get(key);
	}
}
