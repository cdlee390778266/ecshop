package com.cnacex.eshop.service;

import com.cnacex.eshop.msg.body.fee.FeeReq;
import com.cnacex.eshop.msg.xml.fee.FeeRspMsg;

public interface IFeeService {
	
	
	public abstract FeeRspMsg findFeeList(FeeReq feeReq, String type);

}
