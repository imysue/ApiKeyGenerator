package com.keyGenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;

import com.keyGenerator.dto.KeyGeneratorParam;
import com.keyGenerator.dto.KeyInfoParam;
import com.keyGenerator.service.KeyGeneratorService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
@Controller
//@RequestMapping("/key")
public class KeyGeneratorController {

	@Autowired
	KeyGeneratorService service;
	
	@RequestMapping("/")
	public String getIndex() {
		return "index";
	}
	
	@ApiOperation(value="", notes="최초 KEY 정보 등록")
	@RequestMapping(value="/registerKeyInfo", method=RequestMethod.POST)
	public ResponseEntity registerKeyInfo(KeyInfoParam param) {
			
		return new ResponseEntity(service.registerKeyInfo(param), HttpStatus.OK);
	}
	
	@ApiOperation(value="", notes="KEY 발급")
	@PostMapping("/generateKey")
	@ResponseBody
	public ResponseEntity generateKey(KeyGeneratorParam param) {

		return new ResponseEntity(service.generateKey(param), HttpStatus.OK);
	}
}
