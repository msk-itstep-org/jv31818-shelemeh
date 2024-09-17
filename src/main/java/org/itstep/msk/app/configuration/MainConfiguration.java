package org.itstep.msk.app.configuration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * @author shele
 * Config for template register thymeleaf
 */
@Configuration
@EnableCaching
public class MainConfiguration {
    private static final String CUSTOM_CACHE = "storeCache";

    @Bean
    public SpringResourceTemplateResolver thymeleafResolver() {
        final SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setPrefix("classpath:/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        return resolver;
    }

}


