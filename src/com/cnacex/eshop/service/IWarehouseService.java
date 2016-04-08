package com.cnacex.eshop.service;

import com.cnacex.eshop.msg.body.warehouse.CancelWarehouseReq;
import com.cnacex.eshop.msg.body.warehouse.IssueWarehouseListReq;
import com.cnacex.eshop.msg.body.warehouse.ROutWarehouseReq;
import com.cnacex.eshop.msg.body.warehouse.RegWarehouseCancelReq;
import com.cnacex.eshop.msg.body.warehouse.RegWarehouseCancelRsp;
import com.cnacex.eshop.msg.body.warehouse.RegWarehouseListReq;
import com.cnacex.eshop.msg.xml.warehouse.CancelWarehouseRspMsg;
import com.cnacex.eshop.msg.xml.warehouse.IssueWarehouseListRspMsg;
import com.cnacex.eshop.msg.xml.warehouse.ROutWarehouseRspMsg;
import com.cnacex.eshop.msg.xml.warehouse.RegWarehouseCancelRspMsg;
import com.cnacex.eshop.msg.xml.warehouse.RegWarehouseListRspMsg;

public interface IWarehouseService {

	/**
	 * 签发仓单清单查询
	 * @author 文闻
	 * @param applyCdReq
	 * @date 2016-4-5
	 * @return IssueWarehouseListRspMsg
	 */
	public abstract IssueWarehouseListRspMsg findIssueWarehouseList( IssueWarehouseListReq applyReq);

	/**
	 * 签发仓单注册
	 * @author 文闻
	 * @param applyReq
	 * @date 2016-4-5
	 * @return RegWarehouseListRspMsg
	 */
	public abstract RegWarehouseListRspMsg register( RegWarehouseListReq applyReq);
	
	/**
	 * 待审核仓单清单查询
	 * @author 文闻
	 * @param applyCdReq
	 * @date 2016-4-5
	 * @return RegWarehouseCancelRspMsg
	 */
	public abstract RegWarehouseCancelRspMsg findCancelList( RegWarehouseCancelReq applyReq);
	
	/**
	 * 待审核注册仓单撤销
	 * @author 文闻
	 * @param applyCdReq
	 * @date 2016-4-7
	 * @return CancelWarehouseRsOspMsg
	 */
	public abstract CancelWarehouseRspMsg cancelWarehouse( CancelWarehouseReq applyReq);
	
	/**
	 * 注册仓单转出
	 * @author 文闻
	 * @param applyReq
	 * @date 2016-4-7
	 * @return ROutWarehouseRspMsg
	 */
	public abstract ROutWarehouseRspMsg rollout( ROutWarehouseReq applyReq);

}
