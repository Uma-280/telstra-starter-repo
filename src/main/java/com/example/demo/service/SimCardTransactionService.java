package com.example.demo.service;

import com.example.demo.entity.SimCardTransaction;
import com.example.demo.repository.SimCardTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.util.Optional;

@Service
public class SimCardTransactionService {

    private final SimCardTransactionRepository repo;
    private final RestTemplate restTemplate;

    @Autowired
    public SimCardTransactionService(SimCardTransactionRepository repo) {
        this.repo = repo;
        this.restTemplate = new RestTemplate();
    }

    /**
     * Calls the external actuator service and stores transaction record.
     *
     * @param iccid ICCID to actuate
     * @param customerEmail customer email
     * @return saved SimCardTransaction
     */
    public SimCardTransaction actuateAndSave(String iccid, String customerEmail) {
        // call actuator
        String actuatorUrl = "http://localhost:8444/actuate"; // per spec
        // construct body; actuator contract assumed to accept JSON { "iccid": "..." }
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(actuatorUrl,
                    java.util.Collections.singletonMap("iccid", iccid),
                    String.class);

            boolean active = response.getStatusCode().is2xxSuccessful();
            // Optionally parse response to decide active true/false
            SimCardTransaction tx = new SimCardTransaction(iccid, customerEmail, active);
            return repo.save(tx);
        } catch (Exception ex) {
            // If the actuator call fails, still save record with active=false
            SimCardTransaction tx = new SimCardTransaction(iccid, customerEmail, false);
            return repo.save(tx);
        }
    }

    public Optional<SimCardTransaction> findById(Long id) {
        return repo.findById(id);
    }
}

