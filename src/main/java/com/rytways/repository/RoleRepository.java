package com.rytways.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rytways.model.Roles;


public interface RoleRepository extends JpaRepository<Roles, Integer> {

	Optional<Roles> findById(Integer roleId);
	
	/*@Query("select new com.rytways.dto.LoadOptionsDto (mm.roleId as value, mm.roleName as label) from Roles mm"
			)
	//@Query("select cm.customer_id as value, cm.name as label from customer_master cm")
	List<LoadOptionsDto> findLoadOptions();*/
	
}
