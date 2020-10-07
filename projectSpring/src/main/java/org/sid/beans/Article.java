package org.sid.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Article {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idArticle;
	private String titre;
	private String affiliation;
	private String resume;
	private String contenu;
	private String decision;
	private String etat;
	
	  @JsonIgnoreProperties("article")
	@OneToMany(mappedBy="article")
	private Set<MotCle> mots=new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="article")
	private Set<EvaluationJuree> evaluations=new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="article")
	private Set<EcrireArticle> ecrits=new HashSet<>();

	public Long getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(Long idArticle) {
		this.idArticle = idArticle;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public Set<MotCle> getMots() {
		return mots;
	}

	public void setMots(Set<MotCle> mots) {
		this.mots = mots;
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
	
	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Article() {
		super();
	}

	public Article(String titre, String affiliation, String resume, String contenu, String decision, String etat,
			Set<MotCle> mots, Set<EvaluationJuree> evaluations, Set<EcrireArticle> ecrits) {
		super();
		this.titre = titre;
		this.affiliation = affiliation;
		this.resume = resume;
		this.contenu = contenu;
		this.decision = decision;
		this.etat = etat;
		this.mots = mots;
		this.evaluations = evaluations;
		this.ecrits = ecrits;
	}

	public Article(String titre, String affiliation, String resume, String contenu, String decision, String etat,
			Set<MotCle> mots, Set<EcrireArticle> ecrits) {
		super();
		this.titre = titre;
		this.affiliation = affiliation;
		this.resume = resume;
		this.contenu = contenu;
		this.decision = decision;
		this.etat = etat;
		this.mots = mots;
		this.ecrits = ecrits;
	}

	
	
	
	
	
}
