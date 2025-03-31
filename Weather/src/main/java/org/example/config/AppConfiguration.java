package org.example.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration
{
    @Bean
    @LoadBalanced
    public RestTemplate RestTemplate()
    {
        return new RestTemplate();
    }

    @Bean
    public RestTemplate RestTemplateExternal()
    {
        return new RestTemplate();
    }
}