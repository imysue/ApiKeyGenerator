package com.keyGenerator.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyInfoRepository extends JpaRepository<KeyInfo, Long> {

	public KeyInfo findById(String id); 
	
	public KeyInfo findByKey(String key);
	
}
