
package com.cnacex.comm.wtc;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weblogic.wtc.gwt.TuxedoConnection;
import weblogic.wtc.gwt.TuxedoConnectionFactory;
import weblogic.wtc.jatmi.TPException;


/**
 * @author kereny
 *
 */
public class WtcConnect {

	static Logger logger = LoggerFactory.getLogger(WtcConnect.class);
	
	static Context ctx;
	static TuxedoConnectionFactory tcf;
	
	static {  
		try {
			ctx = new InitialContext();
			tcf = (TuxedoConnectionFactory) ctx.lookup("tuxedo.services.TuxedoConnection");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}  
	

		/**
	     *  取一个TUXEDO连接，从TUXEDO的FACTORY中取一个连接
	     *  
		 * @author kereny
		 * @date 2015-6-3 下午3:06:53
		 * @return
		 * TuxedoConnection
	     *
		 */
	public static TuxedoConnection getConnection()
    {
		TuxedoConnection conn = null;
		try {
			conn = tcf.getTuxedoConnection();
		} catch (TPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
    }
	
}
