package com.cnacex.eshop.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnacex.eshop.dao.BaseDAO;
import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.CommRspMsg;
import com.cnacex.eshop.msg.body.trade.sell.ApplyCdListedReq;
import com.cnacex.eshop.msg.body.trade.sell.ApplyCdReq;
import com.cnacex.eshop.msg.body.trade.sell.ApplyReq;
import com.cnacex.eshop.msg.body.trade.sell.AuditReq;
import com.cnacex.eshop.msg.body.trade.sell.BondPayReq;
import com.cnacex.eshop.msg.body.trade.sell.CancelReq;
import com.cnacex.eshop.msg.body.trade.sell.SellBillReq;
import com.cnacex.eshop.msg.body.trade.sell.SellOrderDetailReq;
import com.cnacex.eshop.msg.body.trade.sell.StoreReq;
import com.cnacex.eshop.msg.body.trade.sell.WRDetailReq;
import com.cnacex.eshop.msg.xml.trade.sell.ApplyCancelReqMsg;
import com.cnacex.eshop.msg.xml.trade.sell.ApplyCdListedReqMsg;
import com.cnacex.eshop.msg.xml.trade.sell.ApplyCdListedRspMsg;
import com.cnacex.eshop.msg.xml.trade.sell.ApplyCdReqMsg;
import com.cnacex.eshop.msg.xml.trade.sell.ApplyCdRspMsg;
import com.cnacex.eshop.msg.xml.trade.sell.ApplyEditCdReqMsg;
import com.cnacex.eshop.msg.xml.trade.sell.ApplyReqMsg;
import com.cnacex.eshop.msg.xml.trade.sell.ApplyRspMsg;
import com.cnacex.eshop.msg.xml.trade.sell.AuditCdReqMsg;
import com.cnacex.eshop.msg.xml.trade.sell.AuditCdRspMsg;
import com.cnacex.eshop.msg.xml.trade.sell.AuditReqMsg;
import com.cnacex.eshop.msg.xml.trade.sell.AuditRspMsg;
import com.cnacex.eshop.msg.xml.trade.sell.BondPayReqMsg;
import com.cnacex.eshop.msg.xml.trade.sell.BondPayRspMsg;
import com.cnacex.eshop.msg.xml.trade.sell.CancelCdReqMsg;
import com.cnacex.eshop.msg.xml.trade.sell.CancelCdSellReqMsg;
import com.cnacex.eshop.msg.xml.trade.sell.CancelReqMsg;
import com.cnacex.eshop.msg.xml.trade.sell.EditReqMsg;
import com.cnacex.eshop.msg.xml.trade.sell.SellBillReqMsg;
import com.cnacex.eshop.msg.xml.trade.sell.SellBillRspMsg;
import com.cnacex.eshop.msg.xml.trade.sell.SellOrderDetailReqMsg;
import com.cnacex.eshop.msg.xml.trade.sell.SellOrderDetailRspMsg;
import com.cnacex.eshop.msg.xml.trade.sell.StoreReqMsg;
import com.cnacex.eshop.msg.xml.trade.sell.StoreRspMsg;
import com.cnacex.eshop.msg.xml.trade.sell.WRDetailReqMsg;
import com.cnacex.eshop.msg.xml.trade.sell.WRDetailRspMsg;
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

	/**
	 * 注册仓单查询
	 * @author 文闻
	 * @param applyCdReq
	 * @date 2016-3-25
	 * @return ApplyCdRspMsg
	 */
	@Override
	public ApplyCdRspMsg findApplyCdReq(ApplyCdReq applyCdReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(ApplyCdReqMsg.class, applyCdReq);
		ApplyCdRspMsg rspMsg = baseDAO.handle(reqMsg,  ApplyCdRspMsg.class);
		return rspMsg;
	}

	/**
	 * 注册仓单申请挂牌
	 * @author 文闻
	 * @param apply
	 * @date 2016-3-30
	 * @return ApplyCdListedRspMsg
	 */
	@Override
	public ApplyCdListedRspMsg applyCdListedReq(ApplyCdListedReq apply) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(ApplyCdListedReqMsg.class, apply);
		ApplyCdListedRspMsg rspMsg = baseDAO.handle(reqMsg,  ApplyCdListedRspMsg.class);
		return rspMsg;
	}


	/**
	 * 仓单详情查询
	 * @author 文闻
	 * @param apply
	 * @date 2016-3-30
	 * @return WRDetailRspMsg
	 */
	@Override
	public WRDetailRspMsg getWRDetail(WRDetailReq apply) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(WRDetailReqMsg.class, apply);
		WRDetailRspMsg rspMsg = baseDAO.handle(reqMsg,  WRDetailRspMsg.class);
		return rspMsg;
	}


	/**
     *  注册仓单审核处理
	 * @author 文闻
	 * @date 2015-4-1
	 * @param auditReq
	 * @return
	 * AuditCdRspMsg
     *
	 */
	@Override
	public AuditCdRspMsg auditCdSell(AuditReq auditReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(AuditCdReqMsg.class, auditReq); 
		AuditCdRspMsg rspMsg = baseDAO.handle(reqMsg,  AuditCdRspMsg.class);
		return rspMsg;
	}


	/**
     *  待审核仓单撤销以及删除
	 * @author 文闻
	 * @date 2016-4-7 
	 * @param applyReq
	 * @return
	 * CommRspMsg
	 */
	@Override
	public CommRspMsg applyCancelCd(CancelReq cancelReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(CancelCdReqMsg.class, cancelReq); 
		CommRspMsg rspMsg = baseDAO.handle(reqMsg,  CommRspMsg.class);
		return rspMsg;
	}


	/**
     *  仓单下架处理
	 * @author 文闻
	 * @date 2016-4-8
	 * @param applyReq
	 * @return
	 * ApplyRspMsg
     *
	 */
	@Override
	public ApplyRspMsg cancelCdSell(CancelReq cancelReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(CancelCdSellReqMsg.class, cancelReq); 
		ApplyRspMsg rspMsg = baseDAO.handle(reqMsg,  ApplyRspMsg.class);
		return rspMsg;
	}

	/**
     *  仓单挂牌修改处理
	 * @author 文闻
	 * @date 2016-4-8 
	 * @param applyReq
	 * @return
	 * ApplyCdListedRspMsg
     *
	 */
	@Override
	public ApplyCdListedRspMsg editCdSell(ApplyReq applyReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(ApplyEditCdReqMsg.class, applyReq); 
		ApplyCdListedRspMsg rspMsg = baseDAO.handle(reqMsg,  ApplyCdListedRspMsg.class);
		return rspMsg;
	}
	
}
