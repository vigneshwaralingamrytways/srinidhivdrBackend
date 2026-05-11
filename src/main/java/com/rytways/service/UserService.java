package com.rytways.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rytways.dto.LoadOptionsDto;
import com.rytways.model.DepartmentMaster;
import com.rytways.model.Roles;
import com.rytways.model.Users;
import com.rytways.repository.DepartRepository;
import com.rytways.repository.RoleRepository;
import com.rytways.repository.UserRepository;
import com.rytways.specifications.UserMasterSpec;

@Component
@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private DepartRepository departRepo;

	public Users saveCustomer(Users cust) {
		
		Optional<Users> existingUserOpt = userRepo.findById(cust.getUserId());
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if (existingUserOpt.isPresent()) {
			String incomingPassword = cust.getPassword();
			if (incomingPassword != null && !incomingPassword.isEmpty()) {
				if (!incomingPassword.startsWith("$2a$") && !incomingPassword.startsWith("$2b$")
						&& !incomingPassword.startsWith("$2y$")) {

					incomingPassword = passwordEncoder.encode(incomingPassword);
				}
				cust.setPassword(incomingPassword);
			}
		} else {
			String encodedPass = passwordEncoder.encode(cust.getPassword());
			cust.setPassword(encodedPass);
		}
		
		cust = userRepo.save(cust);
		Optional<Roles> role = roleRepo.findById(cust.getRoleId());
		if(role.isPresent())
		  cust.setRole(role.get());
		Optional<DepartmentMaster> depart = departRepo.findById(cust.getDepartmentId());
		if(depart.isPresent())
	      cust.setDepartment(depart.get());
		return cust;
		
//		String isSaved = "";
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//		System.out.println("cust.getRoleId();==>" + cust.getRoleId());
//
//		String pswd = passwordEncoder.encode(cust.getPassword());
//		cust.setPassword(pswd);
//
//		cust = userRepo.save(cust);
//
//		Optional<Roles> role = roleRepo.findById(cust.getRoleId());
//
//		cust.setRole(role.get());
//
//		Optional<DepartmentMaster> depart = departRepo.findById(cust.getDepartmentId());
//
//		cust.setDepartment(depart.get());
//
//		return cust;
	}

	public List<LoadOptionsDto> loadUser() {

		List<Users> userList = userRepo.findAll();
		List<LoadOptionsDto> dtoList = new ArrayList<>();
		if (!userList.isEmpty()) {
			for (Users userMster : userList) {
				LoadOptionsDto loadDto = new LoadOptionsDto(); // Move inside the loop

				loadDto.setLabel(userMster.getUserName());
				loadDto.setValue(userMster.getUserId());

				dtoList.add(loadDto); // Add each instance to the list
			}
		}
		return dtoList;
	}

	public List<Users> getUserDetails(Users user) {

		Specification spec1 = UserMasterSpec.userNameLike(user.getUserName());
		Specification spec2 = UserMasterSpec.roleIdEqual(user.getRoleId());

		Specification<Users> spec = Specification.where(spec1).and(spec2);
		// Use Sort to specify sorting order
		Sort sort = Sort.by(Sort.Direction.ASC, "userId");

		return userRepo.findAll(spec);

	}
}