package com.liuxu.online;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan	// 扫描加载filter
public class OnlineOrderApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(OnlineOrderApplication.class,args);
		System.out.println("...............................................................");
		System.out.println("..................Service starts successfully..................");
		System.out.println("...............................................................");

	}
}
