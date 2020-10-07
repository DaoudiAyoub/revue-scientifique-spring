package org.sid.controller;

import java.util.List;

import org.sid.beans.Article;
import org.sid.beans.EvaluationJuree;
import org.sid.beans.User;
import org.sid.dao.UserRepository;
import org.sid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin 
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	
	//permet à un auteur authentifié de voir ses articles ainsi que des suivre leurs état
	@RequestMapping(value="/auteur/{id}/articles",method=RequestMethod.GET)
	public List<Object> articleByAuteurId(@PathVariable Long id){
		
		return userService.articleByAuteurId(id);
	}
	
	//permet à un auteur d'un article encours d'évaluation( le statut n'est pas new / article pas encore publié ) de visualiser
		//les commentaires des différents referees 
	@RequestMapping(value="/auteur/articles/{id}/commentaires",method=RequestMethod.GET)
	public List<Object> rapportJureeByArticleId(@PathVariable Long id){
		return userService.rapportJureeByArticleId(id);
	}
	
	//le comité éditorial vérifie la conformité de l’article avec la politique de la revue.
	//pour cela il faut d'abord verifier si l'utilisateur authentifié à le role comité
	//apres il faut verifier si le statut de l'article à verifie est new.
	
	@RequestMapping(value="/comite/user/{idUser}/articles/{idArticle}",method=RequestMethod.PUT)
	public Article conformeArticle(@RequestBody Article article,@PathVariable Long idUser,@PathVariable Long idArticle) {
		return userService.conformeArticle(article,idUser,idArticle);
	}
	
	
	//permet d'afficher à la comité éditorial la liste des articles conforme avec la politique de la revue
	//=> article conforme= article pret à transferer aux referees
	@RequestMapping(value="/comite/conformearticles",method=RequestMethod.GET)
	public List<Article> listArticleConforme(){
		return userService.listArticleConforme();
	}
	
	
	//permet d'afficher pour la comité éditorial la liste des referees
	@RequestMapping(value="/comite/referees",method=RequestMethod.GET)
	public List<User> listReferees(){
		return userService.listReferees();
	}
	
	
	 //permet d'affecter un article dont le statut est conforme à 3 referees
	@RequestMapping(value="/comite/articles/{idArticle}/referees/{idReferee1}/{idReferee2}/{idReferee3}",method=RequestMethod.POST)
	public String affecterArticleReferees(@PathVariable Long idArticle,@PathVariable Long idReferee1,@PathVariable Long idReferee2
			,@PathVariable Long idReferee3) {
		return userService.affecterArticleReferees(idArticle,idReferee1,idReferee2,idReferee3);
	}
	
	//permet d'afficher la liste des articles affecter à un referees
	@RequestMapping(value="/referee/{id}/articles",method=RequestMethod.GET)
	public List<Article> listArticleReferee(@PathVariable Long id){
		return userService.listArticleReferee(id);
	}
	
	
	//permet à un referees d'evaluer un article sous les conditions suivante:
    //l'article doit avoir l'etat transferred
    //cette article n'a jamais été évalué par ce referée
	@RequestMapping(value="/referee/{idReferee}/articles/{idArticle}",method=RequestMethod.POST)
	public String evaluerArticle(@PathVariable Long idReferee,@PathVariable Long idArticle,
			@RequestBody EvaluationJuree evaluationJ) {
		return userService.evaluerArticle(idReferee,idArticle,evaluationJ);
	}
    
    //permet à la comité éditorial de publier un article dont le statut est corrected(évaluer par les referees) ou modified(modifier par l'auteur d'aprés les remarques de jurees
	@RequestMapping(value="/comite/article/{idArticle}/publier",method=RequestMethod.PUT)
	public String publierArticle(@PathVariable Long idArticle) {
		return userService.publierArticle(idArticle);
	}
	
	//permet à la comité éditorial de demander des modification sur un articles
	@RequestMapping(value="/comite/article/{idArticle}/demandemodification",method=RequestMethod.PUT)
	public String demanderModification(@PathVariable Long idArticle) {
		return userService.demanderModification(idArticle);
	}
	
	//permet d'afficher la liste des articles à modifier pour un auteur
	@RequestMapping(value="/auteur/{idAuteur}/demandemodification",method=RequestMethod.GET)
	public List<Article> listArticleAuteurDemandeModifcation(@PathVariable Long idAuteur){
		return userService.listArticleAuteurDemandeModifcation(idAuteur);
	}
	
	//permet à un auteur de modifier l'article aprés la demande de la comité
	@RequestMapping(value="/auteur/article/{idArticle}/modifier",method=RequestMethod.PUT)
	public String updateArticle(@PathVariable Long idArticle,@RequestBody Article article) {
		return userService.updateArticle(idArticle,article);
	}
	
	
	
	//permet à la comité éditorial de refuser l'article selon 2 cas
    //la comité a décider de refuser l'article suite aux commentaires des referees
    // la comité à refuser l'article car il n'est pas conforme avec la politique de la revue
	@RequestMapping(value="/comite/article/{idArticle}/refuser",method=RequestMethod.POST)
	public String refuserArticle(@PathVariable Long idArticle) {
		return userService.refuserArticle(idArticle);
	}
	
	
	
	

	
	
}
