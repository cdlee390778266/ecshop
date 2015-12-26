package com.cnacex.eshop.service.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnacex.eshop.dao.BaseDAO;
import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.CommRspMsg;
import com.cnacex.eshop.msg.body.trade.delivery.AppealBillReq;
import com.cnacex.eshop.msg.body.trade.delivery.AppealReq;
import com.cnacex.eshop.msg.body.trade.delivery.BillReq;
import com.cnacex.eshop.msg.body.trade.delivery.ConfirmReq;
import com.cnacex.eshop.msg.body.trade.delivery.OrderDetailReq;
import com.cnacex.eshop.msg.xml.trade.delivery.AppealBillReqMsg;
import com.cnacex.eshop.msg.xml.trade.delivery.AppealBillRspMsg;
import com.cnacex.eshop.msg.xml.trade.delivery.AppealReqMsg;
import com.cnacex.eshop.msg.xml.trade.delivery.BillRspMsg;
import com.cnacex.eshop.msg.xml.trade.delivery.BuyBillReqMsg;
import com.cnacex.eshop.msg.xml.trade.delivery.BuyConfirmReqMsg;
import com.cnacex.eshop.msg.xml.trade.delivery.ConfirmRspMsg;
import com.cnacex.eshop.msg.xml.trade.delivery.InvConfirmReqMsg;
import com.cnacex.eshop.msg.xml.trade.delivery.OrderDetailReqMsg;
import com.cnacex.eshop.msg.xml.trade.delivery.OrderDetailRspMsg;
import com.cnacex.eshop.msg.xml.trade.delivery.SellBillReqMsg;
import com.cnacex.eshop.msg.xml.trade.delivery.SellConfirmReqMsg;
import com.cnacex.eshop.service.IDeliveryService;
import com.cnacex.eshop.util.MsgBuilder;



@Service("deliveryService")
public class DeliveryServiceImp implements IDeliveryService {
	
	
	static Logger logger = LoggerFactory.getLogger(DeliveryServiceImp.class);
	
	@Autowired
	private BaseDAO baseDAO;

	@Override
	public ConfirmRspMsg confirmDelivery(ConfirmReq confirmReq, String bsType) {
		
		AbstractReqMsg<?> reqMsg =  null;
		if("B".equalsIgnoreCase(bsType)){
			reqMsg = MsgBuilder.buildReqMsg(BuyConfirmReqMsg.class, confirmReq); 
		}else if("S".equalsIgnoreCase(bsType)){
			reqMsg = MsgBuilder.buildReqMsg(SellConfirmReqMsg.class, confirmReq); 
		}else if("I".equalsIgnoreCase(bsType)){
			reqMsg = MsgBuilder.buildReqMsg(InvConfirmReqMsg.class, confirmReq); 
		}else {
			logger.error("未知确认类型  {} 确认数据{}", bsType, confirmReq.toString());
			return null;
		}
		
		ConfirmRspMsg rspMsg = baseDAO.handle(reqMsg,  ConfirmRspMsg.class);

		return rspMsg;
	}

	
	
	@Override
	public BillRspMsg deliveryBillList(BillReq billReq, String bsType) {
		AbstractReqMsg<?> reqMsg =  null;
		if("B".equalsIgnoreCase(bsType)){
			reqMsg = MsgBuilder.buildReqMsg(BuyBillReqMsg.class, billReq); 
		}else if("S".equalsIgnoreCase(bsType)){
			reqMsg = MsgBuilder.buildReqMsg(SellBillReqMsg.class, billReq); 
		}else{ 
			logger.error("未知确认类型  {} 确认数据{}", bsType, billReq.toString());
			return null;
		}
		
		BillRspMsg rspMsg = baseDAO.handle(reqMsg,  BillRspMsg.class);

		return rspMsg;
	}



	@Override
	public CommRspMsg deliveryAppeal(AppealReq appealReq) {
		AbstractReqMsg<?> reqMsg =  MsgBuilder.buildReqMsg(AppealReqMsg.class, appealReq);
		CommRspMsg rspMsg = baseDAO.handle(reqMsg,  CommRspMsg.class);
		return rspMsg;
	}



	@Override
	public OrderDetailRspMsg findDetaiByID(OrderDetailReq orderDetailReq) {
		AbstractReqMsg<?> reqMsg =  MsgBuilder.buildReqMsg(OrderDetailReqMsg.class, orderDetailReq);
		OrderDetailRspMsg rspMsg = baseDAO.handle(reqMsg,  OrderDetailRspMsg.class);
		return rspMsg;
	}



	@Override
	public AppealBillRspMsg findAppealContent(AppealBillReq appealBillReq) {
		AbstractReqMsg<?> reqMsg =  MsgBuilder.buildReqMsg(AppealBillReqMsg.class, appealBillReq);
		AppealBillRspMsg rspMsg = baseDAO.handle(reqMsg,  AppealBillRspMsg.class);
		return rspMsg;
	}

}
