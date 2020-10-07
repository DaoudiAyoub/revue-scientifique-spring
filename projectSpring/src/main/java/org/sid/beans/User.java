package org.sid.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
public class User {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idUser;
	private String lastNameUser;
	private String firstNameUser;
	
	private String username;
	private String password;
	
	private String email;
	
	 @JsonIgnoreProperties("users")
	@ManyToOne
	@JoinColumn(name="idRole")
	private Role role;
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private Set<EvaluationJuree> evaluations=new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private Set<EcrireArticle> ecrits=new HashSet<>();

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getLastNameUser() {
		return lastNameUser;
	}

	public void setLastNameUser(String lastNameUser) {
		this.lastNameUser = lastNameUser;
	}

	public String getFirstNameUser() {
		return firstNameUser;
	}

	public void setFirstNameUser(String firstNameUser) {
		this.firstNameUser = firstNameUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}
	@JsonSetter
	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Set<EvaluationJuree> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(Set<EvaluationJuree> evaluations) {
		this.evaluations = evaluations;
	}
	
	
	public Set<EcrireArticle> getEcrits() {
		return ecrits;
	}

	public void setEcrits(Set<EcrireArticle> ecrits) {
		this.ecrits = ecrits;
	}

	public User() {
		super();
	}
	
	

	public User(String lastNameUser, String firstNameUser, String username, String password, String email) {
		super();
		this.lastNameUser = lastNameUser;
		this.firstNameUser = firstNameUser;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public User(String lastNameUser, String firstNameUser, String username, String password, String email, Role role,
			Set<EvaluationJuree> evaluations, Set<EcrireArticle> ecrits) {
		super();
		this.lastNameUser = lastNameUser;
		this.firstNameUser = firstNameUser;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.evaluations = evaluations;
		this.ecrits = ecrits;
	}

	public User(String lastNameUser, String firstNameUser, String username, String password, String email, Role role,
			Set<EcrireArticle> ecrits) {
		super();
		this.lastNameUser = lastNameUser;
		this.firstNameUser = firstNameUser;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.ecrits = ecrits;
	}

	

	
	
	
	
	
}
