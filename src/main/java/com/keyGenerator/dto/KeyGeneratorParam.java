package com.keyGenerator.dto;

import lombok.Data;

@Data
public class KeyGeneratorParam {
	
	String key;				// 용도 코드 ex) policy-number 보험증권번호, claim-number 고객센터문의번호
	String userName;
	String StringKey;
	Long mySqlKey;
	Long genericKey;
	
}
