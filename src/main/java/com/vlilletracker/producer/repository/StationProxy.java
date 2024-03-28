package com.vlilletracker.producer.repository;

import com.vlilletracker.producer.CustomProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
public class StationProxy {

    @Autowired
    private CustomProperties customProperties;

    /**
     * Retrieve the maximum number of stations info allowed by ilevia's API for a single call
     * @param offset to ping the dataset with a specified position
     * @return list of stations as a response's body
     */
    public String getStationsInfo(int offset) {

        String urlTemplate = this.buildUrlTemplate(offset);

        log.info("calling url {}", urlTemplate);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(
                urlTemplate,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<String>() {}
        );

        return response.getBody();
    }

    private String buildUrlTemplate(int offset) {
        return UriComponentsBuilder.fromHttpUrl(customProperties.getApiUrl())
                .queryParam("order_by", "libelle")
                .queryParam("limit", customProperties.getMaxNumberOfResultPerPing())
                .queryParam("offset", String.valueOf(offset))
                .encode()
                .toUriString();
    }
}
