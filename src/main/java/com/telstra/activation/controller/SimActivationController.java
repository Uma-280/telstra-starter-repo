package com.telstra.activation.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.Map;

@RestController
@RequestMapping("/sim")
public class SimActivationController {

    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/activate")
    public String activateSim(@RequestBody Map<String, String> payload) {
        String iccid = payload.get("iccid");
        String customerEmail = payload.get("customerEmail");

        // Forward request to actuator microservice
        String actuatorUrl = "http://localhost:8444/actuate";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(Map.of("iccid", iccid), headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(actuatorUrl, request, Map.class);

        Boolean success = (Boolean) response.getBody().get("success");

        if (success) {
            System.out.println("Activation successful for ICCID: " + iccid);
            return "SIM activation successful for " + customerEmail;
        } else {
            System.out.println("Activation failed for ICCID: " + iccid);
            return "SIM activation failed for " + customerEmail;
        }
    }
}
