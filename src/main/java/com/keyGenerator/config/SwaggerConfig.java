package com.keyGenerator.config;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.fasterxml.classmate.TypeResolver;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private String version = "v1";
    private String pathUri = "/**";
    private String title = "KEY SYSTEM API";
    private String description = "KEY SYSTEM API Docs";
    private String basePackage = "com.keyGenerator";
        
    @Bean
    public Docket docket() {
    	
        TypeResolver typeResolver = new TypeResolver();
        List<ResponseMessage> commonResponse = setCommonResponse();
        Set<String> contentType = Set.of(MediaType.APPLICATION_JSON_VALUE);

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(title)
                .select()
                .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.ant(pathUri)).build()
                .directModelSubstitute(OffsetDateTime.class, String.class)
                .consumes(contentType)
                .produces(contentType)
                .globalResponseMessage(RequestMethod.GET, commonResponse)
                .globalResponseMessage(RequestMethod.POST, commonResponse)
                .globalResponseMessage(RequestMethod.PUT, commonResponse)
                .globalResponseMessage(RequestMethod.PATCH, commonResponse)
                .globalResponseMessage(RequestMethod.DELETE, commonResponse)
                .additionalModels(typeResolver.resolve(ResponseEntity.class))
                .apiInfo(setApiInfo());
    }
    

    private ApiInfo setApiInfo() {
        return new ApiInfo(title, description, version, "", new Contact("", "", ""), "", "",
                Collections.emptyList());
    }

    private List<ResponseMessage> setCommonResponse() {
        List<ResponseMessage> list = new ArrayList<>();
        list.add(new ResponseMessageBuilder().code(404).message("Not Found").responseModel(new ModelRef("ResponseEntity")).build());
        list.add(new ResponseMessageBuilder().code(500).message("Internal Error").responseModel(new ModelRef("ResponseEntity")).build());
        return list;
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
