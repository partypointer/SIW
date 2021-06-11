package it.uniroma3.siw.spring.model.comparator;

import java.util.Comparator;

import it.uniroma3.siw.spring.model.Opera;

public class OperaByAnnoDiRealizzazioneComparator implements Comparator<Opera>{
	public int compare(Opera o1, Opera o2){
		return o1.getAnnoRealizzazione().compareTo(o2.getAnnoRealizzazione());
	}  
}
