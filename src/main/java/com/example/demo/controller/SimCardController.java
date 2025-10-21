package com.example.demo.controller;

import com.example.demo.entity.SimCardTransaction;
import com.example.demo.service.SimCardTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/sim")
public class SimCardController {

    private final SimCardTransactionService service;

    @Autowired
    public SimCardController(SimCardTransactionService service) {
        this.service = service;
    }

    // POST endpoint to receive email + iccid and process activation
    @PostMapping("/activate")
    public ResponseEntity<Map<String, Object>> activateSim(@RequestBody Map<String, String> payload) {
        String iccid = payload.get("iccid");
        String customerEmail = payload.get("customerEmail");

        if (iccid == null || customerEmail == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "iccid and customerEmail required"));
        }

        SimCardTransaction saved = service.actuateAndSave(iccid, customerEmail);

        return ResponseEntity.ok(Map.of(
                "id", saved.getId(),
                "iccid", saved.getIccid(),
                "customerEmail", saved.getCustomerEmail(),
                "active", saved.isActive()
        ));
    }

    // GET endpoint to query by simCardId (long) and return { iccid, customerEmail, active }
    @GetMapping("/transaction")
    public ResponseEntity<?> getTransaction(@RequestParam("simCardId") Long simCardId) {
        return service.findById(simCardId)
                .map(tx -> ResponseEntity.ok(Map.of(
                        "iccid", tx.getIccid(),
                        "customerEmail", tx.getCustomerEmail(),
                        "active", tx.isActive()
                )))
                .orElseGet(() -> ResponseEntity.status(404).body(Map.of("error", "Not found")));
    }
}
