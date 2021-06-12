package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.model.Opera;
import it.uniroma3.siw.spring.repository.OperaRepository;

@Service
public class OperaService {
	
	@Autowired
	private OperaRepository operaRepository; 
	
	@Transactional
	public Opera inserisci(Opera opera) {
		return operaRepository.save(opera);
	}

	@Transactional
	public List<Opera> tutti() {
		return (List<Opera>) operaRepository.findAll();
	}
	
	@Transactional
	public void cancellaPerId(Opera opera) {
		operaRepository.deleteById(opera.getId());
	}

	@Transactional
	public Opera operaPerId(Long id) {
		Optional<Opera> optional = operaRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExists(Opera opera) {
		List<Opera> opere = this.operaRepository.findByTitolo(opera.getTitolo());
		if (opere.size() > 0)
			return true;
		else 
			return false;
	}
	
	@Transactional
	/** Si suppone che l'ID della collezione sia VALIDO ed appartenente ad una
	 * Collezione esistente **/
	public boolean updateCollezioneIdByOperaId(Collezione collezione, Long idOpera) {
		if(collezione != null && idOpera != null) {
			int opereModificate = this.operaRepository.updateCollezioneIdByOperaId(collezione, idOpera);
			if(opereModificate == 1) return true;
		}
		return false;
	}
	
	@Transactional
	/** Rimuove l'id della collezione dall'opera con idOpera **/
	public boolean removeCollezioneIdByOperaId(Long idOpera) {
		if(idOpera != null) {
			int opereModificate = this.operaRepository.removeCollezioneIdByOperaId(idOpera);
			if(opereModificate == 1) return true;
		}
		return false;
	}
	
}
