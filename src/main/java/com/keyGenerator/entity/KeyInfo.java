package com.keyGenerator.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "KeyInfo")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KeyInfo {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 500, nullable = false)
	private String key;				// 용도 코드 ex) policy-number 보험증권번호, claim-number 고객센터문의번호
	
	@Column(columnDefinition = "TEXT", nullable = true)
	private String description;			
	
	private String type;			// key이 타입 문자형 string, 숫자형 number -> enum
	
	private String generator;		// mysql : MysqlKeyGenerator generic : GenericKeyGenerator 

	@Column(name="min_length")
	private int minLength;
		
}
