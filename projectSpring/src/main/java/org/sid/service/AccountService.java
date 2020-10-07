package org.sid.service;

import org.sid.beans.Role;
import org.sid.beans.User;

public interface AccountService {
	
	public User saveUser(User user);
	
	public Role saveRole(Role role);
	
	public void addRoleToUser(String username,String roleName);
	
	public User findUserByUsername(String username);
	
	

}
