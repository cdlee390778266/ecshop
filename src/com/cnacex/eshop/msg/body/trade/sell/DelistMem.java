package com.cnacex.eshop.msg.body.trade.sell;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("delistmem")
public class DelistMem {
	
	@XStreamAlias("delistmid")
	private String	delistmid;
	
	public String getDelistmid() {
		return delistmid;
	}

	public void setDelistmid(String delistmid) {
		this.delistmid = delistmid;
	}

	@Override
	public String toString() {
		return "DelistMem [delistmid=" + delistmid + "]";
	}


	
	

}
