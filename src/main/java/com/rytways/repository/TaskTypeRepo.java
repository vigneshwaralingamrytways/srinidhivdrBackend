package com.rytways.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.rytways.model.TaskType;

@Repository
public interface TaskTypeRepo extends JpaRepository<TaskType, Integer>,JpaSpecificationExecutor<TaskType>{

}
