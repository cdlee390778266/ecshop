package com.cnacex.eshop.modul;

import java.util.List;


public class MarkRight {
	
	private String code;
	
	private String name;
	
	private List<ClassRight> txRights;
	
	private List<ClassRight> adRights;
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public List<ClassRight> getTxRights() {
		return txRights;
	}

	public void setTxRights(List<ClassRight> txRights) {
		this.txRights = txRights;
	}

	public List<ClassRight> getAdRights() {
		return adRights;
	}

	public void setAdRights(List<ClassRight> adRights) {
		this.adRights = adRights;
	}
	
	
	
	
	

}
