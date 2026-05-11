package com.rytways.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rytways.model.CreateTask;

@Repository
public interface CreateTaskFromMeetingRepo extends JpaRepository<CreateTask, Integer>{

}
