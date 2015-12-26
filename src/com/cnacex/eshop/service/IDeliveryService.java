package com.cnacex.eshop.service;

import com.cnacex.eshop.msg.CommRspMsg;
import com.cnacex.eshop.msg.body.trade.delivery.AppealBillReq;
import com.cnacex.eshop.msg.body.trade.delivery.AppealReq;
import com.cnacex.eshop.msg.body.trade.delivery.BillReq;
import com.cnacex.eshop.msg.body.trade.delivery.ConfirmReq;
import com.cnacex.eshop.msg.body.trade.delivery.OrderDetailReq;
import com.cnacex.eshop.msg.xml.trade.delivery.AppealBillRspMsg;
import com.cnacex.eshop.msg.xml.trade.delivery.BillRspMsg;
import com.cnacex.eshop.msg.xml.trade.delivery.ConfirmRspMsg;
import com.cnacex.eshop.msg.xml.trade.delivery.OrderDetailRspMsg;

public interface IDeliveryService {
	
	
		/**
	     *  成交确认   买 /卖/发票
		 * @author kereny
		 * @date 2015-6-17 下午4:13:20
		 * @param confirmReq
		 * @param bsType
		 * @return
		 * ConfirmRspMsg
	     *
		 */
	public abstract ConfirmRspMsg confirmDelivery(ConfirmReq confirmReq, String bsType);
	
	
	
		/**
	     *  查询交收清单
		 * @author kereny
		 * @date 2015-6-18 上午10:01:25
		 * @param billReq
		 * @param bsType
		 * @return
		 * BillRspMsg
	     *
		 */
	public abstract BillRspMsg deliveryBillList(BillReq billReq, String bsType);
	
	
		/**
	     *  交收投诉
		 * @author kereny
		 * @date 2015-7-3 下午12:53:05
		 * @param appealReq
		 * @return
		 * CommRspMsg
	     *
		 */
	public abstract CommRspMsg deliveryAppeal(AppealReq appealReq);
	
	
	
		/**
	     *  交收详单查询
		 * @author kereny
		 * @date 2015-7-6 下午4:40:05
		 * @param orderDetailReq
		 * @return
		 * OrderDetailRspMsg
	     *
		 */
	public abstract OrderDetailRspMsg findDetaiByID(OrderDetailReq orderDetailReq);
	
	
	
		/**
	     *  投诉单内容查询
		 * @author kereny
		 * @date 2015-8-27 下午5:33:26
		 * @param appealBillReq
		 * @return
		 * AppealBillRspMsg
	     *
		 */
	public abstract AppealBillRspMsg findAppealContent(AppealBillReq appealBillReq);
	

}
