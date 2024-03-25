package com.vlilletracker.producer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vlilletracker.producer.model.Location;
import com.vlilletracker.producer.model.Station;
import com.vlilletracker.producer.repository.StationProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class StationService {

    private String bodyCache = """
        {"total_count": 289, "results": [{"libelle": 136, "nom": "RUE CHAMBORD", "adresse": "RUE CHAMBORD", "commune": "VILLENEUVE D'ASCQ", "etat": "R\u00c9FORM\u00c9", "type": "SANS TPE", "geo": {"lon": 3.133873, "lat": 50.62972}, "nbplacesdispo": 0, "nbvelosdispo": 0, "etatconnexion": "D\u00c9CONNECT\u00c9", "localisation": {"lon": 3.133873, "lat": 50.62972}, "datemiseajour": "2022-11-29T09:47:16+00:00"}, {"libelle": 97, "nom": "LA MADELEINE GARE", "adresse": "250 Rue du G\u00e9n\u00e9ral de Gaulle", "commune": "LA MADELEINE", "etat": "EN SERVICE", "type": "AVEC TPE", "geo": {"lon": 3.074057, "lat": 50.65937}, "nbplacesdispo": 15, "nbvelosdispo": 2, "etatconnexion": "CONNECT\u00c9", "localisation": {"lon": 3.074057, "lat": 50.65937}, "datemiseajour": "2024-03-25T13:56:12+00:00"}, {"libelle": 85, "nom": "CHANZY", "adresse": "102 rue Chanzy", "commune": "LILLE HELLEMMES", "etat": "EN SERVICE", "type": "AVEC TPE", "geo": {"lon": 3.1008, "lat": 50.62504}, "nbplacesdispo": 5, "nbvelosdispo": 7, "etatconnexion": "CONNECT\u00c9", "localisation": {"lon": 3.1008, "lat": 50.62504}, "datemiseajour": "2024-03-25T13:56:12+00:00"}, {"libelle": 13, "nom": "QUAI DU WAULT", "adresse": "17 Square Dutilleul", "commune": "LILLE", "etat": "EN SERVICE", "type": "AVEC TPE", "geo": {"lon": 3.054773, "lat": 50.63671}, "nbplacesdispo": 13, "nbvelosdispo": 5, "etatconnexion": "CONNECT\u00c9", "localisation": {"lon": 3.054773, "lat": 50.63671}, "datemiseajour": "2024-03-25T13:56:12+00:00"}, {"libelle": 11, "nom": "NOUVEAU SIECLE", "adresse": "16 rue de Pas", "commune": "LILLE", "etat": "EN SERVICE", "type": "AVEC TPE", "geo": {"lon": 3.060977, "lat": 50.63734}, "nbplacesdispo": 15, "nbvelosdispo": 17, "etatconnexion": "CONNECT\u00c9", "localisation": {"lon": 3.060977, "lat": 50.63734}, "datemiseajour": "2024-03-25T13:56:12+00:00"}, {"libelle": 30, "nom": "RUE DE GAND", "adresse": "8 place de Gand", "commune": "LILLE", "etat": "EN SERVICE", "type": "AVEC TPE", "geo": {"lon": 3.067375, "lat": 50.64297}, "nbplacesdispo": 7, "nbvelosdispo": 12, "etatconnexion": "CONNECT\u00c9", "localisation": {"lon": 3.067375, "lat": 50.64297}, "datemiseajour": "2024-03-25T13:56:12+00:00"}, {"libelle": 35, "nom": "LECLERC", "adresse": "Place du Mar\u00e9chal Leclerc", "commune": "LILLE", "etat": "EN SERVICE", "type": "AVEC TPE", "geo": {"lon": 3.043307, "lat": 50.62899}, "nbplacesdispo": 11, "nbvelosdispo": 17, "etatconnexion": "CONNECT\u00c9", "localisation": {"lon": 3.043307, "lat": 50.62899}, "datemiseajour": "2024-03-25T13:56:12+00:00"}, {"libelle": 41, "nom": "BUISSON", "adresse": "685 avenue de la R\u00e9publique", "commune": "LILLE", "etat": "EN SERVICE", "type": "AVEC TPE", "geo": {"lon": 3.088004, "lat": 50.656487}, "nbplacesdispo": 8, "nbvelosdispo": 8, "etatconnexion": "CONNECT\u00c9", "localisation": {"lon": 3.088004, "lat": 50.656487}, "datemiseajour": "2024-03-25T13:56:12+00:00"}, {"libelle": 44, "nom": "GAMBETTA UTRECHT", "adresse": "203 Rue L\u00e9on Gambetta", "commune": "LILLE", "etat": "EN SERVICE", "type": "AVEC TPE", "geo": {"lon": 3.053711, "lat": 50.629063}, "nbplacesdispo": 12, "nbvelosdispo": 6, "etatconnexion": "CONNECT\u00c9", "localisation": {"lon": 3.053711, "lat": 50.629063}, "datemiseajour": "2024-03-25T13:56:12+00:00"}, {"libelle": 55, "nom": "PORTE D'ARRAS", "adresse": "3 place Jacques Febvrier", "commune": "LILLE", "etat": "EN SERVICE", "type": "AVEC TPE", "geo": {"lon": 3.061758, "lat": 50.617847}, "nbplacesdispo": 15, "nbvelosdispo": 9, "etatconnexion": "CONNECT\u00c9", "localisation": {"lon": 3.061758, "lat": 50.617847}, "datemiseajour": "2024-03-25T13:56:12+00:00"}, {"libelle": 81, "nom": "GUINGUETTE", "adresse": "208 rue Corneille", "commune": "LILLE HELLEMMES", "etat": "EN SERVICE", "type": "AVEC TPE", "geo": {"lon": 3.115088, "lat": 50.63529}, "nbplacesdispo": 7, "nbvelosdispo": 3, "etatconnexion": "CONNECT\u00c9", "localisation": {"lon": 3.115088, "lat": 50.63529}, "datemiseajour": "2024-03-25T13:56:12+00:00"}, {"libelle": 83, "nom": "EURATECHNOLOGIES", "adresse": "175 avenue de Bretagne", "commune": "LILLE", "etat": "EN SERVICE", "type": "AVEC TPE", "geo": {"lon": 3.02059, "lat": 50.63378}, "nbplacesdispo": 3, "nbvelosdispo": 20, "etatconnexion": "CONNECT\u00c9", "localisation": {"lon": 3.02059, "lat": 50.63378}, "datemiseajour": "2024-03-25T13:56:12+00:00"}, {"libelle": 90, "nom": "LOUVIERE", "adresse": "94 rue de la Louvi\u00e8re", "commune": "LILLE", "etat": "EN SERVICE", "type": "AVEC TPE", "geo": {"lon": 3.0858226, "lat": 50.64569}, "nbplacesdispo": 19, "nbvelosdispo": 1, "etatconnexion": "CONNECT\u00c9", "localisation": {"lon": 3.0858226, "lat": 50.64569}, "datemiseajour": "2024-03-25T13:56:12+00:00"}, {"libelle": 96, "nom": "ROMARIN", "adresse": "92 Avenue de la R\u00e9publique", "commune": "LA MADELEINE", "etat": "EN SERVICE", "type": "AVEC TPE", "geo": {"lon": 3.075116, "lat": 50.64543}, "nbplacesdispo": 11, "nbvelosdispo": 16, "etatconnexion": "CONNECT\u00c9", "localisation": {"lon": 3.075116, "lat": 50.64543}, "datemiseajour": "2024-03-25T13:56:12+00:00"}, {"libelle": 100, "nom": "SAINT MAUR", "adresse": "260 Avenue de la R\u00e9publique", "commune": "LA MADELEINE", "etat": "EN SERVICE", "type": "AVEC TPE", "geo": {"lon": 3.081625, "lat": 50.651638}, "nbplacesdispo": 15, "nbvelosdispo": 9, "etatconnexion": "CONNECT\u00c9", "localisation": {"lon": 3.081625, "lat": 50.651638}, "datemiseajour": "2024-03-25T13:56:12+00:00"}, {"libelle": 102, "nom": "MAIRIE DE MONS", "adresse": "27 Avenue Robert Schuman", "commune": "MONS EN BAR\u0152UL", "etat": "EN SERVICE", "type": "AVEC TPE", "geo": {"lon": 3.109699, "lat": 50.64218}, "nbplacesdispo": 5, "nbvelosdispo": 11, "etatconnexion": "CONNECT\u00c9", "localisation": {"lon": 3.109699, "lat": 50.64218}, "datemiseajour": "2024-03-25T13:56:12+00:00"}, {"libelle": 110, "nom": "RUE BONTE-POLLET", "adresse": "11 rue Bonte Pollet", "commune": "LILLE", "etat": "EN SERVICE", "type": "AVEC TPE", "geo": {"lon": 3.034234, "lat": 50.62487}, "nbplacesdispo": 12, "nbvelosdispo": 4, "etatconnexion": "CONNECT\u00c9", "localisation": {"lon": 3.034234, "lat": 50.62487}, "datemiseajour": "2024-03-25T13:56:12+00:00"}, {"libelle": 144, "nom": "ANNAPPES REPUBLIQUE", "adresse": "2 rue de la Station", "commune": "VILLENEUVE D'ASCQ", "etat": "EN SERVICE", "type": "AVEC TPE", "geo": {"lon": 3.14862, "lat": 50.6258}, "nbplacesdispo": 15, "nbvelosdispo": 9, "etatconnexion": "CONNECT\u00c9", "localisation": {"lon": 3.14862, "lat": 50.6258}, "datemiseajour": "2024-03-25T13:56:12+00:00"}, {"libelle": 147, "nom": "FAUBOURG D'ARRAS", "adresse": "2 rue Marcel H\u00e9naux", "commune": "LILLE", "etat": "EN SERVICE", "type": "AVEC TPE", "geo": {"lon": 3.062195, "lat": 50.614052}, "nbplacesdispo": 11, "nbvelosdispo": 5, "etatconnexion": "CONNECT\u00c9", "localisation": {"lon": 3.062195, "lat": 50.614052}, "datemiseajour": "2024-03-25T13:56:12+00:00"}, {"libelle": 153, "nom": "POMPIDOU", "adresse": "102 rue du Pr\u00e9sident Georges Pompidou", "commune": "LA MADELEINE", "etat": "EN SERVICE", "type": "AVEC TPE", "geo": {"lon": 3.064989, "lat": 50.65547}, "nbplacesdispo": 21, "nbvelosdispo": 3, "etatconnexion": "CONNECT\u00c9", "localisation": {"lon": 3.064989, "lat": 50.65547}, "datemiseajour": "2024-03-25T13:56:12+00:00"}]}
        """;

    @Autowired
    private StationProxy stationProxy;

    public List<Station> getStationList() throws JsonProcessingException {
        String responseBody = stationProxy.getAllStationsInfo();

        ObjectMapper objectMapper = new ObjectMapper();
        log.debug("station ping body result : {}", responseBody);

        JsonNode rootNode = objectMapper.readTree(responseBody);

        JsonNode totalNode = rootNode.at("/total_count");
        log.debug("Found {} stations", totalNode.toString());

        JsonNode resultNode = rootNode.at("/results");
        Station[] stationsArray = objectMapper.treeToValue(resultNode, Station[].class);

        return Arrays.asList(stationsArray);
    }

    private String locationTestJson = "[{\"lon\": 1.074057, \"lat\": 51.65937}, {\"lon\": 2.074057, \"lat\": 52.65937}]";

    private Location[] testLocation() throws JsonProcessingException {
        Location location1 = new Location(1.074057, 51.65937);
        Location location2 = new Location(2.074057, 52.65937);
        Location location3 = new Location(3.074057, 53.65937);

        ObjectMapper objectMapper = new ObjectMapper();

        Location[] locations = objectMapper.readValue(locationTestJson, Location[].class);
        Arrays.stream(locations).forEach(System.out::println);

        return locations;
    }
}
