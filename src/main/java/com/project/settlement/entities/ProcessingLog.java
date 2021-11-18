
    package com.project.settlement.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.settlement.entities.enumeration.LogType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.Instant;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

    @Document(indexName = "processing_log")
    @Getter
    @Setter
    @ToString
    public class ProcessingLog  {
        private static final long serialVersionUID = -2012407242371026473L;

        @Id
        private String id;
        private String fileName;
        private Instant processedOn;
        private boolean failed;
        private String message;
        private LogType logType;

        @Field(type = FieldType.Date, format = DateFormat.date)
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate settlementDate;

        private String affiliateCode;

        public ProcessingLog(){}

        public ProcessingLog(String fileName, boolean failed, String message){
            this.fileName = fileName;
            this.processedOn = Instant.now();
            this.failed = failed;
            this.message = message;
        }

        public ProcessingLog(String fileName, LogType type, LocalDate settlementDate, String affiliateCode,
                             Boolean failed, String message) {
            this.fileName = fileName;
            this.logType = type;
            this.settlementDate = settlementDate;
            this.affiliateCode = affiliateCode;
            this.processedOn = Instant.now();
            this.failed = failed;
            this.message = message;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            ProcessingLog temp = (ProcessingLog) o;
            return Objects.equals(getId(), temp.getId());
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(getId());
        }

        public static List<String[]> getList(List<ProcessingLog> list) {
            List<String[]> data = new LinkedList<>();
            data.add(new String[] {"Filename", "Processed on", "Failed status", "Message"});
            for (ProcessingLog s : list) {
                //  data.add(new String[]{s.narration});
                data.add(new String[]{
                        s.fileName != null? s.fileName: "",
                        s.processedOn != null? s.processedOn.toString() : "",
                        String.valueOf(s.failed),
                        s.message != null? s.message : ""
                });
            }

            return data;
        }
    }

