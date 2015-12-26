package com.cnacex.eshop.msg.body.trade.sell;

import java.util.ArrayList;
import java.util.List;


import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class StoreRsp {
	
	@XStreamImplicit(itemFieldName = "storelist")
	List<Store> list = new ArrayList<Store>();

	public List<Store> getList() {
		return list;
	}

	public void setList(List<Store> list) {
		this.list = list;
	}
	
}
