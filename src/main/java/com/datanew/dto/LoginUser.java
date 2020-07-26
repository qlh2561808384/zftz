package com.datanew.dto;

public class LoginUser {
    private String username;
    private String menus;   //用户的权限，  id用|连接，   如      ,1,2,3,
	
	
	public LoginUser() {
		
	}


	public String getUsername() {
		return username;
	}


	public String getMenus() {
		return menus;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public void setMenus(String menus) {
		this.menus = menus;
	}

}
