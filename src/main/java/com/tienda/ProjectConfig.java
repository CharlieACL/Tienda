
package com.tienda;

import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class ProjectConfig implements WebMvcConfigurer
{
    /* Los siguientes métodos son para incorporar el tema de internacionalizacion en el projecto */
    
    @Bean
    public LocaleResolver localeResolver()
    {
        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.getDefault());
        slr.setLocaleAttributeName("session.current.locale");
        slr.setTimeZoneAttributeName("session.current.timezone");
        
        return slr;
    }
    
    /* localeCahengeInterceptor se utiliza para crear un interceptor de cambio de idioma */
    /* Metodos van en minuscula y variables en mayuscula*/
    
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() /* Metodo en minucula locale*/
    {
        var lci = new LocaleChangeInterceptor(); /* en variable en mayuscula Locale*/
                lci.setParamName("lang");
                return lci;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(localeChangeInterceptor());
    }
    
    //Bean para poder acceder a los Messages.properties en código...

    @Bean("messageSource")

    public MessageSource messageSource() 
    {
        ResourceBundleMessageSource messageSource= new ResourceBundleMessageSource();
        messageSource.setBasenames("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
