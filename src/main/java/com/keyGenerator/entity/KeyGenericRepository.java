package com.keyGenerator.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyGenericRepository extends JpaRepository<KeyGeneric, Long> {

}
