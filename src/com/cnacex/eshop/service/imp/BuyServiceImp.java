package com.cnacex.eshop.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnacex.eshop.dao.BaseDAO;
import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.CommRspMsg;
import com.cnacex.eshop.msg.body.trade.buy.ApplyReq;
import com.cnacex.eshop.msg.body.trade.buy.AuditReq;
import com.cnacex.eshop.msg.body.trade.buy.BuyBillReq;
import com.cnacex.eshop.msg.body.trade.buy.BuyOrderDetailReq;
import com.cnacex.eshop.msg.body.trade.buy.BuyPayReq;
import com.cnacex.eshop.msg.body.trade.buy.CancelReq;
import com.cnacex.eshop.msg.body.trade.buy.EditReq;
import com.cnacex.eshop.msg.body.trade.buy.WRApplyReq;
import com.cnacex.eshop.msg.body.trade.buy.WRAuditReq;
import com.cnacex.eshop.msg.body.trade.buy.WRBuyOrderDetailReq;
import com.cnacex.eshop.msg.body.trade.buy.WRBuyPayReq;
import com.cnacex.eshop.msg.xml.trade.buy.ApplyCancelReqMsg;
import com.cnacex.eshop.msg.xml.trade.buy.ApplyReqMsg;
import com.cnacex.eshop.msg.xml.trade.buy.ApplyRspMsg;
import com.cnacex.eshop.msg.xml.trade.buy.AuditReqMsg;
import com.cnacex.eshop.msg.xml.trade.buy.AuditRspMsg;
import com.cnacex.eshop.msg.xml.trade.buy.BuyBillReqMsg;
import com.cnacex.eshop.msg.xml.trade.buy.BuyBillRspMsg;
import com.cnacex.eshop.msg.xml.trade.buy.BuyOrderDetailReqMsg;
import com.cnacex.eshop.msg.xml.trade.buy.BuyOrderDetailRspMsg;
import com.cnacex.eshop.msg.xml.trade.buy.BuyPayReqMsg;
import com.cnacex.eshop.msg.xml.trade.buy.BuyPayRspMsg;
import com.cnacex.eshop.msg.xml.trade.buy.EditReqMsg;
import com.cnacex.eshop.msg.xml.trade.buy.WRApplyReqMsg;
import com.cnacex.eshop.msg.xml.trade.buy.WRApplyRspMsg;
import com.cnacex.eshop.msg.xml.trade.buy.WRAuditReqMsg;
import com.cnacex.eshop.msg.xml.trade.buy.WRAuditRspMsg;
import com.cnacex.eshop.msg.xml.trade.buy.WRBuyOrderDetailReqMsg;
import com.cnacex.eshop.msg.xml.trade.buy.WRBuyOrderDetailRspMsg;
import com.cnacex.eshop.msg.xml.trade.buy.WRBuyPayReqMsg;
import com.cnacex.eshop.msg.xml.trade.buy.WRBuyPayRspMsg;
import com.cnacex.eshop.service.IBuyService;
import com.cnacex.eshop.util.MsgBuilder;


@Service("buyService")
public class BuyServiceImp implements IBuyService {

	
	@Autowired
	private BaseDAO baseDAO;
	
	@Override
	public ApplyRspMsg applyBuy(ApplyReq applyReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(ApplyReqMsg.class, applyReq); 
		ApplyRspMsg rspMsg = baseDAO.handle(reqMsg,  ApplyRspMsg.class);
		return rspMsg;
	}

	
	@Override
	public BuyOrderDetailRspMsg findBuyDetailByID(String mid, String operid,
			String delistNo) {
		BuyOrderDetailReq req = new BuyOrderDetailReq();
		req.setDeListNo(delistNo);
		req.setmID(mid);
		req.setOperID(operid);
		
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(BuyOrderDetailReqMsg.class, req); 
		BuyOrderDetailRspMsg rspMsg = baseDAO.handle(reqMsg,  BuyOrderDetailRspMsg.class);
		return rspMsg;
	}


	@Override
	public AuditRspMsg auditBuy(AuditReq auditReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(AuditReqMsg.class, auditReq); 
		AuditRspMsg rspMsg = baseDAO.handle(reqMsg,  AuditRspMsg.class);
		return rspMsg;
	}


	@Override
	public BuyPayRspMsg payBuy(BuyPayReq buyPayReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(BuyPayReqMsg.class, buyPayReq); 
		BuyPayRspMsg rspMsg = baseDAO.handle(reqMsg,  BuyPayRspMsg.class);
		return rspMsg;
	}


	@Override
	public BuyBillRspMsg findBuyList(BuyBillReq buyBillReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(BuyBillReqMsg.class, buyBillReq); 
		BuyBillRspMsg rspMsg = baseDAO.handle(reqMsg,  BuyBillRspMsg.class);
		return rspMsg;
	}


	@Override
	public ApplyRspMsg editBuy(EditReq editReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(EditReqMsg.class, editReq); 
		ApplyRspMsg rspMsg = baseDAO.handle(reqMsg,  ApplyRspMsg.class);
		return rspMsg;
	}


	@Override
	public CommRspMsg applyCancel(CancelReq cancelReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(ApplyCancelReqMsg.class, cancelReq); 
		CommRspMsg rspMsg = baseDAO.handle(reqMsg,  CommRspMsg.class);
		return rspMsg;
	}

	@Override
	public WRApplyRspMsg applyWRBuy(WRApplyReq wrApplyReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(WRApplyReqMsg.class, wrApplyReq);
		WRApplyRspMsg rspMsg = baseDAO.handle(reqMsg, WRApplyRspMsg.class);
		return rspMsg;
	}
	
	/**
	 * 仓单摘牌审核
	 * 
	 * @param wrApplyReq
	 * @return WRAuditRspMsg
	 */
	@Override
	public WRAuditRspMsg auditWRBuy(WRAuditReq wrAuditReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(WRAuditReqMsg.class, wrAuditReq);
		WRAuditRspMsg rspMsg = baseDAO.handle(reqMsg, WRAuditRspMsg.class);
		return rspMsg;
	}
	
	/**
	 * 仓单摘牌付款
	 * 
	 * @param wrBuyPayReq
	 * @return BuyPayRspMsg
	 *
	 */
	@Override
	public WRBuyPayRspMsg wrPayBuy(WRBuyPayReq wrBuyPayReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(WRBuyPayReqMsg.class, wrBuyPayReq);
		WRBuyPayRspMsg rspMsg = baseDAO.handle(reqMsg, WRBuyPayRspMsg.class);
		return rspMsg;
	}
	
	/**
	 * 摘牌单详细信息查询
	 * 
	 * @param wrApplyReq
	 * @return WRBuyOrderDetailRspMsg
	 */
	@Override
	public WRBuyOrderDetailRspMsg findWRBuyOrderDetail(WRBuyOrderDetailReq wrApplyReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(WRBuyOrderDetailReqMsg.class, wrApplyReq);
		WRBuyOrderDetailRspMsg rspMsg = baseDAO.handle(reqMsg, WRBuyOrderDetailRspMsg.class);
		return rspMsg;
	}
}
