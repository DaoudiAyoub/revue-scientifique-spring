package org.sid.dao;

import java.util.List;

import org.sid.beans.EvaluationJuree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EvaluationJureeRepository extends JpaRepository<EvaluationJuree, Long> {
	
	//permet de trouver Evaluation Juree d'apres le referees et l'article
		@Query("SELECT e FROM EvaluationJuree e WHERE e.user.idUser=:idReferee AND e.article.idArticle=:idArticle")
		public EvaluationJuree chercherEvaluation(Long idReferee,Long idArticle);
		
		
		//permet de recuperer les etats des evaluations des referees pour un article
		@Query("SELECT e.etat FROM EvaluationJuree e WHERE e.article.idArticle=:idArticle")
		public List<String> etatsEvaluationArticle(Long idArticle);

}
