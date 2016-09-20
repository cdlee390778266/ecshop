package com.cnacex.eshop.modul;

import java.util.List;

public class Node {
	
	
	private String code;
	
	private String name;
	
	private String level;
	
	private boolean haveLeaf;
	
	private List<Node> childNodes;

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

	public List<Node> getChildNodes() {
		return childNodes;
	}

	public void setChildNodes(List<Node> childNodes) {
		this.childNodes = childNodes;
	}

	@Override
	public String toString() {
		return "Node [code=" + code + ", name=" + name + ", level=" + level + ", haveLeaf=" + haveLeaf + ", childNodes="
				+ childNodes + "]";
	}
}
