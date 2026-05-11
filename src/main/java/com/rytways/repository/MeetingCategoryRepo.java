package com.rytways.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.rytways.model.GroupCategory;

@Repository
public interface MeetingCategoryRepo extends JpaRepository<GroupCategory, Integer>,JpaSpecificationExecutor<GroupCategory> {



}
