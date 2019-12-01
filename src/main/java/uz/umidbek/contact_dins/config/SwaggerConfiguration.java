package uz.umidbek.contact_dins.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfiguration implements WebMvcConfigurer {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("uz.umidbek.contact_dins"))
                .paths(getPaths())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "PhoneBook",
                "Some custom description of API.",
                "v 0.0.1",
                "Terms of service",
                new Contact("Umidbek Olimov", "https://github.com/Olimov-Umidbek", "umidbek1477@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }

    private Predicate<String> getPaths() {
        return Predicates.and(PathSelectors.regex("/.*"), Predicates.not(PathSelectors.regex("/error.*")));
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}