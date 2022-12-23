package com.franca.moneyalgaapi.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Bean
    public Docket api(){

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.franca.moneyalgaapi.resource"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo(
                "API - FINANÇA",
                "API para o geranciamento de finanças pessoais",
                "1.0.0",
                "Termos e serviços",
                new Contact("Tiago França","https://github.com/tiagofrancarita",
                        "tiagofranca.rita@gmail.com"),
                "Apache Licence",
                "https://www.apache.org/licesen.html",new ArrayList<VendorExtension>());

        return apiInfo;
    }
}