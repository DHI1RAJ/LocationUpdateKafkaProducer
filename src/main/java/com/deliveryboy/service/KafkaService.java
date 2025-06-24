// KafkaService.java
package com.deliveryboy.service;

import com.deliveryboy.config.AppConstants;
import com.deliveryboy.model.Location;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final Logger logger = LoggerFactory.getLogger(KafkaService.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void updateLocation(Location location) {
        try {
            String message = objectMapper.writeValueAsString(location);
            kafkaTemplate.send(AppConstants.LOCATION_TOPIC_NAME, message);
            logger.info("Location sent to Kafka: " + message);
        } catch (JsonProcessingException e) {
            logger.error("Error serializing location", e);
        }
    }
}