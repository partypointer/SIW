package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.repository.ArtistaRepository;

@Service
public class ArtistaService {
	
	@Autowired
	private ArtistaRepository artistaRepository;

	@Transactional
	public Artista save(Artista persona) {
		return artistaRepository.save(persona);
	}

	@Transactional
	public List<Artista> findByNomeAndCognome(String nome, String cognome) {
		return artistaRepository.findByNomeAndCognome(nome, cognome);
	}

	@Transactional
	public Artista findById(Long id) {
		Optional<Artista> artistaOptional = artistaRepository.findById(id);
		if(artistaOptional.isPresent()){
			return artistaOptional.get();
		}
		return null;
	}
	
	/** Questo metodo non è transazionale in quanto chiama un metodo transazionale! [ findById(Long id) ]**/
	public boolean alreadyExists(Artista a){
		if(findById(a.getId()) != null) return true;
		else return false;
	}

	@Transactional
	public List<Artista> findAll() {
		return (List<Artista>) artistaRepository.findAll();
	}


	@Transactional
	public boolean alreadyExistsByNomeAndCognome(Artista a){
		List<Artista> artisti = artistaRepository.findByNomeAndCognome(a.getNome(), a.getCognome());
		if(artisti.size() > 0) return true;
		else return false;
	}
	
}
