package com.vlilletracker.producer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vlilletracker.producer.CustomProperties;
import com.vlilletracker.producer.model.Station;
import com.vlilletracker.producer.repository.StationProxy;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class StationService {

    @Autowired
    private CustomProperties customProperties;

    @Autowired
    private StationProxy stationProxy;

    /**
     * Retrieve a list of every station available in ilevia's dataset, with their info
     * Make several ping to the API, as few as possible given max number of result per call and dataset size
     * As of today (28/03/2024), it takes 3 ping to retrieve 289 stations info, with a limit of 100 stations per ping
     * @return List of every station with details from the dataset
     * @throws JsonProcessingException during the parsing of response data with jackson library
     */
    public List<Station> getAllStationList() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        String firstResponseBody = stationProxy.getStationsInfo(0);
        log.debug("station ping body result : {}", firstResponseBody);

        StationsArrayAndTotal firstStationsArrayAndTotal = this.extractStationArrayFromResponseBody(firstResponseBody, objectMapper);
        log.info("Found {} stations", firstStationsArrayAndTotal.getTotalCount());

        List<Station> stationList = new ArrayList<>(Arrays.asList(firstStationsArrayAndTotal.getStationArray()));

        int maxNumberOfResultPerPing = Integer.parseInt(customProperties.getMaxNumberOfResultPerPing());

        //Retrieve the rest of the dataset
        for (int i = maxNumberOfResultPerPing; i < firstStationsArrayAndTotal.getTotalCount(); i+=maxNumberOfResultPerPing) {
            String responseBody = stationProxy.getStationsInfo(i);

            stationList.addAll(
                    Arrays.asList(this.extractStationArrayFromResponseBody(responseBody, objectMapper)
                            .getStationArray()));
        }

        return stationList;
    }

    private StationsArrayAndTotal extractStationArrayFromResponseBody(String responseBody, ObjectMapper objectMapper) throws JsonProcessingException {
        JsonNode rootNode = objectMapper.readTree(responseBody);

        JsonNode totalNode = rootNode.at("/total_count");

        JsonNode resultNode = rootNode.at("/results");
        Station[] stationsArray = objectMapper.treeToValue(resultNode, Station[].class);

        return new StationsArrayAndTotal(stationsArray, totalNode.asInt());
    }


    @Data
    private static class StationsArrayAndTotal {
        private Station[] stationArray;

        private int totalCount;

        public StationsArrayAndTotal(Station[] stationArray, int totalCount) {
            this.stationArray = stationArray;
            this.totalCount = totalCount;
        }
    }
}
