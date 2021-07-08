package com.keyGenerator.entity;

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
@Table(name = "KeyString")
@NoArgsConstructor
@AllArgsConstructor
@Builder 
public class KeyString {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String userName;
	private Long keyInfoId; 
	
	private String keyValue;
	
}
