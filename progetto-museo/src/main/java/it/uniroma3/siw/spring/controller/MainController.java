package it.uniroma3.siw.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@RequestMapping(value = {"/", "index", "home"}, method = RequestMethod.GET)
	public String index(Model model) {
			return "index";
	}

	@RequestMapping(value = {"/error"}, method = RequestMethod.GET)
	public String error(Model model) {
			return "error";
	}
	
	@RequestMapping(value = {"/explore", "/esplora"}, method = RequestMethod.GET)
	public String explore(Model model) {
			return "explore";
	}
}