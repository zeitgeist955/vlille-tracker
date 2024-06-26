package com.vlilletracker.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vlilletracker.producer.controller.StationController;
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
    private StationController stationController;

    @Override
    public void run(String... args) {
        log.debug("ping to {}", customProperties.getApiUrl());

        try {
            int i = 1;
            while(true) {
                log.info("Fetching all stations info for the {} time", i);
                stationController.produceStationList();
                i++;
                Thread.sleep(60000); // Dataset is updated every minute
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
