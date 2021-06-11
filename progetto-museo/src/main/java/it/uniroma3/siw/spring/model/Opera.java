package it.uniroma3.siw.spring.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "findAllOpere", query = "SELECT o FROM Opera o")
public class Opera {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private	Long id;

    @Column(nullable = false)
	private	String titolo;

    @Column(nullable = false)
	private	Long annoRealizzazione;

	private	String descrizione;
    
    @Column(nullable = false)
	private String link;

    @ManyToOne(optional = true)
    private Collezione collezione;
    
    @ManyToOne
    private Artista artista;
    
    
    public Opera(){
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public Long getAnnoRealizzazione() {
		return annoRealizzazione;
	}

	public void setAnnoRealizzazione(Long annoRealizzazione) {
		this.annoRealizzazione = annoRealizzazione;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Collezione getCollezione() {
		return collezione;
	}

	public void setCollezione(Collezione collezione) {
		this.collezione = collezione;
	}

	public Artista getArtista() {
		return artista;
	}

	public void setArtista(Artista artista) {
		this.artista = artista;
	}
    
}
