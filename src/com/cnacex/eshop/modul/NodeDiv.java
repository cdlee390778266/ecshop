package com.cnacex.eshop.modul;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class NodeDiv {
	@XStreamAlias("code")
	private String code;

	@XStreamAlias("name")
	private String name;

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

	@Override
	public String toString() {
		return "NodeDiv [code=" + code + ", name=" + name + "]";
	}
	

}
