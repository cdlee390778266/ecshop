package com.cnacex.eshop.service;

import com.cnacex.eshop.msg.body.warehouse.BindingMemberReq;
import com.cnacex.eshop.msg.body.warehouse.CancelWarehouseReq;
import com.cnacex.eshop.msg.body.warehouse.IssueWarehouseListReq;
import com.cnacex.eshop.msg.body.warehouse.ROutWarehouseReq;
import com.cnacex.eshop.msg.body.warehouse.RegWarehouseCancelReq;
import com.cnacex.eshop.msg.body.warehouse.RegWarehouseListReq;
import com.cnacex.eshop.msg.body.warehouse.TrigBindingMemberReq;
import com.cnacex.eshop.msg.xml.warehouse.BindingMemberRspMsg;
import com.cnacex.eshop.msg.xml.warehouse.CancelWarehouseRspMsg;
import com.cnacex.eshop.msg.xml.warehouse.IssueWarehouseListRspMsg;
import com.cnacex.eshop.msg.xml.warehouse.ROutWarehouseRspMsg;
import com.cnacex.eshop.msg.xml.warehouse.RegWarehouseCancelRspMsg;
import com.cnacex.eshop.msg.xml.warehouse.RegWarehouseListRspMsg;
import com.cnacex.eshop.msg.xml.warehouse.TrigBindingMemberRspMsg;

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

	/**
	 * 关联会员关联关系查询
	 * @param applyReq
	 * @return BindingMemberRspMsg
	 */
	public abstract BindingMemberRspMsg findRelations( BindingMemberReq applyReq);
	
	/**
	 * 仓单关联会员
	 * @param applyReq
	 * @return TrigBindingMemberRspMsg
	 */
	public abstract TrigBindingMemberRspMsg trigBindingMember( TrigBindingMemberReq applyReq);
}
