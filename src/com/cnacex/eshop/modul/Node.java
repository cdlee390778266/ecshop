package com.cnacex.eshop.modul;

public class Node {
	
	
	private String code;
	
	private String name;
	
	private String level;
	
	private boolean haveLeaf;

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

	public boolean isHaveLeaf() {
		return haveLeaf;
	}

	public void setHaveLeaf(boolean haveLeaf) {
		this.haveLeaf = haveLeaf;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}


	
}
