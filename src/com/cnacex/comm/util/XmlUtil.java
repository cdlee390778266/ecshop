package com.cnacex.comm.util;



import javax.xml.namespace.QName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.QNameMap;
import com.thoughtworks.xstream.io.xml.StaxDriver;


/**
 * @author kereny
 * 
 * 
 * 
 */

public class XmlUtil {

	static Logger log = LoggerFactory.getLogger(XmlUtil.class);
	
	static String CHARS = "abcdefghijklmnopqrstuvwxyz";


		/**
	     *  返回一个字符串对象<br/>
	     * 
	     * 根据传输对象返回XML字符串
	     * 
		 * @author kereny
		 * @date 2015-6-3 下午3:05:16
		 * @param obj
		 * @return
		 * String
	     *
		 */
	public static String toXml(Object obj) {
		
		QNameMap qmap = new QNameMap();
		qmap.setDefaultPrefix("");
		int rand =  (int)(Math.random() * 26);
		qmap.registerMapping(new QName("http://www.cnacex.com/","root", CHARS.substring(rand, rand+1)), "root");
		StaxDriver staxDriver = new StaxDriver(qmap); 
		staxDriver.setQnameMap(qmap);
		staxDriver.setRepairingNamespace(true);

		XStream xs = new XStream(staxDriver);
		xs.processAnnotations(obj.getClass());
		xs.alias("root",obj.getClass());
		xs.aliasSystemAttribute(null, "class");
		
		String xmlStr = xs.toXML(obj);
		
		log.debug("{}  to xml generated xml:\n{}", obj.getClass()
				.getSimpleName(), xmlStr);
		
		return xmlStr;
	
		
	}


		/**
	     *   返回一个对象 <br/>
	     * 
	     * 根据传输的xml字符串及对象类型返回对映的类对象
	     * 
		 * @author kereny
		 * @date 2015-6-3 下午3:05:41
		 * @param xml
		 * @param cls
		 * @return
		 * T
	     *
		 */
	@SuppressWarnings("unchecked")
	public static <T> T fromXml(String xml, Class<T> cls) {
		log.debug("original xml:\n{}", xml);
		QNameMap qmap = new QNameMap();
		qmap.setDefaultNamespace("http://www.cnacex.com/");
		StaxDriver staxDriver = new StaxDriver(qmap); 
		staxDriver.setQnameMap(qmap);
		XStream xs = new XStream(staxDriver);
		xs.ignoreUnknownElements();
		xs.processAnnotations(cls);
		xs.alias("root",cls);
		xs.aliasSystemAttribute(null, "class");
		
		T obj = (T) xs.fromXML(xml);
		return obj;
	}
	

}
