package com.rytways.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.rytways.model.TaskSubType;

@Repository
public interface TaskSubTypeRepo extends JpaRepository<TaskSubType, Integer>,JpaSpecificationExecutor<TaskSubType>{

	List<TaskSubType> findByTaskTypeId(int taskTypeId);
}