package ro.msgromania.ckad.demoapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msgromania.ckad.demoapplication.service.HealthService;

@RestController
public class HealthController {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private HealthService healthService;

    @GetMapping("/poisonpill")
    public ResponseEntity<Void> shutdown() {
        // Leállítási folyamat: meghívjuk a SpringApplication.exit()-t, majd System.exit()-t.
        int exitCode = SpringApplication.exit(context, () -> 0);
        System.exit(exitCode);
        // Ez a sor valójában sosem ér el, de szükséges a metódus aláírása miatt.
        return ResponseEntity.ok().build();
    }

    @GetMapping("/ready")
    public ResponseEntity<String> isReady() {
        if(healthService.getReady()) {
            return ResponseEntity.ok("Application is ready");
        }
        return ResponseEntity.internalServerError().build();
    }

    @GetMapping("/live")
    public ResponseEntity<String> isLive() {
        if(healthService.getHealthy()) {
            return ResponseEntity.ok("Application is live");
        }
        return ResponseEntity.internalServerError().build();
    }

    @GetMapping("/freeze")
    public String freeze() {
        healthService.toggleHealthyOff();
        return "Freeze application";
    }

}
