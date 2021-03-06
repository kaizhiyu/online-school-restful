package com.liuxu.online.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liuxu.online.entity.MyTest;
import com.liuxu.online.service.IMyTestService;

@Service
public class MyTestManager {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IMyTestService myTestService;


	public MyTest getShopInfo(Long id){
		MyTest myTest  = myTestService.selectById(id);
		logger.info("myTestService.selectById ==> {} ",  myTest);
        return myTest;
	}
	

	

}
