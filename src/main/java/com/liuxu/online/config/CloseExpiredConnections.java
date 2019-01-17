package com.liuxu.online.config;

import java.util.concurrent.TimeUnit;

import org.apache.http.conn.HttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * httpClient连接池处理
 * @author lsx
 *
 */
@Component
public class CloseExpiredConnections extends Thread {
	
	@Autowired
    private HttpClientConnectionManager connMgr;

    private volatile boolean shutdown;

    public CloseExpiredConnections() {
        super();
        super.start();
    }

    @Override
    public void run() {
        try {
            while (!shutdown) {
                synchronized (this) {
                	// 等待5秒
                    wait(5000);
                    // 关闭失效的连接
                    connMgr.closeExpiredConnections();
                    // 选择关闭 空闲30秒的链接
                    connMgr.closeIdleConnections(30, TimeUnit.SECONDS);
                }
            }
        } catch (InterruptedException ex) {
            // 结束
        }
    }

    //关闭清理无效连接的线程
    public void shutdown() {
        shutdown = true;
        synchronized (this) {
            notifyAll();
        }
    }
}
