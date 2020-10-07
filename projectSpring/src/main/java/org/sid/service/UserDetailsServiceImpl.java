package org.sid.service;

import java.util.ArrayList;
import java.util.Collection;

import org.sid.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired 
	private AccountService accountService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user=accountService.findUserByUsername(username);
		if(user==null) throw new UsernameNotFoundException(username);
		
		Collection<GrantedAuthority> authoritie=new ArrayList<>();
		
		authoritie.add(new SimpleGrantedAuthority(user.getRole().getNomRole()));
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authoritie);
		
		
	}

}
