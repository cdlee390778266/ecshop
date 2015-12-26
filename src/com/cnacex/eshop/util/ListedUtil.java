package com.cnacex.eshop.util;

import java.util.Hashtable;

public class ListedUtil extends KVUtil {
	
	
	public static Hashtable<String, String>  listedhashtable = new Hashtable<String, String>();
	
	public static Hashtable<String, String>  effrechashtable = new Hashtable<String, String>();
	
	

    static {
    	loadXmlFile("listed", listedhashtable);
    	
    	loadXmlFile("effrec", effrechashtable);
    	
    }
    
	public static String getListedName(String type)
	{
		return listedhashtable.get(type);
	}

	
	public static String getEffRec(int effrec)
	{
		return effrechashtable.get(String.format("%d", effrec));
	}
}
