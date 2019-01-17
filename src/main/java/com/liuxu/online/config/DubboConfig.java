package com.liuxu.online.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * 加载dubbo，xml文件
 * @author lsx
 *
 */
@Configuration
@ImportResource({"classpath*:dubbo/*.xml"})
public class DubboConfig {
	
}
