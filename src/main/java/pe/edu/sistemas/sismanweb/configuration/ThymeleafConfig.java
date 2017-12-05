package pe.edu.sistemas.sismanweb.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration 
public class ThymeleafConfig {

	@Bean 
	public SpringResourceTemplateResolver templateResolver() {
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		resolver.setPrefix("/views/"); //tambien se puede usar classpath:/paginas/
		resolver.setSuffix(".html");
		resolver.setTemplateMode("HTML5");
		resolver.setCharacterEncoding("UTF-8");
		resolver.setCacheable(false);
		return resolver;
	}
	
	@Bean 
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolver(templateResolver());
		engine.addDialect(new LayoutDialect());
		return engine;
	}
	
	@Bean 
	public ThymeleafViewResolver thymeleafViewResolver() {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine());
		return resolver;
	}
	
}