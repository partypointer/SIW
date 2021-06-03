package it.uniroma3.siw.spring.model;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@NamedQuery(name = "findAllCollezioni", query = "SELECT c FROM Collezione c")
public class Collezione {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private	Long id;
	
    @Column(nullable = false)
	private String nome;
    
	private String descrizione;

    @OneToOne
	Curatore curatore;
    
    @OneToMany (mappedBy = "collezione")
    ArrayList<Opera> opere;
    
    public Collezione(){
    	this.opere = new ArrayList<Opera>();
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

	public Curatore getCuratore() {
		return curatore;
	}

	public void setCuratore(Curatore curatore) {
		this.curatore = curatore;
	}

	public ArrayList<Opera> getOpere() {
		return opere;
	}

	public void setOpere(ArrayList<Opera> opere) {
		this.opere = opere;
	}
    
}
