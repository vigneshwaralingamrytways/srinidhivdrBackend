package com.rytways.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rytways.model.Modules;







@Repository
public interface ModulesRepository extends JpaRepository<Modules,Integer> {
		
}