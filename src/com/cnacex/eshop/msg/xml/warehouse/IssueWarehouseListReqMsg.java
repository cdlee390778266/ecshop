package com.cnacex.eshop.msg.xml.warehouse;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.warehouse.IssueWarehouseListReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 签发仓单清单请求XML（交易码60005010）XML
 * @author 文闻
 *
 */
@XStreamAlias("cnacex:root")
public class IssueWarehouseListReqMsg extends AbstractReqMsg<IssueWarehouseListReq> {
	
	public static final String TX_CODE = "60005010";
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected IssueWarehouseListReq body;
	
	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}
	
	public IssueWarehouseListReq getBody() {
		return body;
	}

	public void setBody(IssueWarehouseListReq body) {
		this.body = body;
	}

	public String getTxCode(){
		return TX_CODE;
	}
	
	

}
