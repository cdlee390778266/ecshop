
package com.cnacex.eshop.msg.xml.report;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.report.ReportCommReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author kereny
 *
 */

public class DailyReqMsg extends AbstractReqMsg<ReportCommReq> {
	
	public static final String TX_CODE = "00001121";
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected ReportCommReq body;


	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public ReportCommReq getBody() {
		return body;
	}

	public void setBody(ReportCommReq body) {
		this.body = body;
	}
	
	public String getTxCode(){
		return TX_CODE;
	}

}
