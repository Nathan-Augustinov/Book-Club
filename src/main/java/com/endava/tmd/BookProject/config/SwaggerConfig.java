package com.endava.tmd.BookProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String USER_TAG = "1. User";
    public static final String BOOK_TAG = "2. Book";
    public static final String USER_BOOK_TAG = "3. Users Books";
    public static final String FOR_RENT_TAG = "4. For Rent Books";
    public static final String RENT_TAG = "5. Rented Books";
    public static final String WAITING_LIST_TAG = "6. Waiting list";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .tags(new Tag(USER_TAG,"User API"),
                        new Tag(BOOK_TAG,"Book API"),
                        new Tag(USER_BOOK_TAG, "Users Books API"),
                        new Tag(FOR_RENT_TAG, "For rent books API"),
                        new Tag(RENT_TAG, "Rented books API"),
                        new Tag(WAITING_LIST_TAG, "Waiting list API"));
    }
}
