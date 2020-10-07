package org.sid.service;

import java.util.List;
import java.util.Optional;

import org.sid.beans.Article;
import org.sid.beans.EcrireArticle;
import org.sid.dao.ArticleRepository;
import org.sid.dao.EcrireArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private EcrireArticleRepository ecrireArticleRepository;
	
	
    //lors de l'ajout d'un article, on récupere du coté client l'article à ajouter avec la liste des auteur/co-auteurs correspondant.
	public Article ajouterArticle(Article article) {
		article.setEtat("new");
		Article art=articleRepository.save(article);
		
		for(EcrireArticle e : article.getEcrits()) {
			EcrireArticle ecrire=new EcrireArticle();
			ecrire.setArticle(article);
			ecrire.setUser(e.getUser());
			ecrireArticleRepository.save(ecrire);
		}
		return art;
	}
	
	//la suppression d'un article dont l'evaluation n'a pas commencé
	public boolean supprimerArticle(Long id) {
		Optional<Article> article =articleRepository.findById(id);
		if(article.get().getEtat().equals("new")) {
			for(EcrireArticle e : article.get().getEcrits()){
				ecrireArticleRepository.deleteById(e.getIdEcrire());
			}
			articleRepository.deleteById(id);
			return true;
		}
		else {
			return false;
		}
		}
	
	//la modification d'un article dont l'evaluation n'a pas commencé
	public Article modifierArticle(Article article,Long id) {
		Article art=articleRepository.findById(id).get();
		Article arrr=null;
		if(art.getEtat().equals("new")) {
			article.setIdArticle(id);
			 arrr=articleRepository.save(article);
		}
		return arrr;
		
	}
	
	
}
