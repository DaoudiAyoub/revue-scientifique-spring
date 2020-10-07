package org.sid.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class EvaluationJuree {
	    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private Long idEvaluation;
	    private String etat;
	    private String commentaire;
	    
	    @JsonIgnore
	    @ManyToOne
	    @JoinColumn(name="idArticle")
	    private Article article;
	    @JsonIgnore
	    @ManyToOne
	    @JoinColumn(name="idUser")
	    private User user;

		public Long getIdEvaluation() {
			return idEvaluation;
		}

		public void setIdEvaluation(Long idEvaluation) {
			this.idEvaluation = idEvaluation;
		}

		public String getEtat() {
			return etat;
		}

		public void setEtat(String etat) {
			this.etat = etat;
		}

		public String getCommentaire() {
			return commentaire;
		}

		public void setCommentaire(String commentaire) {
			this.commentaire = commentaire;
		}

		public Article getArticle() {
			return article;
		}

		public void setArticle(Article article) {
			this.article = article;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public EvaluationJuree() {
			super();
		}

		public EvaluationJuree(String etat, String commentaire, Article article, User user) {
			super();
			this.etat = etat;
			this.commentaire = commentaire;
			this.article = article;
			this.user = user;
		}

		public EvaluationJuree(Article article, User user) {
			super();
			this.article = article;
			this.user = user;
		}
		
		
	    
	    
	    
	    
	    

}
