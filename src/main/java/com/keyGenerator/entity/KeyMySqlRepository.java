package com.keyGenerator.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyMySqlRepository extends JpaRepository<KeyMySql, Long> {

}
