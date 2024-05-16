package com.danielnunesro.authapi.enums;

public enum RoleEnum {
	
	ADMIN("admin"),
	USER("user");
	
	private String role;

	private RoleEnum(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}
	
	
	
}
