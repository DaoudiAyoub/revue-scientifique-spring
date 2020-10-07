package org.sid.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class EcrireArticle {

	 @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private Long idEcrire;
	 
	 @JsonIgnoreProperties("ecrits")
	    @ManyToOne
	    @JoinColumn(name="idArticle")
	    private Article article;
	 
	 @JsonIgnoreProperties("ecrits")
	    @ManyToOne
	    @JoinColumn(name="idUser")
	    private User user;

		public Long getIdEcrire() {
			return idEcrire;
		}

		public void setIdEcrire(Long idEcrire) {
			this.idEcrire = idEcrire;
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

		public EcrireArticle() {
			super();
		}

		public EcrireArticle(Long idEcrire, Article article, User user) {
			super();
			this.idEcrire = idEcrire;
			this.article = article;
			this.user = user;
		}
	    
	    
}
