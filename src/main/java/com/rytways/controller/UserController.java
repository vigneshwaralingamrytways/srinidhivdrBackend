package com.rytways.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rytways.dto.LoadOptionsDto;
import com.rytways.model.Users;
import com.rytways.repository.UserRepository;
import com.rytways.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/create")
	public ResponseEntity<Users> createUser(@RequestBody Users user) {

		String isSaved = "";

		user = userService.saveCustomer(user);

		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("/users")
	public ResponseEntity<List<Users>> listcusotmers() {

		List<Users> customers = userRepo.findAll();

		return new ResponseEntity<>(customers, HttpStatus.OK);
	}
	
	@GetMapping("/loadOptions")
    public ResponseEntity <List<LoadOptionsDto>> loadUsers(){
	 		 	
	 
	 	List<LoadOptionsDto> loadFolderList=userService.loadUser();
       
        return new ResponseEntity<>(loadFolderList,HttpStatus.OK);
    }
	
	 @PostMapping("/searchUsers")
	    public ResponseEntity<List<Users>> searchUsers(@RequestBody Users user){
		 		
		 		String isSaved = "";
				 	
			 	List<Users> options = userService.getUserDetails(user);
		       
		        return new ResponseEntity<>(options,HttpStatus.OK);

	}
}