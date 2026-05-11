package com.rytways.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rytways.model.AssignedToTask;

@Repository
public interface AssignedToTaskRepo extends JpaRepository<AssignedToTask, Integer>{

	List<AssignedToTask> findByTaskId(int taskId);

	List<AssignedToTask> findByUser(int userId);
}
