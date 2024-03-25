package com.vlilletracker.producer.repository;

import com.vlilletracker.producer.CustomProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class StationProxy {

    @Autowired
    private CustomProperties customProperties;

    //TODO find more than the first 20 stations - API parameter is limited to 100 but there is currently 289 stations available
    public String getAllStationsInfo() {
        String apiUrl = customProperties.getApiUrl();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<String>() {}
        );

        return response.getBody();
    }
}
