package com.vlilletracker.producer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vlilletracker.producer.model.Station;
import com.vlilletracker.producer.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class StationController {

    @Autowired
    private StationService stationService;

    public void produceStationList() throws JsonProcessingException {

        //TODO : call this every x time
        List<Station> stationList = stationService.getStationList();

        stationList.forEach(System.out::println);

        //TODO : send station list to kafka producer
    }
}
