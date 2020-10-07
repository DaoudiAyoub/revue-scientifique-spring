package org.sid.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Role {
	
	@Id @GeneratedValue
    private Long idRole;
    private String nomRole;
    
    
	//@JsonIgnore
    //@OneToMany(mappedBy="role")
    //private Set<User> users=new HashSet<>();
    
    
    public Role() {
		super();
	}
	
	public Role(Long idRole, String nomRole) {
		super();
		this.idRole = idRole;
		this.nomRole = nomRole;
	}
	public Role(String nomRole) {
		super();
		this.nomRole = nomRole;
	}
	public Long getIdRole() {
		return idRole;
	}
	public void setIdRole(Long id) {
		this.idRole = id;
	}
	public String getNomRole() {
		return nomRole;
	}
	public void setNomRole(String nomRole) {
		this.nomRole = nomRole;
	}
    
    

}
