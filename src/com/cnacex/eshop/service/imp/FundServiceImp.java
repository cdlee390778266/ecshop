package com.cnacex.eshop.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnacex.comm.util.StringUtil;
import com.cnacex.eshop.dao.BaseDAO;
import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.CommRspMsg;
import com.cnacex.eshop.msg.body.fund.BalAmtPwdReq;
import com.cnacex.eshop.msg.body.fund.BalAmtReq;
import com.cnacex.eshop.msg.body.fund.BindAccQueryReq;
import com.cnacex.eshop.msg.body.fund.FundPwdSetReq;
import com.cnacex.eshop.msg.body.fund.QueryAccSReq;
import com.cnacex.eshop.msg.xml.fund.BalAmtPwdReqMsg;
import com.cnacex.eshop.msg.xml.fund.BalAmtReqMsg;
import com.cnacex.eshop.msg.xml.fund.BalAmtRspMsg;
import com.cnacex.eshop.msg.xml.fund.BindAccQueryReqMsg;
import com.cnacex.eshop.msg.xml.fund.BindAccQueryRspMsg;
import com.cnacex.eshop.msg.xml.fund.BusBalAmtReqMsg;
import com.cnacex.eshop.msg.xml.fund.BusBalAmtRspMsg;
import com.cnacex.eshop.msg.xml.fund.FundPwdSetReqMsg;
import com.cnacex.eshop.msg.xml.fund.QueryAccSReqMsg;
import com.cnacex.eshop.msg.xml.fund.QueryAccSRspMsg;
import com.cnacex.eshop.service.IFundService;
import com.cnacex.eshop.util.MsgBuilder;


@Service("fundService")
public class FundServiceImp implements IFundService {
	
	

	@Autowired
	private BaseDAO baseDAO;

	@Override
	public BalAmtRspMsg queryMemberAvalAmt(String mid, String passwd) {
		
		BalAmtRspMsg rspMsg = null;
		
		if(StringUtil.nullOrBlank(passwd)){
			BalAmtReq balAmtReq = new BalAmtReq();
			balAmtReq.setmID(mid);
			AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(
					BalAmtReqMsg.class, balAmtReq);
			
			rspMsg = baseDAO.handle(reqMsg, BalAmtRspMsg.class);
		}else{
			
			BalAmtPwdReq balAmtPwdReq = new BalAmtPwdReq();
			balAmtPwdReq.setmID(mid);
			balAmtPwdReq.setFundPwd(passwd);
			AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(
					BalAmtPwdReqMsg.class, balAmtPwdReq);
			
			rspMsg = baseDAO.handle(reqMsg, BalAmtRspMsg.class);
		}
		return rspMsg;
	}

	@Override
	public CommRspMsg fundPasswordSet(FundPwdSetReq fundPwdSetReq) {
	
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(
				FundPwdSetReqMsg.class, fundPwdSetReq);
		
		CommRspMsg rspMsg = baseDAO.handle(reqMsg, CommRspMsg.class);
		return rspMsg;
	}

	
	@Override
	public BindAccQueryRspMsg queryBindAccInfo(String mid) {
		
		BindAccQueryReq bindAccQueryReq = new BindAccQueryReq();
		
		bindAccQueryReq.setmID(mid);
		
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(
				BindAccQueryReqMsg.class, bindAccQueryReq);
		
		BindAccQueryRspMsg rspMsg = baseDAO.handle(reqMsg, BindAccQueryRspMsg.class);
		return rspMsg;

	}

	@Override
	public QueryAccSRspMsg queryAccList(QueryAccSReq queryAccSReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(
				QueryAccSReqMsg.class, queryAccSReq);
		
		QueryAccSRspMsg rspMsg = baseDAO.handle(reqMsg, QueryAccSRspMsg.class);
		return rspMsg;
	}

	@Override
	public BusBalAmtRspMsg queryBusAvalAmt(String mid) {
		BalAmtReq balAmtReq = new BalAmtReq();
		balAmtReq.setmID(mid);
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(
				BusBalAmtReqMsg.class, balAmtReq);
		
		BusBalAmtRspMsg rspMsg = baseDAO.handle(reqMsg, BusBalAmtRspMsg.class);
		
		return rspMsg;
	}

}
