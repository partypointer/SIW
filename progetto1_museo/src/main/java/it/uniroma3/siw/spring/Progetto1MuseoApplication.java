package it.uniroma3.siw.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={
		"it.uniroma3.siw.spring",
		"it.uniroma3.siw.spring.controller",
		"it.uniroma3.siw.spring.model",
		"it.uniroma3.siw.spring.repository",
		"it.uniroma3.siw.spring.service",
		"it.uniroma3.siw.spring.view"})
public class Progetto1MuseoApplication {

	public static void main(String[] args) {
		SpringApplication.run(Progetto1MuseoApplication.class, args);
	}

}
