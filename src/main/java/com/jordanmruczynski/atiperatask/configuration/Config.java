package com.jordanmruczynski.atiperatask.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

    @Value("${github.api.url}")
    private String githubApiUrl;

    public String getGithubApiUrl() {
        return githubApiUrl;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
