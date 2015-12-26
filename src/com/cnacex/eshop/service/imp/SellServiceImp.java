package com.cnacex.eshop.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnacex.eshop.dao.BaseDAO;
import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.CommRspMsg;
import com.cnacex.eshop.msg.body.trade.sell.ApplyReq;
import com.cnacex.eshop.msg.body.trade.sell.AuditReq;
import com.cnacex.eshop.msg.body.trade.sell.BondPayReq;
import com.cnacex.eshop.msg.body.trade.sell.CancelReq;
import com.cnacex.eshop.msg.body.trade.sell.SellBillReq;
import com.cnacex.eshop.msg.body.trade.sell.SellOrderDetailReq;
import com.cnacex.eshop.msg.body.trade.sell.StoreReq;
import com.cnacex.eshop.msg.xml.trade.sell.ApplyCancelReqMsg;
import com.cnacex.eshop.msg.xml.trade.sell.ApplyReqMsg;
import com.cnacex.eshop.msg.xml.trade.sell.ApplyRspMsg;
import com.cnacex.eshop.msg.xml.trade.sell.AuditReqMsg;
import com.cnacex.eshop.msg.xml.trade.sell.AuditRspMsg;
import com.cnacex.eshop.msg.xml.trade.sell.BondPayReqMsg;
import com.cnacex.eshop.msg.xml.trade.sell.BondPayRspMsg;
import com.cnacex.eshop.msg.xml.trade.sell.CancelReqMsg;
import com.cnacex.eshop.msg.xml.trade.sell.EditReqMsg;
import com.cnacex.eshop.msg.xml.trade.sell.SellBillReqMsg;
import com.cnacex.eshop.msg.xml.trade.sell.SellBillRspMsg;
import com.cnacex.eshop.msg.xml.trade.sell.SellOrderDetailReqMsg;
import com.cnacex.eshop.msg.xml.trade.sell.SellOrderDetailRspMsg;
import com.cnacex.eshop.msg.xml.trade.sell.StoreReqMsg;
import com.cnacex.eshop.msg.xml.trade.sell.StoreRspMsg;
import com.cnacex.eshop.service.ISellService;
import com.cnacex.eshop.util.MsgBuilder;


@Service("sellService")
public class SellServiceImp implements ISellService {
	
	
	@Autowired
	private BaseDAO baseDAO;
	

	@Override
	public ApplyRspMsg applySell(ApplyReq applyReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(ApplyReqMsg.class, applyReq); 
		ApplyRspMsg rspMsg = baseDAO.handle(reqMsg,  ApplyRspMsg.class);
		return rspMsg;
	}


	@Override
	public SellOrderDetailRspMsg findSellDetailByID(String mid, String operid,
			String listedid) {
		
		
		SellOrderDetailReq req = new SellOrderDetailReq();
		
		req.setListedNo(listedid);
		
		req.setmID(mid);
		
		req.setOperID(operid);
		
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(SellOrderDetailReqMsg.class, req); 
		SellOrderDetailRspMsg rspMsg = baseDAO.handle(reqMsg,  SellOrderDetailRspMsg.class);
		return rspMsg;
		
	}


	@Override
	public AuditRspMsg auditSell(AuditReq auditReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(AuditReqMsg.class, auditReq); 
		AuditRspMsg rspMsg = baseDAO.handle(reqMsg,  AuditRspMsg.class);
		return rspMsg;
	}


	@Override
	public BondPayRspMsg payBondSell(BondPayReq payReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(BondPayReqMsg.class, payReq); 
		BondPayRspMsg rspMsg = baseDAO.handle(reqMsg,  BondPayRspMsg.class);
		return rspMsg;
	}

	
	

	@Override
	public SellBillRspMsg findSellList(SellBillReq sellBillReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(SellBillReqMsg.class, sellBillReq); 
		SellBillRspMsg rspMsg = baseDAO.handle(reqMsg,  SellBillRspMsg.class);
		return rspMsg;
	}


	@Override
	public ApplyRspMsg editSell(ApplyReq applyReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(EditReqMsg.class, applyReq); 
		ApplyRspMsg rspMsg = baseDAO.handle(reqMsg,  ApplyRspMsg.class);
		return rspMsg;
	}


	@Override
	public ApplyRspMsg cancelSell(CancelReq cancelReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(CancelReqMsg.class, cancelReq); 
		ApplyRspMsg rspMsg = baseDAO.handle(reqMsg,  ApplyRspMsg.class);
		return rspMsg;
	}


	@Override
	public StoreRspMsg findStoreList(StoreReq storeReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(StoreReqMsg.class, storeReq); 
		StoreRspMsg rspMsg = baseDAO.handle(reqMsg,  StoreRspMsg.class);
		return rspMsg;
	}


	@Override
	public CommRspMsg applyCancel(CancelReq cancelReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(ApplyCancelReqMsg.class, cancelReq); 
		CommRspMsg rspMsg = baseDAO.handle(reqMsg,  CommRspMsg.class);
		return rspMsg;
		
	}
	
}
