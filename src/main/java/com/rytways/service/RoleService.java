package com.rytways.service;





import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rytways.dto.LoadOptionsDto;
import com.rytways.model.Roles;
import com.rytways.repository.RoleRepository;

@Component
@Service
@Transactional
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	public Roles saveRole (Roles roles) {
		String isSaved = "";
		roles=roleRepository.save(roles);
        return roles;
	}
	
	
	public List<LoadOptionsDto> loadRoleOptions() {

	    List<Roles> rolesList = roleRepository.findAll();
	    
	    List<LoadOptionsDto> dtoList = new ArrayList<>();

	    if (!rolesList.isEmpty()) {
	        for (Roles role : rolesList) {
	            LoadOptionsDto loadDto = new LoadOptionsDto(); // Move inside the loop

	            loadDto.setLabel(role.getRoleName());
	            loadDto.setValue(role.getRoleId());

	            dtoList.add(loadDto); // Add each instance to the list
	        }
	    }

	    return dtoList; // Return the list after the loop
	}

	
}
