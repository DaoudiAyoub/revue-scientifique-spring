package org.sid.service;

import java.util.List;

import org.sid.dao.EcrireArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service
public class EcrireArticleService {
	
	@Autowired
	private EcrireArticleRepository ecrireArticleRepository;
	//liste réduites des articles par nom Auteur
	 public List<Object> infoArticleReduitesParAuteur(String nom){
		 return ecrireArticleRepository.infoArticleReduitesParAuteur("%"+nom+"%");
	 }
		//liste réduites des articles par motCle
	 public List<Object> infoArticleReduitesMotCle(String mot){
		 return ecrireArticleRepository.infoArticleReduitesMotCle("%"+mot+"%");
	 }
	 
	//visualiser l’ensemble du contenu y compris le manuscrit de toutes les publications.
	 public List<Object> infoArticle(){
		 return ecrireArticleRepository.infoArticle();
	 }

}
