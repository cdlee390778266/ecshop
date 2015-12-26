package com.cnacex.eshop.util;

import java.util.Hashtable;

public class SysStatusUtil extends KVUtil {
	
	
	public static Hashtable<String, String>  hashtable = new Hashtable<String, String>();
	

    static {
    	loadXmlFile("sysstatus", hashtable);
    }
    
	/**
	 *  系统状态描述
	 * @author kereny
	 * @date 2015-6-25 上午9:30:03
	 * @param status
	 * @return
	 * String
	 *
	 */
    
	public static String getSysStatus(String type)
	{
		return hashtable.get(type);
	}

}
