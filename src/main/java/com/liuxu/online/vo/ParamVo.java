package com.liuxu.online.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class ParamVo implements Serializable{

	private static final long serialVersionUID = 8785560104487427732L;
	
	@NotBlank(message = "{paramVo.id.notBlank}")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	

}
