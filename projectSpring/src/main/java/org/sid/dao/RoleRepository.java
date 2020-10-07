package org.sid.dao;

import org.sid.beans.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	public Role findRoleByNomRole(String roleName);

}
