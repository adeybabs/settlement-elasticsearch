package com.project.settlement.api;

import com.project.settlement.entities.ProcessingLog;
import com.project.settlement.repository.LogRepository;
import com.project.settlement.service.LogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/log/v1")
@Tag(name = "Car API", description = "Documentation for Car API")
public class LogApi {

        @Autowired
        private LogRepository logRepository;

        @Autowired
        private LogService logService;

        @GetMapping(value = "/random", produces = MediaType.APPLICATION_JSON_VALUE)
        public ProcessingLog random() {
            return  logService.generateLog();
        }


        //Endpoint to carElasticRepository.count
        @GetMapping(value = "/count")
        public String countCar() {
            return "There are : " + logRepository.count() + " Logs.";
        }

        //To save new car into elasticsearch
        @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
        public String saveCar(@RequestBody ProcessingLog processingLog) {
            final String id = logRepository.save(processingLog).getId();
            return "Saved with ID : " + id;
        }

        @GetMapping(value = "/{id}/")
        public ProcessingLog getLog(@PathVariable("id") String LogId) {
            return logRepository.findById(LogId).orElse(null);
        }

        //Using the ResponseEntity's eTag() Method
        //We can use the version itself as the ETag to indicate if the entity has been modified
        @GetMapping(value = "/{id}/custom-etag")
        public ResponseEntity<ProcessingLog>
        findByIdWithCustomEtag(@PathVariable("id") final Long id) {

            ProcessingLog processingLog = new ProcessingLog();

            return ResponseEntity.ok()
                    .eTag(logRepository.toString())
                    .body(processingLog);
        }

    }
