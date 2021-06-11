package it.uniroma3.siw.configuration.language;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import it.uniroma3.siw.spring.controller.validator.CollezioneValidator;

@Component
public class LanguageResolver {
	//https://www.baeldung.com/spring-boot-internationalization
	
	private static final Logger logger = LoggerFactory.getLogger(CollezioneValidator.class);
	
	@Bean
	/** The LocaleResolver interface has implementations that determine the current locale based
	 * on the session, cookies, the Accept-Language header, or a fixed value. **/
	public LocaleResolver localeResolver() {
		logger.debug("\nMYINFO) invoked localeResolver from LanguageResolver\n");
	    SessionLocaleResolver slr = new SessionLocaleResolver();
	    slr.setDefaultLocale(Locale.ITALIAN);
	    return slr;
	}
	
	@Bean
	/** will switch to a new locale based on the value of the lang parameter appended to a request* */
	public LocaleChangeInterceptor localeChangeInterceptor() {
		logger.debug("\nMYINFO) invoked localeChangeInterceptor from LanguageResolver\n");
	    LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
	    lci.setParamName("lang");
	    return lci;
	}
}
