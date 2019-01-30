package com.liuxu.online.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.liuxu.online.entity.MyTest;
import com.liuxu.online.manager.MyTestManager;
import com.liuxu.online.restfulcommon.exception.MyException;
import com.liuxu.online.restfulcommon.exception.ServiceException;
import com.liuxu.online.restfulcommon.reponse.ResponseJson;
import com.liuxu.online.vo.ParamVo;

@RestController
@Validated
@RequestMapping("/v1")
public class MyTestController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MyTestManager myTestManager;

	
	@GetMapping("/recommend/getShopInfo")
	public ResponseJson<MyTest> getShopInfo(
			@NotBlank(message = "{param.error}") @RequestParam(value = "id", required = true) String id,
			@CookieValue(value = "PPI", required = false) Integer ppi, 
			@CookieValue(value = "SCN", required = false) String scn, 
			HttpServletRequest request)throws MyException {
		System.out.println(">>>>>>>>>>>"+id);
		ResponseJson<MyTest> response = new ResponseJson<MyTest>();
		MyTest myTest=myTestManager.getShopInfo(1L);
		response.setData(myTest);
		return response;
	}

	
	
	@GetMapping("/recommend/getShopInfo1")
	public ResponseJson<MyTest> getShopInfo1(
			@NotBlank(message = "{param.error}") @RequestParam(value = "id", required = true) String id,
			@CookieValue(value = "SCN", required = true) String scn, 
			HttpServletRequest request)throws MyException {
		System.out.println(">>>>>>>>>>>"+id+"scn"+scn);
		ResponseJson<MyTest> response = new ResponseJson<MyTest>();
		MyTest myTest=myTestManager.getShopInfo(1L);
		response.setData(myTest);
		return response;
	}
	
	@PostMapping("/recommend/getShopInfo2")
	public ResponseJson<MyTest> getgetShopInfo2(@Valid @RequestBody ParamVo paramVo) throws MyException {
		ResponseJson<MyTest> response = new ResponseJson<MyTest>();
		try{
			MyTest myTest=myTestManager.getShopInfo(1L);
			response.setData(myTest);
		}catch (Exception e) {
			throw new ServiceException("product.skuList.exception");
		}
		return response;
	}

	
	
	@PostMapping("/recommend/getShopInfo3")
	public ResponseJson<MyTest> getgetShopInfo3(@Valid @RequestBody ParamVo paramVo) throws MyException {
		ResponseJson<MyTest> response = new ResponseJson<MyTest>();
		try{
			MyTest myTest=myTestManager.getShopInfo(1L);
			response.setData(myTest);
		}catch (Exception e) {
			throw new ServiceException("paramVo.id.notBlank");
		}
		return response;
	}






	

	



	
	
}
