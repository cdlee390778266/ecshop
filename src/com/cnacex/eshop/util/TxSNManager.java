package com.cnacex.eshop.util;


/**
 * @author kereny
 *
 */
public class TxSNManager {
	
	public  static int txsn = 100000000; 
	

		/**
	     *  生成请求流水号
		 * @author kereny
		 * @date 2015-6-10 上午10:11:56
		 * @return
		 * String
	     *
		 */
	public static  synchronized String BuildTxSN()
	{
		txsn++;
		if(txsn >= 1000000000)
			txsn = 100000000; 
		return String.format("%9d", txsn);
	}


}
