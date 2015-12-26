package com.cnacex.eshop.util;

import java.util.Hashtable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StatusUtil extends KVUtil{
	
	private static Logger logger = LoggerFactory.getLogger(StatusUtil.class);
	
	public static Hashtable<String, String> sellhashtable = new Hashtable<String, String>();
	
	public static Hashtable<String, String> buyhashtable = new Hashtable<String, String>();
	
	
	public static Hashtable<String, String> contracthashtable = new Hashtable<String, String>();
	
	
    static {
    	loadXmlFile("sellstatus", sellhashtable);
    	
    	logger.info("加载销售状态映射数据个数 {}", sellhashtable.size());
    	
    	loadXmlFile("buystatus", buyhashtable);
    	
    	logger.info("加载采购状态映射数据个数 {}", buyhashtable.size());
    	
    	loadXmlFile("contractstatus", contracthashtable);
    	
    	logger.info("加载合同状态映射数据个数 {}", contracthashtable.size());
    }
    
    
    
    
    	/**
         *  买状态代码转描述
    	 * @author kereny
    	 * @date 2015-6-25 上午9:30:03
    	 * @param status
    	 * @return
    	 * String
         *
    	 */
    public static String getBuyStatus(int status)
    {
    	return buyhashtable.get(String.format("%d", status));
    }

    	/**
         *  卖状态代码转描述
    	 * @author kereny
    	 * @date 2015-6-25 上午9:30:33
    	 * @param status
    	 * @return
    	 * String
         *
    	 */
    public static String getSellStatus(int status)
    {
    	return sellhashtable.get(String.format("%d", status));
    }

		/**
	     *  合同状态代码转描述
		 * @author kereny
		 * @date 2015-6-25 上午9:30:33
		 * @param status
		 * @return
		 * String
	     *
		 */
	public static String getContractStatus(int status)
	{
		return contracthashtable.get(String.format("%d", status));
	}

    
}
