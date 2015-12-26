package com.cnacex.eshop.service.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnacex.eshop.dao.BaseDAO;
import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.body.query.HisQueryReq;
import com.cnacex.eshop.msg.xml.query.BuyHisQueryReqMsg;
import com.cnacex.eshop.msg.xml.query.HisQueryRspMsg;
import com.cnacex.eshop.msg.xml.query.SellHisQueryReqMsg;
import com.cnacex.eshop.service.IQueryService;
import com.cnacex.eshop.util.MsgBuilder;

@Service("queryService")
public class QueryServiceImp implements IQueryService {

	static Logger logger = LoggerFactory.getLogger(QueryServiceImp.class);
	@Autowired
	private BaseDAO baseDAO;

	@Override
	public HisQueryRspMsg queryHisList(HisQueryReq hisQueryReq, String bsType) {
		
		
		AbstractReqMsg<?> reqMsg =  null;
		if("B".equalsIgnoreCase(bsType)){
			reqMsg = MsgBuilder.buildReqMsg(BuyHisQueryReqMsg.class, hisQueryReq); 
		}else if("S".equalsIgnoreCase(bsType)){
			reqMsg = MsgBuilder.buildReqMsg(SellHisQueryReqMsg.class, hisQueryReq); 
		}else{
			logger.error("未知确认类型  {} 确认数据{}", bsType, hisQueryReq.toString());
			return null;
		}
		
		HisQueryRspMsg rspMsg = baseDAO.handle(reqMsg,  HisQueryRspMsg.class);
		
		return rspMsg;
	}




}
