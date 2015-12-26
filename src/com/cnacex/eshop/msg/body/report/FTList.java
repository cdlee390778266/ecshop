package com.cnacex.eshop.msg.body.report;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class FTList {
	
	@XStreamAlias("acctitle")
	private String accTitle;
	
	@XStreamAlias("acctitlename")
	private String accTitleName;

	public String getAccTitle() {
		return accTitle;
	}

	public void setAccTitle(String accTitle) {
		this.accTitle = accTitle;
	}

	public String getAccTitleName() {
		return accTitleName;
	}

	public void setAccTitleName(String accTitleName) {
		this.accTitleName = accTitleName;
	}
	
	
	
	


}
