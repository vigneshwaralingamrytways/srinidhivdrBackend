package com.rytways.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.rytways.model.ClientMaster;

@Repository
public interface ClientMasterRepo extends JpaRepository<ClientMaster, Integer>,JpaSpecificationExecutor<ClientMaster>{

}
