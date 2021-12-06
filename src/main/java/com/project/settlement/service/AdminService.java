package com.project.settlement.service;

import com.project.settlement.entities.ProcessingLog;
import com.project.settlement.entities.enumeration.LogType;
import com.project.settlement.repository.LogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.jms.Queue;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import java.time.LocalDate;

@Slf4j
//@Aspect
@Component
public class AdminService extends AbstractRequestLoggingFilter {

    private LogRepository logRepository;
    private MessageSource messageSource;

    @Autowired
    public void setLogRepository(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    public void addProcessingLog(String fileName, String message) {
        ProcessingLog pLog = new ProcessingLog(fileName, true, message);
        addProcessingLog(pLog);
    }

    public void addProcessingLog(String fileName, LogType type, LocalDate settlementDate, String affiliateCode,
                                 Boolean failed, String message) {
        ProcessingLog pLog = new ProcessingLog(fileName, type, settlementDate, affiliateCode, true, message);
        addProcessingLog(pLog);
    }

    public void addProcessingLog(ProcessingLog processingLog) {
        logRepository.save(processingLog);
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {

    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {

    }
}
