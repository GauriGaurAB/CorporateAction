package com.sdt.corporateaction.scheduler;

import com.sdt.corporateaction.service.MainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@EnableAsync
public class Scheduler {

    @Autowired
    MainService mainService;

    @Scheduled(cron = "0 49 13 * * *")
    @Async
    public void initiateCorporateAction() {
        log.info("STARTING TO INITIATE FOR CORPORATE ACTION");
        try {
            mainService.initiateCorporateAction();
        } catch (Exception e){
            log.error("CORPORATE ACTION INITIALISING FAILED", e);
        }
    }
}
