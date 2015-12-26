package com.cnacex.eshop.service;

import com.cnacex.eshop.msg.CommRspMsg;
import com.cnacex.eshop.msg.body.fund.FundPwdSetReq;
import com.cnacex.eshop.msg.body.fund.QueryAccSReq;
import com.cnacex.eshop.msg.xml.fund.BalAmtRspMsg;
import com.cnacex.eshop.msg.xml.fund.BindAccQueryRspMsg;
import com.cnacex.eshop.msg.xml.fund.BusBalAmtRspMsg;
import com.cnacex.eshop.msg.xml.fund.QueryAccSRspMsg;

/**
 * @author kereny
 * 
 */

public interface IFundService {
	
	
	
	
		/**
	     *  查询会员余额
		 * @author kereny
		 * @date 2015-6-25 下午2:13:13
		 * @param mid
		 * @param passwd
		 * @return
		 * BalAmtRspMsg
	     *
		 */
	public abstract BalAmtRspMsg queryMemberAvalAmt(String mid, String passwd);
	
	
	
		/**
	     *  查询会员业务账户状态
		 * @author kereny
		 * @date 2015-9-11 下午3:35:43
		 * @param mid
		 * @return
		 * BusBalAmtRspMsg
	     *
		 */
	public abstract BusBalAmtRspMsg queryBusAvalAmt(String mid);
	
	
	
		/**
	     *  资金密码修改
		 * @author kereny
		 * @date 2015-7-3 下午2:07:46
		 * @param fundPwdSetReq
		 * @return
		 * CommRspMsg
	     *
		 */
	public abstract CommRspMsg fundPasswordSet(FundPwdSetReq fundPwdSetReq);
	
	
	
		/**
	     *  绑定信息查询
		 * @author kereny
		 * @date 2015-7-3 下午2:09:44
		 * @param mid
		 * @return
		 * BindAccQueryRspMsg
	     *
		 */
	public abstract BindAccQueryRspMsg queryBindAccInfo(String mid);
	
	
		/**
	     *  查询账务明细
		 * @author kereny
		 * @date 2015-7-7 下午2:34:41
		 * @param queryAccSReq
		 * @return
		 * QueryAccSRspMsg
	     *
		 */
	public abstract QueryAccSRspMsg queryAccList(QueryAccSReq queryAccSReq);
	
	
	

}
