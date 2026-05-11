package com.rytways.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rytways.model.TaskMeetingStatus;
import com.rytways.model.TaskType;

@Repository
public interface TaskMeetingStatusRepo extends JpaRepository<TaskMeetingStatus, Integer>{

	Optional<TaskMeetingStatus> findByTaskId(int taskId);

}
