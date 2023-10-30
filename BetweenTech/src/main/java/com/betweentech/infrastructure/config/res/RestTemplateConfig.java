package com.betweentech.infrastructure.config.res;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Value("${app.conf.rest.timeout}")
    private int timeOut;


    /**
     * @return RestTemplate
     */
    @Bean
    public RestTemplate restTemplate(){

        return new RestTemplate(getClientHttpRequestFactory());
    }


    /**
     * @return SimpleClientHttpRequestFactory
     */
    private SimpleClientHttpRequestFactory getClientHttpRequestFactory()
    {
        SimpleClientHttpRequestFactory clientHttpRequestFactory
                = new SimpleClientHttpRequestFactory();
        //Connect timeout
        clientHttpRequestFactory.setConnectTimeout(timeOut);

        //Read timeout
        clientHttpRequestFactory.setReadTimeout(timeOut);
        return clientHttpRequestFactory;
    }
}