package com.cnacex.eshop.msg;


/*
 * 抽象业务报文
 * 
 */
public abstract class AbstractReqMsg<B> {
	
	
	/** 获得交易报文头内容 */
	public abstract Head getHead();
	/** 设置交易报文头内容 */
	public abstract void setHead(Head head);
	
	/** 获得交易报文体内容 */
	public abstract B getBody();
	/** 设置交易报文体内容 */
	public abstract void setBody(B body) ;
	
	/** 取交易码 **/
	public abstract String getTxCode();
	
}
