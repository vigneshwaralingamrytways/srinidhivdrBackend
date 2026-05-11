package com.rytways.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rytways.model.EmployeeDetailsForOrgChart;

@Repository
public interface EmployeeDetailsRepo extends JpaRepository<EmployeeDetailsForOrgChart, Integer>{

}
