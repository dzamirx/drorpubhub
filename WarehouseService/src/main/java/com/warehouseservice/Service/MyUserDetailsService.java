package com.warehouseservice.Service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//class to return a specific username pulled out of database and return it to the calling method usually a jwt creation method
//Currently we will return a HARD CODDED USER needs to get users credentials from DB *****************************************************
@Service
public class MyUserDetailsService implements UserDetailsService
{

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		return new User("foo","foo", new ArrayList<>());//just empty array list instead of authorities
	}
	
}
