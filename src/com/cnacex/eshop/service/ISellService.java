package com.cnacex.eshop.service;


import com.cnacex.eshop.msg.CommRspMsg;
import com.cnacex.eshop.msg.body.trade.sell.ApplyCdListedReq;
import com.cnacex.eshop.msg.body.trade.sell.ApplyCdReq;
import com.cnacex.eshop.msg.body.trade.sell.ApplyReq;
import com.cnacex.eshop.msg.body.trade.sell.AuditReq;
import com.cnacex.eshop.msg.body.trade.sell.BondPayReq;
import com.cnacex.eshop.msg.body.trade.sell.CancelReq;
import com.cnacex.eshop.msg.body.trade.sell.SellBillReq;
import com.cnacex.eshop.msg.body.trade.sell.StoreReq;
import com.cnacex.eshop.msg.body.trade.sell.WRDetailReq;
import com.cnacex.eshop.msg.xml.trade.sell.ApplyCdListedRspMsg;
import com.cnacex.eshop.msg.xml.trade.sell.ApplyCdRspMsg;
import com.cnacex.eshop.msg.xml.trade.sell.ApplyRspMsg;
import com.cnacex.eshop.msg.xml.trade.sell.AuditCdRspMsg;
import com.cnacex.eshop.msg.xml.trade.sell.AuditRspMsg;
import com.cnacex.eshop.msg.xml.trade.sell.BondPayRspMsg;
import com.cnacex.eshop.msg.xml.trade.sell.SellBillRspMsg;
import com.cnacex.eshop.msg.xml.trade.sell.SellOrderDetailRspMsg;
import com.cnacex.eshop.msg.xml.trade.sell.StoreRspMsg;
import com.cnacex.eshop.msg.xml.trade.sell.WRDetailRspMsg;
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
	
	
	/**
	 * 注册仓单查询
	 * @author 文闻
	 * @param applyCdReq
	 * @date 2016-3-25
	 * @return ApplyCdRspMsg
	 */
	public abstract ApplyCdRspMsg findApplyCdReq( ApplyCdReq applyCdReq);
	
	/**
	 * 注册仓单申请挂牌
	 * @author 文闻
	 * @param apply
	 * @date 2016-3-30
	 * @return ApplyCdListedRspMsg
	 */
	public abstract ApplyCdListedRspMsg applyCdListedReq( ApplyCdListedReq apply);
	
	/**
	 * 仓单详情查询
	 * @author 文闻
	 * @param apply
	 * @date 2016-3-30
	 * @return WRDetailRspMsg
	 */
	public abstract WRDetailRspMsg getWRDetail( WRDetailReq apply);
	
	/**
     *  注册仓单审核处理
	 * @author 文闻
	 * @date 2015-4-1
	 * @param auditReq
	 * @return
	 * AuditCdRspMsg
     *
	 */
	public abstract AuditCdRspMsg auditCdSell(AuditReq auditReq);
	
	/**
     *  待审核仓单撤销以及删除
	 * @author 文闻
	 * @date 2016-4-7 
	 * @param applyReq
	 * @return
	 * CommRspMsg
     *
	 */
	public abstract CommRspMsg applyCancelCd(CancelReq cancelReq);
	
	/**
     *  仓单下架处理
	 * @author 文闻
	 * @date 2016-4-8
	 * @param applyReq
	 * @return
	 * ApplyRspMsg
     *
	 */
	public abstract ApplyRspMsg cancelCdSell(CancelReq cancelReq);
	
	/**
     *  仓单挂牌修改处理
	 * @author 文闻
	 * @date 2016-4-8 
	 * @param applyReq
	 * @return
	 * ApplyCdListedRspMsg
     *
	 */
	public abstract ApplyCdListedRspMsg editCdSell(ApplyReq applyReq);

}
