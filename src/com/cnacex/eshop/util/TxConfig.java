package com.cnacex.eshop.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cnacex.comm.util.Config;

public class TxConfig {

	private static Properties properties = new Properties();

	private static Logger logger = LoggerFactory.getLogger(TxConfig.class);

	private static final String CONFIG_PATH = "/conf/txconfig.properties";

	public TxConfig() {

	}

	/**
	 * 初始化
	 */
	static {
		loadConfigFile();
	}

	/**
	 * 装载配置文件
	 * 
	 * @author kereny
	 * @date 2015-6-3 下午3:03:15 void
	 * 
	 */
	private static void loadConfigFile() {
		InputStream is = Config.class.getResourceAsStream(CONFIG_PATH);
		try {
			properties.load(is);
		} catch (IOException ex) {
			logger.error("加载资源配置文件出错,请检查工程下[WEB-INF/classes/conf/txconfig.properties]文件是否存在!!");
			ex.printStackTrace();
		}
	}

	/**
	 * 返回对映交易码所对映的服务
	 * 
	 * @author kereny
	 * @date 2015-6-3 下午3:03:55
	 * @param key
	 * @return String
	 * 
	 */

	public static String getSvcName(String key) {
		return properties.getProperty(key);
	}

}
