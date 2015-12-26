package com.cnacex.eshop.msg.body.mall;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class MdseStaticRsp {
	
	@XStreamAlias("dac")
	private String dac;
	
	@XStreamAlias("isupd")
	private char isUpd;
	
	@XStreamImplicit
	private List<MdseElement> mdseLists;
	
	public String getDac() {
		return dac;
	}

	public void setDac(String dac) {
		this.dac = dac;
	}

	public List<MdseElement> getMdseLists() {
		return mdseLists;
	}

	public void setMdseLists(List<MdseElement> mdseLists) {
		this.mdseLists = mdseLists;
	}

	public char getIsUpd() {
		return isUpd;
	}

	public void setIsUpd(char isUpd) {
		this.isUpd = isUpd;
	}
	
	
	

}
