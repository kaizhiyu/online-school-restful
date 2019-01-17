package com.liuxu.online.vo;

import java.io.Serializable;

public class TestVo implements Serializable{
	private static final long serialVersionUID = -1914902250539971025L;
	private Long id;
	private String name;
	private Long age;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	

}
