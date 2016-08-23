package com.cnacex.eshop.service;

import com.cnacex.eshop.msg.CommRspMsg;
import com.cnacex.eshop.msg.body.trade.buy.ApplyReq;
import com.cnacex.eshop.msg.body.trade.buy.AuditReq;
import com.cnacex.eshop.msg.body.trade.buy.BuyBillReq;
import com.cnacex.eshop.msg.body.trade.buy.BuyPayReq;
import com.cnacex.eshop.msg.body.trade.buy.CancelReq;
import com.cnacex.eshop.msg.body.trade.buy.EditReq;
import com.cnacex.eshop.msg.body.trade.buy.WRApplyReq;
import com.cnacex.eshop.msg.body.trade.buy.WRAuditReq;
import com.cnacex.eshop.msg.body.trade.buy.WRBuyOrderDetailReq;
import com.cnacex.eshop.msg.body.trade.buy.WRBuyPayReq;
import com.cnacex.eshop.msg.body.trade.buy.WRCancelReq;
import com.cnacex.eshop.msg.xml.trade.buy.ApplyRspMsg;
import com.cnacex.eshop.msg.xml.trade.buy.AuditRspMsg;
import com.cnacex.eshop.msg.xml.trade.buy.BuyBillRspMsg;
import com.cnacex.eshop.msg.xml.trade.buy.BuyOrderDetailRspMsg;
import com.cnacex.eshop.msg.xml.trade.buy.BuyPayRspMsg;
import com.cnacex.eshop.msg.xml.trade.buy.WRApplyRspMsg;
import com.cnacex.eshop.msg.xml.trade.buy.WRAuditRspMsg;
import com.cnacex.eshop.msg.xml.trade.buy.WRBuyOrderDetailRspMsg;
import com.cnacex.eshop.msg.xml.trade.buy.WRBuyPayRspMsg;

/**
 * @author kereny
 * 
 */

public interface IBuyService {

	/**
	 * 摘牌请求服务
	 * 
	 * @author kereny
	 * @date 2015-6-17 上午10:34:16
	 * @param applyReq
	 * @return ApplyRspMsg
	 *
	 */
	public abstract ApplyRspMsg applyBuy(ApplyReq applyReq);

	/**
	 * 摘牌修改服务
	 * 
	 * @author kereny
	 * @date 2015-6-17 上午10:34:16
	 * @param applyReq
	 * @return ApplyRspMsg
	 *
	 */
	public abstract ApplyRspMsg editBuy(EditReq editReq);

	/**
	 * 根据摘牌单号请求详细信息
	 * 
	 * @author kereny
	 * @date 2015-6-17 上午10:34:35
	 * @param mid
	 * @param operid
	 * @param delistNo
	 * @return BuyOrderDetailRspMsg
	 *
	 */
	public abstract BuyOrderDetailRspMsg findBuyDetailByID(String mid, String operid, String delistNo);

	/**
	 * 未支付撤消处理
	 * 
	 * @author kereny
	 * @date 2015-6-17 上午11:00:27
	 * @param auditReq
	 * @return AuditRspMsg
	 *
	 */
	public abstract CommRspMsg applyCancel(CancelReq cancelReq);

	/**
	 * 摘牌审核
	 * 
	 * @author kereny
	 * @date 2015-6-17 上午11:00:27
	 * @param auditReq
	 * @return AuditRspMsg
	 *
	 */
	public abstract AuditRspMsg auditBuy(AuditReq auditReq);

	/**
	 * 摘牌付款
	 * 
	 * @author kereny
	 * @date 2015-6-17 下午1:37:41
	 * @param buyPayReq
	 * @return BuyPayRspMsg
	 *
	 */
	public abstract BuyPayRspMsg payBuy(BuyPayReq buyPayReq);

	/**
	 * 查询未摘牌完成清单列表
	 * 
	 * @author kereny
	 * @date 2015-6-17 下午2:01:10
	 * @param buyBillReq
	 * @return BuyBillRspMsg
	 *
	 */
	public abstract BuyBillRspMsg findBuyList(BuyBillReq buyBillReq);

	/**
	 * 仓单摘牌申请
	 * 
	 * @param wrApplyReq
	 * @return WRApplyRspMsg
	 */
	public abstract WRApplyRspMsg applyWRBuy(WRApplyReq wrApplyReq);
	
	/**
	 * 仓单摘牌审核
	 * 
	 * @param wrApplyReq
	 * @return WRAuditRspMsg
	 */
	public abstract WRAuditRspMsg auditWRBuy(WRAuditReq wrAuditReq);
	
	/**
	 * 仓单摘牌付款
	 * 
	 * @param wrBuyPayReq
	 * @return BuyPayRspMsg
	 *
	 */
	public abstract WRBuyPayRspMsg wrPayBuy(WRBuyPayReq wrBuyPayReq);
	
	/**
	 * 摘牌单详细信息查询
	 * 
	 * @param wrApplyReq
	 * @return WRBuyOrderDetailRspMsg
	 */
	public abstract WRBuyOrderDetailRspMsg findWRBuyOrderDetail(WRBuyOrderDetailReq wrApplyReq);
	
	/**
	 * 仓单摘牌撤销
	 * 
	 * @param cancelRsp
	 * @return WRCancelRspMsg
	 */
	public abstract CommRspMsg wrCancel(WRCancelReq cancelRsp);
}
