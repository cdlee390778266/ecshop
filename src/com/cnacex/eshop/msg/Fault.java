package com.cnacex.eshop.msg;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cnacex.comm.util.StringUtil;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class Fault {
	
	static Logger logger = LoggerFactory.getLogger(Fault.class);
	
	/** 异常返回码  **/
	@XStreamAlias("rspcode")
	private String rspCode;
	
	/** 异常返回信息  **/
	@XStreamAlias("rspmsg")
	private String rspMsg;

	
	private int	reqNo;
	
	/** 异常返回信息  **/
	@XStreamImplicit
	public List<String> faultstring;
	
	public String getRspCode() {
		return rspCode;
	}

	public void setRspCode(String rspCode) {
		this.rspCode = rspCode;
	}

	public String getRspMsg() {
		
		StringBuffer strbuf = new StringBuffer();
		
		if(!StringUtil.nullOrBlank(this.rspMsg)){
			strbuf.append(this.rspMsg);
		}
		
		if(this.faultstring!=null){
			strbuf.append("交易号:").append(reqNo).append("<br/>");
			strbuf.append("错误提示:");
			for(int i = 0; i<this.faultstring.size(); i++){
				strbuf.append(this.faultstring.get(i));
				strbuf.append("<br/>");				
			}			
		}
		return strbuf.toString();
	}

	public void setRspMsg(String rspMsg) {
		this.rspMsg = rspMsg;
	}

	public List<String> getFaultstring() {
		return faultstring;
	}

	public void setFaultstring(List<String> faultstring) {
		this.faultstring = faultstring;
	}

	public int getReqNo() {
		return reqNo;
	}

	public void setReqNo(int reqNo) {
		this.reqNo = reqNo;
	}






}
