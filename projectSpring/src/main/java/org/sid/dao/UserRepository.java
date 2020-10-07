package org.sid.dao;

import java.util.List;

import org.sid.beans.Article;
import org.sid.beans.EvaluationJuree;
import org.sid.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
	
	
	public User findUserByUsername(String username);
	
	
	
	
	//permet à un auteur authentifié de voir ses articles ainsi que des suivre leurs état
	@Query("SELECT e.article FROM EcrireArticle e WHERE e.user.idUser=:id")
	public List<Object> articleByAuteurId(Long id);
	
	//permet à un auteur d'un article encours d'évaluation( le statut n'est pas new / article pas encore publié ) de visualiser
		//les commentaires des différents referees 
	@Query("SELECT e.commentaire FROM EvaluationJuree e WHERE e.article.idArticle=:id")
	public List<Object> rapportJureeByArticleId(Long id);
	
	
	
	//permet d'afficher à la comité éditorial la liste des articles conforme avec la politique de la revue
	//=> article conforme= article pret à transferer aux referees
	@Query("SELECT a FROM Article a WHERE a.etat='conforme'")
	public List<Article> listArticleConforme();
	
	
	//permet d'afficher pour la comité éditorial la liste des referees
	@Query("SELECT u FROM User u WHERE u.role.nomRole='REFEREE'")
	public List<User> listReferees();
	
	
	
	//permet d'afficher la liste des articles affecter à un referees
	@Query("SELECT e.article FROM EvaluationJuree e WHERE e.user.idUser=:id")
	public List<Article> listArticleReferee(Long id);
	
	//permet d'afficher la liste des articles à modifier pour un auteur
	@Query("SELECT e.article FROM EvaluationJuree e WHERE e.user.idUser=:id AND e.article.etat='request modification'")
	public List<Article> listArticleAuteurDemandeModifcation(Long id);

	
	
	

}
