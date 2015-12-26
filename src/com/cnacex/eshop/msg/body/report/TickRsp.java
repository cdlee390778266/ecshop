package com.cnacex.eshop.msg.body.report;

import java.util.ArrayList;
import java.util.List;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class TickRsp {
	
	@XStreamAlias("mid")
	private String mID;
	
	@XStreamAlias("memname")
	private String memName;
	
	@XStreamAlias("date")
	private String date;
	
	@XStreamImplicit(itemFieldName = "strikelist")
	private List<Tick> list = new ArrayList<Tick>();

	public List<Tick> getList() {
		return list;
	}

	public void setList(List<Tick> list) {
		this.list = list;
	}

	public String getmID() {
		return mID;
	}

	public void setmID(String mID) {
		this.mID = mID;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	

}
