package org.sid.controller;

import java.util.List;

import org.sid.beans.Article;
import org.sid.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin 
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	//ajouter un article par un auteur
	@RequestMapping(value="/auteur/articles",method=RequestMethod.POST)
	public Article ajouterArticle(@RequestBody Article article) {
		return articleService.ajouterArticle(article);
	}
	//supprimer un article par un auteur dont le statue est new
	@RequestMapping(value="/auteur/articles/{id}",method=RequestMethod.DELETE)
	public boolean supprimerArticle(@PathVariable Long id) {
		 return articleService.supprimerArticle(id);
		
	}
	//modifier un article par un auteur dont le statue est new
	@RequestMapping(value="/auteur/articles/{id}",method=RequestMethod.PUT)
	public Article modifierArticle(@RequestBody Article article,@PathVariable Long id) {
		return articleService.modifierArticle(article,id);
	}
	
	

}
