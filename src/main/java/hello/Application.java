package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
// put these back in when using jpamappings
// @EnableJpaRepositories(basePackages = "jpamappings")
// @EntityScan(basePackages = "jpamappings")
// Shouldn't need this because normally you would add @EnableWebMvc
// for a Spring MVC app, but Spring Boot adds it automatically
// when it sees spring-webmvc on the classpath.
// This flags the application as a web application and activates
// key behaviors such as setting up a DispatcherServlet.
// @EnableWebMvc
@ComponentScan
// comment this out when building a jar file, uncomment this out when building a war file
// public class Application extends SpringBootServletInitializer
// uncomment this when building a jar file, comment this out when building a war file
public class Application
{

    // comment this out when building a jar file, uncomment this out when building a war file
    //@Override
    //protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    //{
    //    return application.sources(Application.class);
    //}

    public static void main(String[] args)
    {

        ApplicationContext context = SpringApplication.run(Application.class, args);
        DefaultFormattingConversionService conversionService = (DefaultFormattingConversionService) context.getBean("defaultConversionService");
        // put these back in when using jpamappings	of Toolbox database
        // conversionService.addConverter(new String2CurveEntryPK());
        // conversionService.addConverter(new CurveEntryPK2String());
    }
}
