package it.uniroma3.siw.spring.controller;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.controller.validator.OperaValidator;
import it.uniroma3.siw.spring.model.Opera;
import it.uniroma3.siw.spring.service.OperaService;

@Controller
public class OperaController {
	
	@Autowired
	private OperaService operaService;
	
    @Autowired
    private OperaValidator operaValidator;
        
    /*
    @RequestMapping(value="/admin/opera", method = RequestMethod.GET)
    public String addOpera(Model model) {
    	model.addAttribute("opera", new Opera());
        return "operaForm";
    }

    @RequestMapping(value = "/opera/{id}", method = RequestMethod.GET)
    public String getPersona(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("opera", this.operaService.operaPerId(id));
    	return "opera";
    }

    @RequestMapping(value = "/opera", method = RequestMethod.GET)
    public String getProdotti(Model model) {
    		model.addAttribute("opere", this.operaService.tutti());
    		return "opere";
    }
    
    @RequestMapping(value = "/admin/opera", method = RequestMethod.POST)
    public String addOpera(@ModelAttribute("opera") Opera opera, 
    									Model model, BindingResult bindingResult) {
    	this.operaValidator.validate(opera, bindingResult);
        if (!bindingResult.hasErrors()) {
        	this.operaService.inserisci(opera);
            model.addAttribute("opere", this.operaService.tutti());
            return "opere";
        }
        return "operaForm";
    }
    */
    

    @RequestMapping(value = "/admin/addOpera", method = RequestMethod.GET)
    public String addOpera(Model model) {
    		model.addAttribute("opera", new Opera());
    		/* Se viene premuto il bottone della sezione, viene restituita la pagina */
    		return "admin/addOpera";
    }
    
    @RequestMapping(value = "/admin/addOpera", method = RequestMethod.POST)
    public String addOpera(@ModelAttribute("opera") Opera opera, 
    									Model model, BindingResult bindingResult) {
    	this.operaValidator.validate(opera, bindingResult);
        if (!bindingResult.hasErrors()) {
        	this.operaService.inserisci(opera);
    		/* Se l'inserimento dei dati nella form è corretto, viene mostrata la pagina di successo dell'operazione */
            return "successfulOperation";
        }
		/* Se l'inserimento dei dati non è corretto, viene effettuato un refresh */
        return "admin/addOpera";
    }
}
