package ro.msgromania.ckad.demoapplication.configuration;

import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class RootConfiguration {
    public static final String APP_ID = UUID.randomUUID().toString();

}
