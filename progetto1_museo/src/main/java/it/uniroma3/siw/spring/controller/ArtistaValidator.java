package it.uniroma3.siw.spring.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.service.ArtistaService;

@Component
public class ArtistaValidator implements Validator{
	
	@Autowired
	private ArtistaService artistaService;
	
	public boolean validate(HttpServletRequest request){

	  	String nome = request.getParameter("nome");
	  	String cognome = request.getParameter("cognome");
	  	HashMap<String, String> messaggiErrore = new HashMap<>();
	  	
	  	/* Controllo delle stringhe vuote */
	  	if(!nome.equals("") && !cognome.equals("")) {
		  	return true;
	  	}
	  	else {
	  		/* Se ci sono errori, predisponiamo di nuovo la form con degli errori presenti
			 * nella mappa messaggiErrore. */
	  		if(nome.equals("")) {
	  			messaggiErrore.put("nome", "Il nome è un campo obbligatorio (*)");
	  			request.setAttribute("cognome", cognome); // Così il valore di default verrà riempito
	  		}
	  		if(nome.equals("")) {
	  			messaggiErrore.put("cognome", "Il cognome è un campo obbligatorio (*)");
	  			request.setAttribute("nome", nome); // Così il valore di default verrà riempito
	  		}
	  		request.setAttribute("errori", messaggiErrore);
	  		return false;
	  	}

	}


	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome", "required");
		
		if(this.artistaService.alreadyExists((Artista) target)) {
			errors.reject("duplicato");
		}
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Artista.class.equals(aClass);
	}
	
}
