package com.cnacex.eshop.service;


import com.cnacex.eshop.msg.body.query.HisQueryReq;
import com.cnacex.eshop.msg.xml.query.HisQueryRspMsg;


/**
 * @author kereny
 *
 */
public interface IQueryService {
	

		/**
	     *  查询历史成交单
		 * @author kereny
		 * @date 2015-7-25 下午12:03:39
		 * @param sellHisQueryReq
		 * @return
		 * HisQueryRspMsg
	     *
		 */
	public abstract HisQueryRspMsg queryHisList(HisQueryReq hisQueryReq, String bsType);
	
	


}
