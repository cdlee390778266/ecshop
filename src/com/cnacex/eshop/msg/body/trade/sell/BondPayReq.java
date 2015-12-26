
package com.cnacex.eshop.msg.body.trade.sell;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author kereny
 *
 */
public class BondPayReq {
	
	@XStreamAlias("mid")
	private String mID;
	
	@XStreamAlias("pyoperid")
	private String pyOperID;
	
	@XStreamAlias("fundpwd")
	private String fundPwd;
	
	@XStreamAlias("listedno")
	private String listedNo;


	public String getmID() {
		return mID;
	}

	public void setmID(String mID) {
		this.mID = mID;
	}

	public String getPyOperID() {
		return pyOperID;
	}

	public void setPyOperID(String pyOperID) {
		this.pyOperID = pyOperID;
	}

	public String getFundPwd() {
		return fundPwd;
	}

	public void setFundPwd(String fundPwd) {
		this.fundPwd = fundPwd;
	}

	public String getListedNo() {
		return listedNo;
	}

	public void setListedNo(String listedNo) {
		this.listedNo = listedNo;
	}
	
}
