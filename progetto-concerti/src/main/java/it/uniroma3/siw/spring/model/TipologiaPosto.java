package it.uniroma3.siw.spring.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class TipologiaPosto {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private	Long id;

	@Column(nullable=false)
	private String nome;

	private String descrizione;
	
	@Column(nullable=false)
	private Integer postiDisponibili;
	
	@Column(nullable=false)
	private Long prezzoUnitario;
	
	@ManyToOne
	private Concerto concerto;
	
	@OneToMany(mappedBy="tipologiaPosto")
	private List<Biglietto> biglietti;
	
	
	public TipologiaPosto() {
		this.biglietti = new ArrayList<Biglietto>();
	}
	

	/** Restituisce il prezzo totale in base alla quantità di posti.
	 * Se la quantità di posti è minore di 1, restituisce il long pari a zero. (0L) **/
	public Long prezzoTotale(int quant){
		if(quant > 0) return this.prezzoUnitario * quant;
		return 0L;
	}
	
	/** Controlla se c'è disponibilità e poi decrementa i posti.
	 * Se non vi è disponibilità per il numero di posti in input, non decrementa
	 * il numero di posti e ritorna false. **/
	public boolean riduciPosti(int quant){
		if(checkDisponibilita(quant)){
			this.postiDisponibili -= quant;
			return true;
		}
		return false;
	}
	
	/** Controlla se sono presenti n posti.
	 * Per esempio, se numeroPosti = 1 controlla se c'è un posto libero. **/
	public boolean checkDisponibilita(int numeroPosti){
		if(this.postiDisponibili - numeroPosti >= 0) return true;
		return false;
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

	public Integer getPostiDisponibili() {
		return postiDisponibili;
	}

	public void setPostiDisponibili(Integer postiDisponibili) {
		this.postiDisponibili = postiDisponibili;
	}

	public Long getPrezzoUnitario() {
		return prezzoUnitario;
	}

	public void setPrezzoUnitario(Long prezzoUnitario) {
		this.prezzoUnitario = prezzoUnitario;
	}

	public Concerto getConcerto() {
		return concerto;
	}

	public void setConcerto(Concerto concerto) {
		this.concerto = concerto;
	}
	
}
