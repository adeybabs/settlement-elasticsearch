package com.project.settlement.service;

import com.project.settlement.entities.ProcessingLog;
import com.project.settlement.entities.enumeration.LogType;
import com.project.settlement.repository.LogRepository;
import com.project.settlement.util.RandomDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class RandomLogService implements LogService {

    @Autowired
    private LogRepository logRepository;

    @Override
    public ProcessingLog generateLog() {
        String fileName = Filename.get(ThreadLocalRandom.current().nextInt(0, Filename.size()));
        String affiliateCode = AFFILIATE_CODE.get(ThreadLocalRandom.current().nextInt(0, AFFILIATE_CODE.size()));
        String message = MESSAGE.get(ThreadLocalRandom.current().nextInt(0, MESSAGE.size()));

       // LogType type = Arrays.stream(LogType.values()).findAny().get();
       // LogType type = Arrays.values[(int)(Math.random()*LogType.values().length)];



        boolean failed = ThreadLocalRandom.current().nextBoolean();
        LocalDate settlementDate = RandomDateUtil.generateRandomLocalDate();



        ProcessingLog result = new ProcessingLog(fileName,LogType.type(),settlementDate,affiliateCode,failed,message);

        result.setFailed(failed);
        result.setSettlementDate(settlementDate);
        result.setLogType(LogType.type());

        return logRepository.save(result);
    }
}