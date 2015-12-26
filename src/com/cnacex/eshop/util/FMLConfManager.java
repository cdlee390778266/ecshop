/**
 * 
 */
package com.cnacex.eshop.util;

import java.util.zip.CRC32;

import com.cnacex.comm.util.Config;
import com.cnacex.eshop.msg.AbstractMsg;

/**
 * @author Administrator
 *
 */
public class FMLConfManager {
	
	/** 协议版本 报文的协议版本号，便于不同版本解析兼容 **/
	public static final int PHF_VER = Config.getIntValue("PHF_VER");
	
	
	/** 请求方系统号 请求方系统编号，见数据字典公共部分 **/
	public static final String PHF_REQSYS = Config.getValue("PHF_REQSYS");
	
	
	/** 请求方节点号  **/
	public static final String PHF_REQNODE = Config.getValue("PHF_REQNODE");

		
	private  static int reqno = 99999999; 
	
		/**
	     *  生成系统请求号
		 * @author kereny
		 * @date 2015-6-10 上午10:10:55
		 * @return
		 * int
	     *
		 */
	public static  synchronized int buildREQNO()
	{
		reqno--;
		if(reqno < 10000000)
			reqno = 99999999; 
		return reqno;
	}
	
	
		/**
	     *  生成串的CRC32校验码
		 * @author kereny
		 * @date 2015-6-10 上午10:11:20
		 * @param str
		 * @return
		 * int
	     *
		 */
	public static int getCRC32(String str)
	{
		CRC32 crc32 = new CRC32();
		crc32.update(str.getBytes());
		return (int)(crc32.getValue()&0x7FFFFFFF);
	}

}
