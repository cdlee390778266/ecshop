package com.cnacex.eshop.util;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cnacex.comm.util.Config;

import com.cnacex.comm.util.XmlUtil;
import com.cnacex.eshop.modul.ItemKV;
import com.cnacex.eshop.modul.ItemKV.Item;
import com.cnacex.eshop.modul.ItemKV.Item.Row;
import com.cnacex.eshop.msg.KeymapMsg;

public class KVUtil {
	
	private static Logger logger = LoggerFactory.getLogger(KVUtil.class);
	
	/**
	 * 装载KV对映文件文件
	 * 
	 * @author kereny
	 * @date 2015-6-3 下午3:03:15 void
	 * 
	 */
	protected static void loadXmlFile(String type, Hashtable<String, String> hashtable) {

		InputStream is = Config.class.getResourceAsStream("/conf/keymap.xml");
		try {

			BufferedReader reader = new BufferedReader(new InputStreamReader(is));   
			StringBuilder xmlbuffer = new StringBuilder();   
			String line = null;   

		    while ((line = reader.readLine()) != null) {   
		    	xmlbuffer.append(line);   
		   }   
		    if(xmlbuffer.toString().length() > 0){
		    	KeymapMsg msg = XmlUtil.fromXml(xmlbuffer.toString(), KeymapMsg.class);

				ItemKV itemkvs = msg.getBody();
				if(itemkvs != null ){
					List<Item> items = itemkvs.getItem();
					
					if(items != null){
						for(Item it:items){
							if(type.equalsIgnoreCase(it.getCode()))
							{
								List<Row> rows = it.getRow();
								if(rows != null)
									for(Row row:rows)
									{
										hashtable.put(row.getKey(), row.getValue());
									}
								
								break;
							}
						}
					}
				}
		    }else{
		    	logger.error("加载资源配置文件出错,请检查工程下[/conf/keymap.xml]文件为空");
		    }

		} catch (IOException ex) {
			logger.error("加载资源配置文件出错,请检查工程下[conf/keymap.xml]文件是否存在!!");
			ex.printStackTrace();
		}finally {   
            try {   
                is.close();   
            } catch (IOException e) {   
                e.printStackTrace();   
            }   
        } 
	}
}
