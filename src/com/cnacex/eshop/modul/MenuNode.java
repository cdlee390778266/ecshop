package com.cnacex.eshop.modul;

import java.util.List;

public class MenuNode {
	
	private String menuName;
	
	private String menuURL;
	
	public String getMenuName() {
		return menuName;
	}


	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}


	public String getMenuURL() {
		return menuURL;
	}


	public void setMenuURL(String menuURL) {
		this.menuURL = menuURL;
	}

	public List<MenuNode> getSubMenus() {
		return subMenus;
	}


	public void setSubMenus(List<MenuNode> subMenus) {
		this.subMenus = subMenus;
	}


	private boolean hasSubMenu;
	
	
	private List<MenuNode> subMenus;

	public boolean isHasSubMenu() {
		return hasSubMenu;
	}


	public void setHasSubMenu(boolean hasSubMenu) {
		this.hasSubMenu = hasSubMenu;
	}


	@Override
	public String toString() {
		return "MenuNode [menuName=" + menuName + ", menuURL=" + menuURL
				+ ", hasSubMenu=" + hasSubMenu + ", subMenus=" + subMenus + "]";
	}
	
	
	
	

}
