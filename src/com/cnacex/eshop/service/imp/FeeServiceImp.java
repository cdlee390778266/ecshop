package com.cnacex.eshop.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnacex.eshop.dao.BaseDAO;
import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.body.fee.FeeReq;
import com.cnacex.eshop.msg.xml.fee.BondReqMsg;
import com.cnacex.eshop.msg.xml.fee.ChargeReqMsg;
import com.cnacex.eshop.msg.xml.fee.FeeRspMsg;
import com.cnacex.eshop.service.IFeeService;
import com.cnacex.eshop.util.MsgBuilder;

@Service("feeService")
public class FeeServiceImp implements IFeeService {

	@Autowired
	private BaseDAO baseDAO;

	@Override
	public FeeRspMsg findFeeList(FeeReq feeReq, String type) {

		AbstractReqMsg<?> reqMsg = null;

		if ("1".equalsIgnoreCase(type)) {
			reqMsg = MsgBuilder.buildReqMsg(BondReqMsg.class, feeReq);

		} else {
			reqMsg = MsgBuilder.buildReqMsg(ChargeReqMsg.class, feeReq);
		}

		FeeRspMsg rspMsg = baseDAO.handle(reqMsg, FeeRspMsg.class);

		return rspMsg;

	}

}
