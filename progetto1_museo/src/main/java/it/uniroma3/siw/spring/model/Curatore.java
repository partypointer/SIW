package it.uniroma3.siw.spring.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@NamedQuery(name = "findAllCuratori", query = "SELECT c FROM Curatore c")
public class Curatore {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @Column(nullable = false)
    private LocalDateTime dataNascita;

    @Column(nullable = false)
    private String luogoNascita;

    @Column(nullable = false)
    private String numeroTelefono;

    @Column(nullable = false)
    private String matricola;
    
    @OneToMany (mappedBy = "curatore", fetch = FetchType.EAGER)
    private ArrayList<Collezione> collezioni;
	
    @OneToOne (mappedBy = "proprietario", cascade = {CascadeType.REMOVE})
    Account account;
    
	public Curatore(){
		collezioni = new ArrayList<Collezione>();
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

	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}

	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}

	public ArrayList<Collezione> getCollezioni() {
		return collezioni;
	}

	public void setCollezioni(ArrayList<Collezione> collezioni) {
		this.collezioni = collezioni;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
