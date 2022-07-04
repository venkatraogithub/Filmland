/**
 * 
 */
package com.sogeti.filmland.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sogeti.filmland.entity.UserDataTable;
import com.sogeti.filmland.repo.UserDataRepo;

/**
 * @author monal500
 *
 */
@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDataRepo userDataRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserDataTable userDataTable = userDataRepo.findByEmail(username);

		return new User(userDataTable.getEmail(), userDataTable.getEncryptedPassword(), new ArrayList<>());
	}

}
