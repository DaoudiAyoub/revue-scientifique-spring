package org.sid.controller;

import java.util.List;

import org.sid.service.EcrireArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin 
public class EcrireArticleController {
	
	@Autowired
	private EcrireArticleService ecrireArticleService;
	
	
	//liste réduites des articles par nom Auteur
	@RequestMapping(value="/nonauth/articles",method=RequestMethod.GET)
	public List<Object> infoArticleReduitesParAuteur(@RequestParam(name="nom",defaultValue="")String nom){
		return ecrireArticleService.infoArticleReduitesParAuteur(nom);
	}
	
	//liste réduites des articles par motCle
	@RequestMapping(value="/nonauth/articlesparmot",method=RequestMethod.GET)
	public List<Object> infoArticleReduitesMotCle(@RequestParam(name="mot",defaultValue="")String mot){
		return ecrireArticleService.infoArticleReduitesMotCle(mot);
	}
		
	//visualiser l’ensemble du contenu y compris le manuscrit de toutes les publications.
		// pour un utilisateur authentifié 
	@RequestMapping(value="/auth/articles",method=RequestMethod.GET)
	public List<Object> infoArticle(){
		return ecrireArticleService.infoArticle();
				}

}
