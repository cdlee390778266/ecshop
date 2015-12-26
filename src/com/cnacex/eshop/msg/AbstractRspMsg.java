package com.cnacex.eshop.msg;


/*
 * 抽象业务报文
 * 
 */
public abstract class AbstractRspMsg<B> {
	
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
		
}
