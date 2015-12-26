package com.cnacex.eshop.util;

import java.util.Calendar;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.cnacex.eshop.service.ITradeService;

public class DBPropertyUtil {
	
	public static boolean bIsLoad = false;
	
	public static int loadDate = 0;
	
	public static HashMap<String, String> configs;
	
	
	private synchronized void getRemoteCostProperty()
	{
		configs = new HashMap<String, String>();
		

		
		Calendar c = Calendar.getInstance();

		int date = c.get(Calendar.YEAR)*10000 +  c.get(Calendar.MONTH)*100 + c.get(Calendar.DATE); 
		
		bIsLoad = true;
		loadDate = date;
		
	}
	
	public String getCostPropertyName(String code)
	{
		Calendar c = Calendar.getInstance();

		int date = c.get(Calendar.YEAR)*10000 +  c.get(Calendar.MONTH)*100 + c.get(Calendar.DATE); 
		

		if(!bIsLoad || configs.isEmpty() || loadDate != date)
			getRemoteCostProperty();
		
		return configs.get(code);
	}
	
}
