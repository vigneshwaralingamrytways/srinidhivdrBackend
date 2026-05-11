package com.rytways.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.rytways.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>,JpaSpecificationExecutor<Task>{

	List<Task> findByCreateBy(int userId);

	List<Task> findByOwner(int userId);

}
