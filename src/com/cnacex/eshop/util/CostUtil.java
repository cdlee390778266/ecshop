package com.cnacex.eshop.util;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cnacex.comm.util.StringUtil;
import com.cnacex.eshop.msg.body.comm.CostPay;

public class CostUtil extends KVUtil {
	

	private static Logger logger = LoggerFactory.getLogger(CostUtil.class);
	
	public static Hashtable<String, String> hashtable = new Hashtable<String, String>();
	
	public static Hashtable<String, String> flaghashtable = new Hashtable<String, String>();

	static {
		loadXmlFile("cost", hashtable);
		
		loadXmlFile("costflag", flaghashtable);
		
		logger.info("加载费用代码类型映射数据个数 {}", hashtable.size());
	}

    
    	/**
         *  根据键得到值
    	 * @author kereny
    	 * @date 2015-6-13 下午1:40:10
    	 * @param key
    	 * @return
    	 * String
         *
    	 */
    public static String getValue(String key)
    {
    	if(StringUtil.nullOrBlank(key)) return null;
    	return hashtable.get(key);
    }
    
    
    
		/**
	     *  根据键得到值
		 * @author kereny
		 * @date 2015-6-13 下午1:40:10
		 * @param key
		 * @return
		 * String
	     *
		 */
	public static String getFlagValue(String key)
	{
		if(StringUtil.nullOrBlank(key)) return null;
		return flaghashtable.get(key);
	}
    
    
    	/**
         *  对费用代码项目列表
    	 * @author kereny
    	 * @date 2015-7-7 下午5:17:17
    	 * @return
    	 * List<CostPay>
         *
    	 */
    public static List<CostPay> getCostList(){
    	
    	List<CostPay> list = new ArrayList<CostPay>();
    	
    	for(Iterator itr = hashtable.keySet().iterator(); itr.hasNext();){
    		String key = (String) itr.next();
    		String value = (String) hashtable.get(key);
    		
    		CostPay cp = new CostPay();
    		cp.setCostCode(key);
    		cp.setCostName(value);
    		list.add(cp);
    	}
    	
    	return list;
    	
    }
	
}
