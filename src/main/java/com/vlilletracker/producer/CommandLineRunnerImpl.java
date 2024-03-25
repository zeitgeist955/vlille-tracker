package com.vlilletracker.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vlilletracker.producer.service.StationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    @Autowired
    private CustomProperties customProperties;

    @Autowired
    private StationService stationService;

    @Override
    public void run(String... args) {
        log.debug("ping to {}", customProperties.getApiUrl());

        try {
            stationService.getStationList().forEach(System.out::println);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
