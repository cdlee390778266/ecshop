package com.cnacex.eshop.msg.xml.trade.buy;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.trade.buy.WRApplyReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 仓单摘牌申请请求报文
 * @author frog
 *
 */
@XStreamAlias("cnacex:root")
public class WRApplyReqMsg extends AbstractReqMsg<WRApplyReq> {
	
	/**
	 * 仓单摘牌申请
	 */
	public static final String TX_CODE = "60006001";

	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected WRApplyReq body;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public WRApplyReq getBody() {
		return body;
	}

	public void setBody(WRApplyReq body) {
		this.body = body;
	}
	
	public  String getTxCode() {
		return TX_CODE;
	}
}
