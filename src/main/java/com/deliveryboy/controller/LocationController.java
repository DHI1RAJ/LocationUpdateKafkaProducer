// LocationController.java
package com.deliveryboy.controller;

import com.deliveryboy.model.Location;
import com.deliveryboy.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private KafkaService kafkaService;

    @GetMapping("/track")
    public String getTrackingPage() {
        return "location";
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity<?> updateLocation(@RequestBody Location location) {
        kafkaService.updateLocation(location);
        return ResponseEntity.ok(Map.of("message", "Location updated successfully---"));
    }
}