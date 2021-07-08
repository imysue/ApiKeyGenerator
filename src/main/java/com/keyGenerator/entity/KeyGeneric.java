package com.keyGenerator.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@TableGenerator(
		name = "KEY_SEQ_GENERATOR", 
		table = "KEY", 		
		pkColumnValue = "KEY_SEQ",
		allocationSize = 1)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KeyGeneric {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "KEY_SEQ_GENERATOR")
	private Long id;  
	private String userName;
	private Long keyInfoId; 
	private Long keyValue;
}