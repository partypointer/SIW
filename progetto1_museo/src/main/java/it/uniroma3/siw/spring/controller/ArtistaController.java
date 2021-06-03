package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
// import javax.validation.Valid;

import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.service.ArtistaService;
import it.uniroma3.siw.spring.controller.ArtistaValidator;

@Controller
public class ArtistaController {
	
	@Autowired
	private ArtistaService artistaService;

	@Autowired
	private ArtistaValidator artistaValidator;
	
	
	/** Restituisce la pagina con la form per aggiungere un Artista **/
	@RequestMapping(value="/addArtista", method = RequestMethod.GET)
	public String newArtista(Model model) {
		model.addAttribute("artista", new Artista());
		return "formArtista.html";
	}

	@RequestMapping(value = "/artista/{id}", method = RequestMethod.GET)
	public String getPersona(@PathVariable("id") Long id, Model model) {
		model.addAttribute("persona", this.artistaService.findById(id));
		return "persona.html";
	}
	
	/** Restituisce **/
	@RequestMapping(value = "/artista", method = RequestMethod.POST)
	public String newArtista(@Validated @ModelAttribute("artista") Artista artista,
	Model model, BindingResult bindingResult) {
		this.artistaValidator.validate(artista, bindingResult);
		if (!bindingResult.hasErrors()) { // se i dati sono corretti
			this.artistaService.save(artista); // salvo un oggetto persona
		    model.addAttribute("artisti", this.artistaService.findAll());
		    return "artisti.html"; // presenta un pagina con l'elenco di tutti gli artisti
		}else return "artistaForm.html"; // ci sono errori, torna alla form iniziale
	}
	
}
