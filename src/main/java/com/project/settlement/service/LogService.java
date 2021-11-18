package com.project.settlement.service;

import com.project.settlement.entities.ProcessingLog;

import java.util.Arrays;
import java.util.List;

public interface LogService {
    List<String> Filename = Arrays
            .asList("introspec", "data", "updates", "new items", "open source");

    List<String> AFFILIATE_CODE = Arrays
            .asList("GH", "NG", "SA", "SN", "SL");

    List<String> MESSAGE = Arrays
            .asList("introspec log is updated", "Another log has been updated", "successful log");



    ProcessingLog generateLog();

}
