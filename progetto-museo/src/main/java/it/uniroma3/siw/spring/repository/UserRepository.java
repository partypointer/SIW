package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	public List<User> findByCognomeAndNome(String cognome, String nome);
}