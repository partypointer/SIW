package it.uniroma3.siw.spring.model;
import java.time.LocalDateTime;

/*
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

https://mkyong.com/java/java-aes-encryption-and-decryption/#:~:text=The%20Advanced%20Encryption%20Standard%20(AES,%2C%20192%2C%20or%20256%20bits.
https://www.baeldung.com/java-aes-encryption-decryption
*/
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@NamedQuery(name = "findAllAccounts", query = "SELECT a FROM Account a")
@Data public class Account {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private	Long id;
	
	public static final String DEFAULT_RUOLO = "DEFAULT";
	public static final String ADMIN_RUOLO = "ADMIN";

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;
    
	@Column(nullable = false)
	private String ruolo;
    
    @Column(nullable = false)
	private	LocalDateTime dataCreazione;

    @OneToOne
    private Curatore proprietario;
    

	public Account(){
		this.dataCreazione = LocalDateTime.now();
		this.ruolo = this.getDefaultRuolo();
	}

	public Account(Curatore proprietario, String username, String password, String ruolo){
		this.dataCreazione = LocalDateTime.now();
		this.setUsername(username);
		this.setProprietario(proprietario);
		this.setPassword(password);
		this.setRuolo(ruolo);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public LocalDateTime getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(LocalDateTime dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public Curatore getProprietario() {
		return proprietario;
	}

	public void setProprietario(Curatore proprietario) {
		this.proprietario = proprietario;
	}

	public static String getDefaultRuolo() {
		return DEFAULT_RUOLO;
	}

	public static String getAdminRuolo() {
		return ADMIN_RUOLO;
	}

}
