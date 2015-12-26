package com.cnacex.comm.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/**
 * @author Administrator
 * 
 */
public class StringUtil {

		/**
	     *  判断是否为空串
	     *  
		 * @author kereny
		 * @date 2015-6-3 下午3:00:14
		 * @param param
		 * @return
		 * boolean
	     *
		 */
	public static boolean nullOrBlank(String param) {
		return (param == null || param.length() == 0 || param.trim().equals("")) ? true
				: false;
	}



		/**
	     *  去空格并将NULL转成""
	     *  
		 * @author kereny
		 * @date 2015-6-3 下午3:00:47
		 * @param param
		 * @return
		 * String
	     *
		 */
	public static String notNull(String param) {
		return param == null ? "" : param.trim();
	}


		/**
	     *  是否为浮点型数
	     *  
		 * @author kereny
		 * @date 2015-6-3 下午3:01:25
		 * @param str
		 * @return
		 * boolean
	     *
		 */
	public static boolean isFloat(String str) {
		int num;
		int j = 0;
		if (StringUtil.nullOrBlank(str))
			return false;

		for (int i = 0; i < str.length(); i++) {
			num = (int) str.charAt(i);
			if (num > 47 && num < 57)
				continue;

			if (num == 46) {
				j++;
			}
			if (j > 1) {
				return false;
			}
		}
		return true;

	}

		/**
	     *  是否为一个数字串
	     *  
		 * @author kereny
		 * @date 2015-6-3 下午3:01:55
		 * @param str
		 * @return
		 * boolean
	     *
		 */
	public static boolean isNumber(String str) {
		int num = 0;
		if (str == null)
			return false;
		for (int i = 0; i < str.length(); i++) {
			num = (int) str.charAt(i);
			if (num < 48 || num > 57)
				return false;
		}
		return true;
	}
	

		/**
	     *  加密串按某方法
		 * @author kereny
		 * @date 2015-6-3 下午3:02:16
		 * @param string
		 * @param method
		 * @return
		 * String
	     *
		 */
	public static String encrypt(String string, String method) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = string.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance(method);
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

}
