package org.sid.service;

import org.sid.beans.Role;
import org.sid.beans.User;
import org.sid.dao.RoleRepository;
import org.sid.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncorder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public User saveUser(User user) {
		String hashPW=bCryptPasswordEncorder.encode(user.getPassword());
		user.setPassword(hashPW);;
		return userRepository.save(user);
		
	}

	@Override
	public Role saveRole(Role role) {
		
		return roleRepository.save(role);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		Role role=roleRepository.findRoleByNomRole(roleName);
		User user=userRepository.findUserByUsername(username);
		user.setRole(role);
		userRepository.save(user);
	}

	@Override
	public User findUserByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findUserByUsername(username);
	}

}
