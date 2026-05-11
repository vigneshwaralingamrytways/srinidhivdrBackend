package com.rytways.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rytways.model.OrganizationChart;

@Repository
public interface OrganizationChartRepo extends JpaRepository<OrganizationChart, Long>{

	
	List<OrganizationChart> findByParentId(@Param("parentId") Long parentId);

	@Query("SELECT o FROM OrganizationChart o WHERE o.parentId IS NULL")
	List<OrganizationChart> findByRootDesignation();

}
