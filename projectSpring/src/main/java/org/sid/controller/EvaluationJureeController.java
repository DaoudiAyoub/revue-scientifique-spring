package org.sid.controller;

import java.util.List;

import org.sid.dao.EvaluationJureeRepository;
import org.sid.service.EvaluationJureeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin 
public class EvaluationJureeController {

	@Autowired 
	private EvaluationJureeService evaluationJureeService;
	
}
