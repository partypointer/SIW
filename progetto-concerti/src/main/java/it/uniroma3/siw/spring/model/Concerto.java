package it.uniroma3.siw.spring.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Concerto {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private	Long id;

	@Column(nullable=false)
	private String nome;
	
	private String descrizione;
	
	@Column(nullable=false)
	private LocalDateTime dataOraInizio;

	@Column(nullable=false)
	private String indirizzoLocation;
	
	@OneToMany(mappedBy="concerto")
	private List<TipologiaPosto> tipologiaPosti;
	
	@OneToMany(mappedBy="concerto")
	private List<Partecipazione> partecipazioni;
	
	
	public Concerto(){
		this.tipologiaPosti = new ArrayList<TipologiaPosto>();
		this.partecipazioni = new ArrayList<Partecipazione>();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public LocalDateTime getDataOraInizio() {
		return dataOraInizio;
	}

	public void setDataOraInizio(LocalDateTime dataOraInizio) {
		this.dataOraInizio = dataOraInizio;
	}

	public String getIndirizzoLocation() {
		return indirizzoLocation;
	}

	public void setIndirizzoLocation(String indirizzoLocation) {
		this.indirizzoLocation = indirizzoLocation;
	}

	public List<TipologiaPosto> getTipologiaPosti() {
		return tipologiaPosti;
	}

	public void setTipologiaPosti(List<TipologiaPosto> tipologiaPosti) {
		this.tipologiaPosti = tipologiaPosti;
	}

	public List<Partecipazione> getPartecipazioni() {
		return partecipazioni;
	}

	public void setPartecipazioni(List<Partecipazione> partecipazioni) {
		this.partecipazioni = partecipazioni;
	}

}
