package it.uniroma3.siw.configuration.language;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import it.uniroma3.siw.configuration.language.LanguageResolver;
import it.uniroma3.siw.spring.controller.validator.CollezioneValidator;

@Configuration
public class LanguageConfiguration implements WebMvcConfigurer{
	@Autowired
	private LanguageResolver languageResolver;

	private static final Logger logger = LoggerFactory.getLogger(CollezioneValidator.class);
	
	//@Override
	public void addInterceptors(InterceptorRegistry registry) {
		logger.debug("\nMYINFO) invoked addInterceptors from LanguageConfiguration\n");
	    registry.addInterceptor(this.languageResolver.localeChangeInterceptor());
	}
}
