package it.uniroma3.siw.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.spring.model.Artista;

public interface ArtistaRepository extends CrudRepository<Artista, Long>{
	
	/* Il framework genererà i metodi in base alla semantica dei rispettivi nomi come descritto in:
	 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference */
	
	/* Già presenti in CrudRepository:

	public Artista save(Artista a);
	
	public List<Artista> findAll();
	
	public Optional<Artista> findById(Long id);
	*/
	
	public List<Artista> findByNome(String nome);

	public List<Artista> findByCognome(String nome);

	public List<Artista> findByNomeOrCognome(String nome, String cognome);
	
	public List<Artista> findByNomeAndCognome(String nome, String cognome);
}
