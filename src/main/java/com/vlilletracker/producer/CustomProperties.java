package com.vlilletracker.producer;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "com.vlilletracker.producer") //map with the application.properties file
public class CustomProperties {

    private String apiUrl;

    private String applicationDesignation;

    private String maxNumberOfResultPerPing;
}
