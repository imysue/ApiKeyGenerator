package com.keyGenerator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.keyGenerator.dto.KeyGeneratorParam;
import com.keyGenerator.dto.KeyInfoParam;
import com.keyGenerator.entity.KeyGeneric;
import com.keyGenerator.entity.KeyGenericRepository;
import com.keyGenerator.entity.KeyInfo;
import com.keyGenerator.entity.KeyInfoRepository;
import com.keyGenerator.entity.KeyMySql;
import com.keyGenerator.entity.KeyMySqlRepository;
import com.keyGenerator.entity.KeyString;
import com.keyGenerator.entity.KeyStringRepository;
import com.keyGenerator.exception.CustomException;
import com.keyGenerator.utils.RandomValueUtil;


@Service
public class KeyGeneratorServiceImpl implements KeyGeneratorService {

	@Autowired
	private KeyInfoRepository keyInfoRepository;
	
	@Autowired
	private KeyStringRepository keyStringRepository;	

	@Autowired
	private KeyMySqlRepository keyMySqlRepository;	

	@Autowired
	private KeyGenericRepository keyGenericRepository;
			
	public KeyInfoParam registerKeyInfo(KeyInfoParam param) {
				
		KeyInfo exist = keyInfoRepository.findByKey(param.getKey());
        if(exist != null && exist.getId() > 0 ) {
        	throw new CustomException( "존재하는 ID 입니다.", HttpStatus.CONFLICT);
        }
        KeyInfo keyInfo = new KeyInfo();
        keyInfo.setKey(param.getKey());
        keyInfo.setType(param.getType());
        keyInfo.setGenerator(param.getGenerator());
        keyInfo.setDescription(param.getDescription());
        keyInfo.setMinLength(param.getMinLength());
        
        keyInfo = keyInfoRepository.save(keyInfo);
        
        param.setKeyInfoId(keyInfo.getId());
		
		return param;
	}


	@Override
	public KeyGeneratorParam generateKey(KeyGeneratorParam param) {
				
		// keyInfo 조회
		KeyInfo exist = keyInfoRepository.findByKey(param.getKey());
		
		if(exist == null || exist.getId() == 0 ) {
        	throw new CustomException( "Key info를 입력하세요.", HttpStatus.NO_CONTENT);
        }
		
		String id = "";
	
		if("string".equals(exist.getType())) {

			KeyString keyString = new KeyString();
			keyString.setUserName(param.getUserName());
			keyString.setKeyInfoId(exist.getId());
			
			String keyValue = (String) new RandomValueUtil().getRandomValue(16);
			
			String regex  = "(.{4})(.{4})(.{4})(.{4})";
			keyValue = keyValue.replaceAll(regex , "$1-$2-$3-$4");
			keyString.setKeyValue(keyValue);
			
			keyString = keyStringRepository.save(keyString);
						
			param.setStringKey(keyValue);
	        
		} else if("number".equals(exist.getType()) && "mysql".equals(exist.getGenerator())) {
						
			KeyMySql keyMySql = new KeyMySql();
			keyMySql.setUserName(param.getUserName());
			keyMySql.setKeyInfoId(exist.getId());
			
			keyMySql = keyMySqlRepository.save(keyMySql);
			
			param.setMySqlKey(Long.valueOf("1"+keyMySql.getId().toString()));
			
		} else if("number".equals(exist.getType()) && "generic".equals(exist.getGenerator())) {

			KeyGeneric keyGeneric = new KeyGeneric();
			keyGeneric.setUserName(param.getUserName());
			keyGeneric.setKeyInfoId(exist.getId());
			
			keyGeneric = keyGenericRepository.save(keyGeneric);
			
			param.setGenericKey(Long.valueOf("2"+ keyGeneric.getId().toString()));
		}
		
		return param;
	}
}
