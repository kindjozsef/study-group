package ro.msgromania.ckad.demoapplication.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class HealthService {

    private final AtomicBoolean ready = new AtomicBoolean(true);

    private final AtomicBoolean healthy = new AtomicBoolean(true);


    public boolean getReady() {
        return ready.get();
    }

    public boolean getHealthy() {
        return healthy.get();
    }


    public void toggleReadyOn() {
        ready.set(true);
    }

    public void toggleReadyOf() {
        ready.set(false);
    }

    public void toggleHealthyOn() {
        healthy.set(true);
    }

    public void toggleHealthyOff() {
        healthy.set(false);
    }

}
