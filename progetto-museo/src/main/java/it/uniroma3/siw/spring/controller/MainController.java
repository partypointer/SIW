package it.uniroma3.siw.spring.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.model.comparator.OperaByArtistaComparator;
import it.uniroma3.siw.spring.model.comparator.OperaByAnnoDiRealizzazioneComparator;
import it.uniroma3.siw.spring.service.OperaService;

@Controller
public class MainController {
	
	@Autowired
	private OperaService operaService;

	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public String index(Model model) {
			return "index";
	}

	@RequestMapping(value = {"/error"}, method = RequestMethod.GET)
	public String error(Model model) {
			return "error";
	}

	@RequestMapping(value = {"/explore"}, method = RequestMethod.GET)
	public String explore(Model model) {
	    	model.addAttribute("opere", operaService.tutti());
			return "explore";
	}
	
	@RequestMapping(value = {"/explore/byArtista"}, method = RequestMethod.GET)
	public String exploreByAuthor(Model model) {
	    	model.addAttribute("opere", operaService.tutti());
	        model.addAttribute("byArtista", new OperaByArtistaComparator());
			return "exploreByArtista";
	}
	
	@RequestMapping(value = {"/explore/byAnnoDiRealizzazione"}, method = RequestMethod.GET)
	public String exploreByAnnoDiRealizzazione(Model model) {
	    	model.addAttribute("opere", operaService.tutti());
	        model.addAttribute("byAnnoDiRealizzazione", new OperaByAnnoDiRealizzazioneComparator());
			return "exploreByAnnoDiRealizzazione";
	}
}