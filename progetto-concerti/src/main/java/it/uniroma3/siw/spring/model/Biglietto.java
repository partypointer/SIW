package it.uniroma3.siw.spring.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Biglietto {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private	Long id;
	
	@Column(nullable=false)
	LocalDate dataAcquisizione;

	@ManyToOne
	private TipologiaPosto tipologiaPosto;
	
	@ManyToOne
	private Account account;
	
	
	public Biglietto(){
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataAcquisizione() {
		return dataAcquisizione;
	}

	public void setDataAcquisizione(LocalDate dataAcquisizione) {
		this.dataAcquisizione = dataAcquisizione;
	}

	public TipologiaPosto getTipologiaPosto() {
		return tipologiaPosto;
	}

	public void setTipologiaPosto(TipologiaPosto tipologiaPosto) {
		this.tipologiaPosto = tipologiaPosto;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
}
