package org.sid.service;

import java.util.List;

import org.sid.beans.Article;
import org.sid.beans.EvaluationJuree;
import org.sid.beans.User;
import org.sid.dao.ArticleRepository;
import org.sid.dao.EvaluationJureeRepository;
import org.sid.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired 
	private EvaluationJureeRepository evaluationJureeRepository;
	
	
	//permet à un auteur authentifié de voir ses articles ainsi que des suivre leurs état
	public List<Object> articleByAuteurId(Long id){
	   return userRepository.articleByAuteurId(id);
	}
	
	//permet à un auteur d'un article encours d'évaluation( le statut n'est pas new / article pas encore publié ) de visualiser
	//les commentaires des différents referees 
	public List<Object> rapportJureeByArticleId(Long id){
		List<Object> comments=null;
		Article article=articleRepository.findById(id).get();
		if(!article.getEtat().equals("new") && !article.getDecision().equals("published")) {
	
		comments= userRepository.rapportJureeByArticleId(id);
		}
		return comments;
	}
	
	//le comité éditorial vérifie la conformité de l’article avec la politique de la revue.
		//pour cela il faut d'abord verifier si l'utilisateur authentifié à le role comité
		//apres il faut verifier si le statut de l'article à verifie est new.
	public Article conformeArticle(Article article,Long idUser,Long idArticle) {
		Article arrrr=null;
		User user=userRepository.findById(idUser).get();
		if(user.getRole().getNomRole().equals("comite")) {
			Article art=articleRepository.findById(idArticle).get();
			if(art.getEtat().equals("new")) {
				article.setIdArticle(idArticle);
				arrrr=articleRepository.save(article);
			}
			
		}
		return arrrr;
		
	}
	
	//permet d'afficher à la comité éditorial la liste des articles conforme avec la politique de la revue
		//=> article conforme= article pret à transferer aux referees
	public List<Article> listArticleConforme(){
		return userRepository.listArticleConforme();
	}

	
	//permet d'afficher pour la comité éditorial la liste des referees
    public List<User> listReferees(){
  	  return userRepository.listReferees();
    }
	
    
    //permet d'affecter un article dont le statut est conforme à 3 referees
      public String affecterArticleReferees(Long idArticle,Long idReferee1,Long idReferee2,Long idReferee3) {
		
    	  Article article=articleRepository.findById(idArticle).get();
    	  if(article.getEtat().equals("conforme")) {
    		  User user1=userRepository.findById(idReferee1).get();
    		//normalement la verification du role n'est pas nécessaire car l'utilisateur doit selectionner les jurees depuis la liste des juree
    		  if(user1.getRole().getNomRole().equals("referee")) {
    			  EvaluationJuree evaluation1=new EvaluationJuree();
    			  evaluation1.setArticle(article);
    			  evaluation1.setUser(user1);
    			  evaluationJureeRepository.save(evaluation1);
    		  }
    		  User user2=userRepository.findById(idReferee2).get();
    		  //normalement la verification du role n'est pas nécessaire car l'utilisateur doit selectionner les jurees depuis la liste des juree
    		  if(user2.getRole().getNomRole().equals("referee")) {
    			  EvaluationJuree evaluation2=new EvaluationJuree();
    			  evaluation2.setArticle(article);
    			  evaluation2.setUser(user2);
    			  evaluationJureeRepository.save(evaluation2);
    		  }
    		  User user3=userRepository.findById(idReferee3).get();
    		  //normalement la verification du role n'est pas nécessaire car l'utilisateur doit selectionner les jurees depuis la liste des juree
    		  if(user3.getRole().getNomRole().equals("referee")) {
    			  EvaluationJuree evaluation3=new EvaluationJuree();
    			  evaluation3.setArticle(article);
    			  evaluation3.setUser(user3);
    			  evaluationJureeRepository.save(evaluation3);
    		  }
    		  
    		  article.setEtat("transferred");
    		  articleRepository.save(article);
    		  
    		  return "affectation réussite";
    	  }
    	  else {
    		  return "ERROR | l'article n'a pas le statut conforme" ;
    	  }
    	  
    	  
		
	}
      
      
    //permet d'afficher la liste des articles affecter à un referees
      public List<Article> listArticleReferee(Long id){
    	  return userRepository.listArticleReferee(id);
      }
      
      //permet à un referees d'evaluer un article sous les conditions suivante:
      //l'article doit avoir l'etat transferred
      //cette article n'a jamais été évalué par ce referée
      public String evaluerArticle(Long idReferee,Long idArticle,EvaluationJuree evaluationJ) {
    	  String message="Impossible d'ajouter l'évaluation";
    	  Article article = articleRepository.findById(idArticle).get();
    	  if(article.getEtat().equals("transferred")) {
    		  EvaluationJuree evaluation=evaluationJureeRepository.chercherEvaluation(idReferee, idArticle);
    		  if(evaluation.getEtat()==null) {
        	  evaluation.setCommentaire(evaluationJ.getCommentaire());
        	  evaluation.setEtat(evaluationJ.getEtat());
        	  evaluationJureeRepository.save(evaluation);
        	  message="Evaluation ajoutée";
        	  testEvaluationTerminer(idArticle);
    		  }
    		  else {
    			  message="Impossible d'ajouter l'évaluation";
    		  }
    	  }
    	  return message;
      }
      
      // cette methode permet de verifier si un article à été évaluer par les 3 referees afin de changer son statut
      public void testEvaluationTerminer(Long idArticle) {
    	   boolean t=true;
    	  List<String> etatsEvaluation=evaluationJureeRepository.etatsEvaluationArticle(idArticle);
    	  for(String e : etatsEvaluation) {
    		  if(e==null) {
    			  t=false;
    		  }
    	  }
    	  
    	  if(t) {
    		  Article article=articleRepository.findById(idArticle).get();
    		  article.setEtat("corrected");
    		  articleRepository.save(article);
    	  }
    	  
      }
      
      
      //permet à la comité éditorial de publier un article dont le statut est corrected(évaluer par les referees) ou modified(modifier par l'auteur d'aprés les remarques de jurees
      public String publierArticle(Long idArticle) {
    	  String message="";
    	  Article article=articleRepository.findById(idArticle).get();
    	  if(article.getEtat().equals("corrected")) {
    		  article.setEtat("accepted");
    		  article.setDecision("published");
    		  articleRepository.save(article);
    		  message="article publiée";
    	  }
    	  else if(article.getEtat().equals("modified")){
    		  article.setEtat("accepted");
    		  article.setDecision("published");
    		  articleRepository.save(article);
    		  message="article publiée";
    		  
    	  }
    	  else {
    		  message="impossible de publier l'article";
    	  }
    	  return message;
    	  
      }
      
    //permet à la comité éditorial de demander des modification sur un articles
      public String demanderModification(Long idArticle) {
    	  String message="";
    	  Article article=articleRepository.findById(idArticle).get();
    	  if(article.getEtat().equals("corrected")) {
    		  article.setEtat("request modification");
    		
    		  articleRepository.save(article);
    		  message="modifications demandées";
    	  }
    	  else {
    		  message="impossible de demander des modfications";
    	  }
    	  return message;
    			  
      }

      //permet d'afficher la liste des articles à modifier pour un auteur
        public List<Article> listArticleAuteurDemandeModifcation(Long id){
      	  return userRepository.listArticleAuteurDemandeModifcation(id);
      	  
        }
      //permet à un auteur de modifier l'article aprés la demande de la comité
        public String updateArticle(Long idArticle,Article article) {
        	String message="";
        	Article art= articleRepository.findById(idArticle).get();
        	if(art.getEtat().equals("request modification")) {
        		article.setIdArticle(idArticle);
        		article.setEtat("modified");
        		articleRepository.save(article);
        		
        		 message="modifications apportées";
        	}
        	return message;
        	
        }
        
        
    //permet à la comité éditorial de refuser l'article selon 2 cas
      //la comité a décider de refuser l'article suite aux commentaires des referees
      // la comité à refuser l'article car il n'est pas conforme avec la politique de la revue
      public String refuserArticle(Long idArticle) {
    	  String message="";
    	  Article article=articleRepository.findById(idArticle).get();
    	  if(article.getEtat().equals("corrected")) {
    		  article.setEtat("refused");
    		  article.setDecision("not published");
    		  articleRepository.save(article);
    		  message="article refusé";
    	  }
    	  else if(article.getEtat().equals("new")){
    		  article.setEtat("refused");
    		  article.setDecision("not published");
    		  articleRepository.save(article);
    		  message="article n'est pas conforme avec la politique de la revue";
    	  }
    	  else {
    		  message="impossible de refuser l'article";
    	  }
    	  return message;
    	  
      }

  	
}
