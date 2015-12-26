package com.cnacex.eshop.msg.body.mall;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * @author kereny
 *
 */

public class MdseStaticReq {
	
	
	@XStreamAlias("dac")
	private String dac;

	public String getDac() {
		return dac;
	}

	public void setDac(String dac) {
		this.dac = dac;
	}

	

}
