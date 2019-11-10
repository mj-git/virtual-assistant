package com.mediaocean.hackathon.virtualassistant.config;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfiguration {

    @Bean
    public JsonNodeFactory getJsonNodeFactory() {
        return JsonNodeFactory.instance;
    }

    @Bean
    public RestTemplate getRestController() {
        return new RestTemplate();
    }
}
