package com.rytways.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.rytways.model.ApprovalAuthorities;

@Repository
public interface ApprovalAuthoritiesRepo extends JpaRepository<ApprovalAuthorities,Integer>,JpaSpecificationExecutor<ApprovalAuthorities> {
	
	
}
