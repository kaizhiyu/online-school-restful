package com.liuxu.online.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "meidian.cms")
public class PageCodeConfig {

	private String productDetailsPageCode;

	public String getProductDetailsPageCode() {
		return productDetailsPageCode;
	}

	public void setProductDetailsPageCode(String productDetailsPageCode) {
		this.productDetailsPageCode = productDetailsPageCode;
	}
	
	
}
