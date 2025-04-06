package org.example.task3calender.develop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
public class Task3CalenderApplication {

    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(Task3CalenderApplication.class);
        app.setDefaultProperties(
                Map.of("spring.config.location", "classpath:/application-develop.properties")
        );
        app.run(args);
//        SpringApplication.run(Task3CalenderApplication.class, args);
    }

}
