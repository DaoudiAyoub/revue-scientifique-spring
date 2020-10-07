package org.sid.dao;

import java.util.List;

import org.sid.beans.EcrireArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EcrireArticleRepository extends JpaRepository<EcrireArticle, Long> {
	
	//liste réduites des articles par nom Auteur
	@Query("SELECT e.user.firstNameUser,e.user.lastNameUser,e.article.titre,e.article.resume,m FROM EcrireArticle e join e.article a join a.mots m WHERE e.article.decision='publié' AND e.user.lastNameUser like :nom")
	public List<Object> infoArticleReduitesParAuteur(String nom);
	
	//liste réduites des articles par motCle
	@Query("SELECT e.user.firstNameUser,e.user.lastNameUser,e.article.titre,e.article.resume,m.motCle FROM EcrireArticle e join e.article a join a.mots m WHERE m.motCle like :mot AND e.article.decision='publié'")
		public List<Object> infoArticleReduitesMotCle(String mot);
	
	//visualiser l’ensemble du contenu y compris le manuscrit de toutes les publications.
		@Query("SELECT e.user.firstNameUser,e.user.lastNameUser,e.article.titre,e.article.resume,e.article.affiliation,e.article.contenu,m.motCle FROM EcrireArticle e join e.article a join a.mots m WHERE e.article.decision='publié'")
			public List<Object> infoArticle();

}
