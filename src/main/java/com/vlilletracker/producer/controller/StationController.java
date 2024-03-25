package com.vlilletracker.producer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
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

        List<Station> stationList = stationService.getStationList();

        //TODO proper serialization of object instead of ugly toString
        stationList.forEach(station ->
            kafkaProducerConfig.sendMessage(station.toString())
        );
    }
}
