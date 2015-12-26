package com.cnacex.eshop.msg.xml.trade.buy;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.trade.buy.EditReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("cnacex:root")
public class EditReqMsg extends AbstractReqMsg<EditReq> {

	public static final String TX_CODE = "05101026";

	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected EditReq body;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public EditReq getBody() {
		return body;
	}

	public void setBody(EditReq body) {
		this.body = body;
	}

	public  String getTxCode() {
		return TX_CODE;
	}
	

}
