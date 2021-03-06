package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.spring.model.Account;
import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.repository.AccountRepository;

@Service
public class AccountService {
	
    @Autowired
    protected PasswordEncoder passwordEncoder;

	@Autowired
	protected AccountRepository accountRepository;
	
	@Transactional
	public Account getAccount(Long id) {
		Optional<Account> result = this.accountRepository.findById(id);
		return result.orElse(null);
	}

	@Transactional
	public Account getAccount(String username) {
		Optional<Account> result = this.accountRepository.findByUsername(username);
		return result.orElse(null);
	}
		
    @Transactional
    public Account saveAccount(Account Account) {
        Account.setRuolo(Account.DEFAULT_RUOLO);
        Account.setPassword(this.passwordEncoder.encode(Account.getPassword()));
        return this.accountRepository.save(Account);
    }
    
}
