package com.mcm.entities.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcm.config.Constants;
import com.mcm.entities.dao.UserDao;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao userDao;
	//private UserDaoImpl userDao;
	
	
	@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
	
		com.mcm.entities.model.Actor user = userDao.getUserByUserName(username);
		
		//List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());

		List<GrantedAuthority> authorities = buildUserAuthority(0);
		
		return buildUserForAuthentication(user, authorities);
		
	}

	// Converts com.wubby.users.model.User user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(com.mcm.entities.model.Actor user, List<GrantedAuthority> authorities) {
		//return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
		return new User(user.getName(), "", true, true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(int userType) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		if(userType == Constants.USER_TYPE.ADMIN.getCode()){
			setAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} else if(userType == Constants.USER_TYPE.CUSTOMER.getCode()){
			setAuths.add(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
		} else if(userType == Constants.USER_TYPE.DEVELOPER.getCode()){
			setAuths.add(new SimpleGrantedAuthority("ROLE_DEVELOPER"));
		}  else if(userType == Constants.USER_TYPE.MANUFACTURER.getCode()){
			setAuths.add(new SimpleGrantedAuthority("ROLE_MANUFACTURER"));
		}	else if(userType == Constants.USER_TYPE.DEVICE.getCode()){
			setAuths.add(new SimpleGrantedAuthority("ROLE_DEVICE"));
		} else {

		}
		
		
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}
	

}