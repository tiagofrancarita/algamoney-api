package com.franca.moneyalgaapi;

import io.swagger.annotations.Contact;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API - FINANCEIRA", version = "1.0.0", description = "API para controle financeiro pessoal"))
public class MoneyalgaapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoneyalgaapiApplication.class, args);
    }

}
