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

@Entity
@NamedQuery(name = "findAllAccounts", query = "SELECT a FROM Account a")
public class Account {

	/*
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private	Long id;
	*/

	@Id
    private String email;

    @Column(nullable = false)
	private String password;

    @Column(nullable = false)
	private	LocalDateTime dataCreazione;

    @OneToOne
    private Curatore proprietario;

	public Account(){
		this.dataCreazione = LocalDateTime.now();
	}

	public Account(Curatore proprietario, String password){
		this.dataCreazione = LocalDateTime.now();
		this.setProprietario(proprietario);
		this.setPassword(password);
	}
	
	/*
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	 */

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Curatore getProprietario() {
		return proprietario;
	}

	public void setProprietario(Curatore proprietario) {
		this.proprietario = proprietario;
	}

	
	/* Encrypt - Decrypt */
	
	/* Genera una secret key CASUALE da 256 bits di AES */
    /*public static SecretKey getAESKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256, SecureRandom.getInstanceStrong());
        return keyGen.generateKey();
    }*/
    
    /* Generazione del vettore pseudo-casuale IV (Initialization Vector) da 16 Byte */
    /*public static IvParameterSpec generateIv() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }*/

	/*public String encryptPassword(String password){
		return DigestUtils.sha1Hex(password);
	}

	public String decryptPassword(String encryptedPassword){
		String password = encryptedPassword;
		//...
		return DigestUtils.;
	}*/
	
}
