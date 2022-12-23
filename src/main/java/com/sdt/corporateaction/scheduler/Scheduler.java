package com.sdt.corporateaction.scheduler;

import com.sdt.corporateaction.service.GoogleSheetsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Component
@Slf4j
@EnableAsync
public class Scheduler {

    @Autowired
    GoogleSheetsService googleSheetsService;

    @Scheduled(cron = "0 49 13 * * *")
    @Async
    public void initiateCorporateAction() {
        log.info("STARTING TO INITIATE FOR CORPORATE ACTION");
        try {
            googleSheetsService.getSpreadsheetValues();
        } catch (IOException | GeneralSecurityException e){
            log.error("CORPORATE ACTION INITIALISING FAILED", e);
        }
    }
}
