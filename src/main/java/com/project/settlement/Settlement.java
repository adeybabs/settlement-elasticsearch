package com.project.settlement;

import com.project.settlement.repository.LogRepository;
import com.project.settlement.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.servlet.Filter;


@SpringBootApplication
public class Settlement implements CommandLineRunner {

    @Bean
    public Filter shallowEtagFilter(){
        return new ShallowEtagHeaderFilter();
    }

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
