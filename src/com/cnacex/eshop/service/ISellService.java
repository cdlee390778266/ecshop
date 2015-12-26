package com.cnacex.eshop.service;


import com.cnacex.eshop.msg.CommRspMsg;
import com.cnacex.eshop.msg.body.trade.sell.ApplyReq;
import com.cnacex.eshop.msg.body.trade.sell.AuditReq;
import com.cnacex.eshop.msg.body.trade.sell.BondPayReq;
import com.cnacex.eshop.msg.body.trade.sell.CancelReq;
import com.cnacex.eshop.msg.body.trade.sell.SellBillReq;
import com.cnacex.eshop.msg.body.trade.sell.StoreReq;
import com.cnacex.eshop.msg.xml.trade.sell.ApplyRspMsg;
import com.cnacex.eshop.msg.xml.trade.sell.AuditRspMsg;
import com.cnacex.eshop.msg.xml.trade.sell.BondPayRspMsg;
import com.cnacex.eshop.msg.xml.trade.sell.SellBillRspMsg;
import com.cnacex.eshop.msg.xml.trade.sell.SellOrderDetailRspMsg;
import com.cnacex.eshop.msg.xml.trade.sell.StoreRspMsg;
/**
 * @author kereny
 * 
 */

public interface ISellService {
	
	
		/**
	     *  挂牌申请处理
		 * @author kereny
		 * @date 2015-6-16 下午2:30:11
		 * @param applyReq
		 * @return
		 * ApplyRspMsg
	     *
		 */
	public abstract ApplyRspMsg applySell(ApplyReq applyReq);
	
		
		/**
	     *  挂牌修改处理
		 * @author kereny
		 * @date 2015-6-16 下午2:30:11
		 * @param applyReq
		 * @return
		 * ApplyRspMsg
	     *
		 */
	public abstract ApplyRspMsg editSell(ApplyReq applyReq);
	
	
	
		/**
	     *  下架处理
		 * @author kereny
		 * @date 2015-6-16 下午2:30:11
		 * @param applyReq
		 * @return
		 * ApplyRspMsg
	     *
		 */
	public abstract ApplyRspMsg cancelSell(CancelReq cancelReq);
	
	
	
	
		/**
	     *  未上架撤消处理
		 * @author kereny
		 * @date 2015-6-16 下午2:30:11
		 * @param applyReq
		 * @return
		 * ApplyRspMsg
	     *
		 */
	public abstract CommRspMsg applyCancel(CancelReq cancelReq);
	
	
	
		/**
	     *  根据挂牌单号查询对映明细
		 * @author kereny
		 * @date 2015-6-16 下午2:30:29
		 * @param mid
		 * @param operid
		 * @param listedid
		 * @return
		 * SellOrderDetailRspMsg
	     *
		 */
	public abstract SellOrderDetailRspMsg findSellDetailByID(String mid, String operid, String listedid);
	
	
		/**
	     *  审核处理
		 * @author kereny
		 * @date 2015-6-16 下午2:31:06
		 * @param auditReq
		 * @return
		 * AuditRspMsg
	     *
		 */
	public abstract AuditRspMsg auditSell(AuditReq auditReq);
	
	
		/**
	     *  保证金支付
		 * @author kereny
		 * @date 2015-6-16 下午3:03:43
		 * @param payReq
		 * @return
		 * BondPayRspMsg
	     *
		 */
	public abstract BondPayRspMsg payBondSell(BondPayReq payReq);
	
	
	
	
	
		/**
	     *  查询未处理完成挂牌清单
		 * @author kereny
		 * @date 2015-6-16 下午3:36:40
		 * @param sellBillReq
		 * @return
		 * SellBillRspMsg
	     *
		 */
	public abstract SellBillRspMsg findSellList(SellBillReq sellBillReq);
	
	
		/**
	     *  指定商品会员仓库查询
		 * @author kereny
		 * @date 2015-6-16 下午3:36:40
		 * @param storeReq
		 * @return
		 * StoreRspMsg
	     *
		 */
	public abstract StoreRspMsg findStoreList( StoreReq storeReq);

}
