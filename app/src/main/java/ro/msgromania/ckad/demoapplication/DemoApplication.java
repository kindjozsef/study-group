package ro.msgromania.ckad.demoapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws InterruptedException {
		Thread.sleep(15000);
		SpringApplication.run(DemoApplication.class, args);
	}

}
