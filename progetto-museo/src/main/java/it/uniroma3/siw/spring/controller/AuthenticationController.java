package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.controller.validator.AccountValidator;
import it.uniroma3.siw.spring.controller.validator.CuratoreValidator;
import it.uniroma3.siw.spring.model.Account;
import it.uniroma3.siw.spring.service.AccountService;

@Controller
public class AuthenticationController {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private CuratoreValidator curatoreValidator;
	
	@Autowired
	private AccountValidator accountValidator;

	
	@RequestMapping(value = "/loginCuratori", method = RequestMethod.GET) 
	public String showLoginForm (Model model) {
		return "loginCuratori";
	}

	@RequestMapping(value = "/default", method = RequestMethod.GET)
    public String defaultAfterLogin(Model model) {
        
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Account account = accountService.getAccount(userDetails.getUsername());
    	if (account.getRuolo().equals(Account.ADMIN_RUOLO)) {
            return "admin/home";
        }
        return "index";
    }
}
