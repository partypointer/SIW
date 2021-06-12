package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.model.Opera;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OperaRepository extends CrudRepository<Opera, Long> {

	public List<Opera> findByTitolo(String nome);

	@Query("UPDATE Opera o SET o.collezione = ?1 WHERE o.id = ?2")
	@Modifying
	public int updateCollezioneIdByOperaId(Collezione collezione, Long idOpera);

	public List<Opera> findByCollezione(Long idCollezione);
	
	@Query("UPDATE Opera o SET o.collezione = NULL WHERE o.id = ?1")
	@Modifying
	public int removeCollezioneIdByOperaId(Long idOpera);
}