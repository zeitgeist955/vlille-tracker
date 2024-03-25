package com.vlilletracker.producer;

import com.vlilletracker.producer.repository.IleviaProxy;
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
    private IleviaProxy ileviaProxy;

    @Override
    public void run(String... args) {
        log.debug("ping to {}", customProperties.getApiUrl());

        String responseBody = ileviaProxy.getAllStationsInfo();
        System.out.println(responseBody);
    }
}
