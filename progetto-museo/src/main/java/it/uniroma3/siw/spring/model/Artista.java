package it.uniroma3.siw.spring.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQuery(name = "findAllArtisti", query = "SELECT a FROM Artista a")
public class Artista {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private	Long id;

    private String nomeArtistico;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @Column(nullable = false)
    private LocalDateTime dataNascita;

    @Column(nullable = false)
    private String luogoNascita;

    private LocalDateTime dataMorte;

    private String luogoMorte;

    @Column(nullable = false)
    String nazionalita;
    
    @OneToMany (mappedBy = "artista", fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE})
    private List<Opera> opere;

    
	public Artista(){
		this.opere = new ArrayList<Opera>();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNomeArtistico() {
		return nomeArtistico;
	}


	public void setNomeArtistico(String nomeArtistico) {
		this.nomeArtistico = nomeArtistico;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCognome() {
		return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	public LocalDateTime getDataNascita() {
		return dataNascita;
	}


	public void setDataNascita(LocalDateTime dataNascita) {
		this.dataNascita = dataNascita;
	}


	public String getLuogoNascita() {
		return luogoNascita;
	}


	public void setLuogoNascita(String luogoNascita) {
		this.luogoNascita = luogoNascita;
	}


	public LocalDateTime getDataMorte() {
		return dataMorte;
	}


	public void setDataMorte(LocalDateTime dataMorte) {
		this.dataMorte = dataMorte;
	}


	public String getLuogoMorte() {
		return luogoMorte;
	}


	public void setLuogoMorte(String luogoMorte) {
		this.luogoMorte = luogoMorte;
	}


	public String getNazionalita() {
		return nazionalita;
	}


	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}


	public List<Opera> getOpere() {
		return opere;
	}


	public void setOpere(List<Opera> opere) {
		this.opere = opere;
	}
	
}
