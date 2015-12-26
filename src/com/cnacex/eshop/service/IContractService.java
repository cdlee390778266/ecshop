package com.cnacex.eshop.service;

import com.cnacex.eshop.msg.body.contract.QueryListReq;
import com.cnacex.eshop.msg.body.contract.QuerySingleReq;
import com.cnacex.eshop.msg.xml.contract.QueryListRspMsg;
import com.cnacex.eshop.msg.xml.contract.QuerySingleRspMsg;

public interface IContractService {
	
	
		/**
	     *  合同列表查询
		 * @author kereny
		 * @date 2015-6-30 上午10:34:16
		 * @param queryListReq
		 * @return
		 * QueryListRspMsg
	     *
		 */
	public abstract QueryListRspMsg queryContractList(QueryListReq queryListReq);
	
	
	
		/**
	     *  单个合同查询
		 * @author kereny
		 * @date 2015-6-30 上午10:34:16
		 * @param querySingleReq
		 * @return
		 * QuerySingleRspMsg
	     *
		 */
	
	public abstract QuerySingleRspMsg querySingleContract(QuerySingleReq querySingleReq);
	
	
}
