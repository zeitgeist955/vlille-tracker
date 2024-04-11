package com.vlilletracker.producer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.vlilletracker.producer.kafka.KafkaProducerConfig;
import com.vlilletracker.producer.model.Station;
import com.vlilletracker.producer.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class StationController {

    @Autowired
    private StationService stationService;

    @Autowired
    KafkaProducerConfig kafkaProducerConfig;

    public void produceStationList() throws JsonProcessingException {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

        List<Station> stationList = stationService.getAllStationList();

        for (Station station : stationList) {
            //FIXME fix null key in kafka - is this an issue, can it maybe auto increment ?
            kafkaProducerConfig.sendMessage(objectWriter.writeValueAsString(station));
        }
    }
}
