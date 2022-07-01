package com.mysite.sbb.user;

import lombok.Getter;

/*
 * - 열거 자료형인 Enumeration 으로 작성. 
 * - 상수자료형이라 Setter는 불필요
 */
@Getter
public enum UserRole {
	ADMIN("ROLE_ADMIN"),
	USER("ROLE_USER");
	
	UserRole(String value){
		this.value = value;
	}
	
	private String value;
}
