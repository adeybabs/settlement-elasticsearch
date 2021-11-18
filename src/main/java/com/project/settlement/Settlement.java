package com.project.settlement;

import com.project.settlement.repository.LogRepository;
import com.project.settlement.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Settlement implements CommandLineRunner {
    @Autowired
    private LogService logService;

    @Autowired
    private LogRepository logRepository;

    public static void main(String[] args) {
        SpringApplication.run(Settlement.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("generating data");
        logRepository.deleteAll();
        for(int i =0; i<50; i++) {
            logService.generateLog();
        }
        System.out.println("generating data done");
    }
}
