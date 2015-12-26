package com.cnacex.eshop.msg;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/*
 * 抽象业务报文
 * 
 */

public abstract class AbstractMsg<B> {
	
	@XStreamAsAttribute()
	@XStreamAlias("xmlns:cnacex")
	private String xsiName = "http://www.cnacex.com/";
	
	/** 获得交易报文头内容 */
	public abstract Head getHead();
	/** 设置交易报文头内容 */
	public abstract void setHead(Head head);
	
	/** 获得交易报文体内容 */
	public abstract B getBody();
	/** 设置交易报文体内容 */
	public abstract void setBody(B body) ;
	
	/** 获得异常报文体内容 */
	public abstract Fault getFault();
	/** 设置异常报文体内容 */
	public abstract void setFault(Fault fault);
	
	/** 取交易码 **/
	public abstract String getTxCode();
	
}
