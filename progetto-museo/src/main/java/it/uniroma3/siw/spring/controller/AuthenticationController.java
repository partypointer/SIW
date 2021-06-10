package it.uniroma3.siw.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import it.uniroma3.siw.spring.controller.validator.AccountValidator;
import it.uniroma3.siw.spring.controller.validator.CollezioneValidator;
import it.uniroma3.siw.spring.controller.validator.CuratoreValidator;
import it.uniroma3.siw.spring.controller.validator.UserValidator;
import it.uniroma3.siw.spring.model.Account;
import it.uniroma3.siw.spring.model.User;
import it.uniroma3.siw.spring.service.AccountService;

@Controller
@SessionAttributes("accountCorrente")
public class AuthenticationController {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private AccountValidator accountValidator;
	
	private static final Logger logger = LoggerFactory.getLogger(CollezioneValidator.class);
    
	@RequestMapping(value = "/login", method = RequestMethod.GET) 
	public String showLoginForm(Model model) {
		Account account = (Account) model.getAttribute("accountCorrente");
		
		if(account != null) {
			logger.debug("\nMYINFO) Account username: " + account.getUsername() + "\n");
			if(checkIfIsAccountIsAdministrator(account)) return "admin/home";
			if(checkIfIsAccountIsDefault(account)) return "default/home";
		}
		else logger.debug("\nMYINFO) Account is currently NULL\n");

		return "loginForm";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET) 
	public String showRegisterForm (Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("account", new Account());
		return "registrationForm";
	}
	
    @RequestMapping(value = { "/register" }, method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("user") User user,
                 BindingResult userBindingResult,
                 @ModelAttribute("account") Account account,
                 BindingResult credentialsBindingResult,
                 Model model) {

        // validate user and credentials fields
        this.accountValidator.validate(account, userBindingResult);
        this.accountValidator.validate(account, credentialsBindingResult);

        // if neither of them had invalid contents, store the User and the Credentials into the DB
        if(!userBindingResult.hasErrors() && ! credentialsBindingResult.hasErrors()) {
            // set the user and store the credentials;
            // this also stores the User, thanks to Cascade.ALL policy
            account.setUser(user);
            accountService.saveAccount(account);
            return "registrationSuccessful";
        }
        return "registrationForm";
    }
	
	/* Se ha successo il login del curatore... */
	@RequestMapping(value = "/default", method = RequestMethod.GET)
    public String defaultAfterLogin(Model model) {
		String returnPath = "default/home";
        
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Account account = accountService.getAccount(userDetails.getUsername());
    	if(this.checkIfIsAccountIsAdministrator(account)) returnPath = "admin/home";

    	model.addAttribute("accountCorrente", account);
    	/* L'utente non è admin (in questo progetto, tutti i curatori che possono accedere sono anche admin! -> ci sono solo admin!) */
        return returnPath;
    }
	
	/** Controlla se l'account ha il ruolo di ADMIN **/
	public boolean checkIfIsAccountIsAdministrator(Account account) {
		if(account.getRuolo().equals(Account.ADMIN_RUOLO)) return true;
		return false;
	}
	
	/** Controlla se l'account ha il ruolo di DEFAULT USER **/
	public boolean checkIfIsAccountIsDefault(Account account) {
		if(account.getRuolo().equals(Account.DEFAULT_RUOLO)) return true;
		return false;
	}
}
