package ro.msgromania.ckad.demoapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msgromania.ckad.demoapplication.service.HealthService;

import static ro.msgromania.ckad.demoapplication.configuration.RootConfiguration.APP_ID;

@RestController
public class InfoController {

    @Autowired
    private HealthService healthService;

    @GetMapping("/info")
    public ResponseEntity<String> getInfo() {
        if(healthService.getReady() && healthService.getHealthy()) {
            return ResponseEntity.ok("Application %s is ready and healthy".formatted(APP_ID));
        }
        return ResponseEntity.internalServerError().build();
    }

}
