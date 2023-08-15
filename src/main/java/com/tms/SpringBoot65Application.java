package com.tms;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "Group 65 project",
                description = "This is pet project",
                contact = @Contact(
                        name = "Unuchko Mikhail",
                        email = "Wnuk1988@mail.ru",
                        url = "@wnuk"
                )
        )
)
@SpringBootApplication
public class SpringBoot65Application {
   // static final Logger log = LogManager.getLogger(SpringBoot65Application.class);
    static final Logger log = LoggerFactory.getLogger(SpringBoot65Application.class);
    public static void main(String[] args) {
//        log.error("ERROR LOG");
//        log.info("GOOD");
//        log.debug("DEBUG LOG");


        SpringApplication.run(SpringBoot65Application.class, args);
        log.info("Good");
    }

}
