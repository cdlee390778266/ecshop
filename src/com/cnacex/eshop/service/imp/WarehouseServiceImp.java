package com.cnacex.eshop.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnacex.eshop.dao.BaseDAO;
import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.body.warehouse.CancelWarehouseReq;
import com.cnacex.eshop.msg.body.warehouse.IssueWarehouseListReq;
import com.cnacex.eshop.msg.body.warehouse.ROutWarehouseReq;
import com.cnacex.eshop.msg.body.warehouse.RegWarehouseCancelReq;
import com.cnacex.eshop.msg.body.warehouse.RegWarehouseListReq;
import com.cnacex.eshop.msg.xml.warehouse.CancelWarehouseReqMsg;
import com.cnacex.eshop.msg.xml.warehouse.CancelWarehouseRspMsg;
import com.cnacex.eshop.msg.xml.warehouse.IssueWarehouseListReqMsg;
import com.cnacex.eshop.msg.xml.warehouse.IssueWarehouseListRspMsg;
import com.cnacex.eshop.msg.xml.warehouse.ROutWarehouseReqMsg;
import com.cnacex.eshop.msg.xml.warehouse.ROutWarehouseRspMsg;
import com.cnacex.eshop.msg.xml.warehouse.RegWarehouseCancelReqMsg;
import com.cnacex.eshop.msg.xml.warehouse.RegWarehouseCancelRspMsg;
import com.cnacex.eshop.msg.xml.warehouse.RegWarehouseListReqMsg;
import com.cnacex.eshop.msg.xml.warehouse.RegWarehouseListRspMsg;
import com.cnacex.eshop.service.IWarehouseService;
import com.cnacex.eshop.util.MsgBuilder;

/**
 * 交易端仓单管理
 * @author 文闻
 *
 */
@Service("warehouseService")
public class WarehouseServiceImp implements IWarehouseService {
	
	
	@Autowired
	private BaseDAO baseDAO;


	/**
	 * 签发仓单清单查询
	 * @author 文闻
	 * @param applyCdReq
	 * @date 2016-4-6
	 * @return IssueWarehouseListRspMsg
	 */
	@Override
	public IssueWarehouseListRspMsg findIssueWarehouseList(IssueWarehouseListReq applyReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(IssueWarehouseListReqMsg.class, applyReq); 
		IssueWarehouseListRspMsg rspMsg = baseDAO.handle(reqMsg,  IssueWarehouseListRspMsg.class);
		return rspMsg;
	}


	/**
	 * 签发仓单注册
	 * @author 文闻
	 * @param applyReq
	 * @date 2016-4-6
	 * @return RegWarehouseListRspMsg
	 */
	@Override
	public RegWarehouseListRspMsg register(RegWarehouseListReq applyReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(RegWarehouseListReqMsg.class, applyReq); 
		RegWarehouseListRspMsg rspMsg = baseDAO.handle(reqMsg,  RegWarehouseListRspMsg.class);
		return rspMsg;
	}

	/**
	 * 待审核注册仓单清单查询
	 * @author 文闻
	 * @param applyReq
	 * @date 2016-4-6
	 * @return RegWarehouseCancelRspMsg
	 */
	@Override
	public RegWarehouseCancelRspMsg findCancelList(RegWarehouseCancelReq applyReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(RegWarehouseCancelReqMsg.class, applyReq); 
		RegWarehouseCancelRspMsg rspMsg = baseDAO.handle(reqMsg,  RegWarehouseCancelRspMsg.class);
		return rspMsg;
	}


	/**
	 * 待审核注册仓单撤销
	 * @author 文闻
	 * @param applyCdReq
	 * @date 2016-4-7
	 * @return CancelWarehouseRspMsg
	 */
	@Override
	public CancelWarehouseRspMsg cancelWarehouse(CancelWarehouseReq applyReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(CancelWarehouseReqMsg.class, applyReq); 
		CancelWarehouseRspMsg rspMsg = baseDAO.handle(reqMsg,  CancelWarehouseRspMsg.class);
		return rspMsg;
	}


	/**
	 * 注册仓单转出
	 * @author 文闻
	 * @param applyReq
	 * @date 2016-4-7
	 * @return ROutWarehouseRspMsg
	 */
	@Override
	public ROutWarehouseRspMsg rollout(ROutWarehouseReq applyReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(ROutWarehouseReqMsg.class, applyReq); 
		ROutWarehouseRspMsg rspMsg = baseDAO.handle(reqMsg,  ROutWarehouseRspMsg.class);
		return rspMsg;
	}
	
	


}
