package com.keyGenerator.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KeyInfoParam {

	String key;				// 용도 코드 ex) policy-number 보험증권번호, claim-number 고객센터문의번호
	String type;					// key이 타입 문자형 string, 숫자형 number -> enum
	String generator;		// Mysql 에서 사용 MySql, 범용 Generic
	String description;
	int minLength;
	Long keyInfoId;
	
}