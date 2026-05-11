package com.rytways.service;




import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rytways.dto.TokenDto;
import com.rytways.model.Users;
import com.rytways.repository.UserRepository;




@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private TokenDto tokenDto;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Users> user = userRepo.findByUserName(username);
		if (user.isPresent()) {
			tokenDto.setRoleId(user.get().getRoleId());
			tokenDto.setUserId(user.get().getUserId());
			//tokenDto.setTimeOut(15*60*1000);
			tokenDto.setTimeOut(1 * 60 * 1000);
			tokenDto.setPersonName(user.get().getPersonName());
			tokenDto.setMachineName(user.get().getMachineName());
			tokenDto.setUserType(user.get().getUserType());
			return new User(user.get().getUserName(), user.get().getPassword(),
					new ArrayList<>(Arrays.asList(new SimpleGrantedAuthority(user.get().getRole().getRoleName()))));
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}