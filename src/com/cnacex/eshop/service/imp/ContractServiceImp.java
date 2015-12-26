package com.cnacex.eshop.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnacex.eshop.dao.BaseDAO;
import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.body.contract.QueryListReq;
import com.cnacex.eshop.msg.body.contract.QuerySingleReq;
import com.cnacex.eshop.msg.xml.contract.QueryListReqMsg;
import com.cnacex.eshop.msg.xml.contract.QueryListRspMsg;
import com.cnacex.eshop.msg.xml.contract.QuerySingleReqMsg;
import com.cnacex.eshop.msg.xml.contract.QuerySingleRspMsg;
import com.cnacex.eshop.service.IContractService;
import com.cnacex.eshop.util.MsgBuilder;

@Service("contractService")
public class ContractServiceImp implements IContractService {

		
	@Autowired
	private BaseDAO baseDAO;
	
	@Override
	public QueryListRspMsg queryContractList(QueryListReq queryListReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(QueryListReqMsg.class, queryListReq); 
		QueryListRspMsg rspMsg = baseDAO.handle(reqMsg,  QueryListRspMsg.class);
		return rspMsg;
	}

	@Override
	public QuerySingleRspMsg querySingleContract(QuerySingleReq querySingleReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(QuerySingleReqMsg.class, querySingleReq); 
		QuerySingleRspMsg rspMsg = baseDAO.handle(reqMsg,  QuerySingleRspMsg.class);
		return rspMsg;
	}

}
