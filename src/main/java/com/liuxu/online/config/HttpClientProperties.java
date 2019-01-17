package com.liuxu.online.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "http")
public class HttpClientProperties {
	
	private Integer maxTotal;					//最大连接数
    private Integer defaultMaxPerRoute;			//并发数
    private Integer connectTimeout;				//连接超时时间，取得了连接池中的某个连接之后到接通目标url的连接等待时间。发生超时，会抛出ConnectionTimeoutException异常
    private Integer connectionRequestTimeout;	//从连接池中取连接的超时时间,从ConnectionManager管理的连接池中取出连接的超时时间,超时则抛出ConnectionPoolTimeoutException异常
    private Integer socketTimeout;				//请求超时时间，连接到服务器之后到从服务器获取响应数据需要等待的时间，连接上一个url之后到获取response的返回等待时间。发生超时，会抛出SocketTimeoutException异常
    private boolean staleConnectionCheckEnabled;// 在提交请求之前 测试连接是否可用
	
    public Integer getMaxTotal() {
		return maxTotal;
	}
	public void setMaxTotal(Integer maxTotal) {
		this.maxTotal = maxTotal;
	}
	public Integer getDefaultMaxPerRoute() {
		return defaultMaxPerRoute;
	}
	public void setDefaultMaxPerRoute(Integer defaultMaxPerRoute) {
		this.defaultMaxPerRoute = defaultMaxPerRoute;
	}
	public Integer getConnectTimeout() {
		return connectTimeout;
	}
	public void setConnectTimeout(Integer connectTimeout) {
		this.connectTimeout = connectTimeout;
	}
	public Integer getConnectionRequestTimeout() {
		return connectionRequestTimeout;
	}
	public void setConnectionRequestTimeout(Integer connectionRequestTimeout) {
		this.connectionRequestTimeout = connectionRequestTimeout;
	}
	public Integer getSocketTimeout() {
		return socketTimeout;
	}
	public void setSocketTimeout(Integer socketTimeout) {
		this.socketTimeout = socketTimeout;
	}
	public boolean isStaleConnectionCheckEnabled() {
		return staleConnectionCheckEnabled;
	}
	public void setStaleConnectionCheckEnabled(boolean staleConnectionCheckEnabled) {
		this.staleConnectionCheckEnabled = staleConnectionCheckEnabled;
	}
    
    
}
