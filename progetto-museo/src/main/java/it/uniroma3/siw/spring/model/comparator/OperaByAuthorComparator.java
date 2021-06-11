package it.uniroma3.siw.spring.model.comparator;

import java.util.Comparator;

import it.uniroma3.siw.spring.model.Opera;

public class OperaByAuthorComparator implements Comparator<Opera>{
	
	/** Compara prima il nome artistico, poi il cognome ed infine il nome. **/
	public int compare(Opera o1,Opera o2){
		String nomeArtistico1 = o1.getArtista().getNomeArtistico();
		String nomeArtistico2 = o2.getArtista().getNomeArtistico();
		
		if(nomeArtistico1 != null && nomeArtistico2 != null) {
			return nomeArtistico1.compareTo(nomeArtistico2);
		}

		String cognome1 = o1.getArtista().getCognome();
		String cognome2 = o2.getArtista().getCognome().toString();

		if(cognome1 != null && cognome2 != null) {
			return cognome1.compareTo(cognome2);
		}
		
		String nome1 = o1.getArtista().getNome();
		String nome2 = o2.getArtista().getNome();

		if(nome1 != null && nome2 != null) {
			return nome1.compareTo(nome2);
		}
		
		return 0;
	}  
}
