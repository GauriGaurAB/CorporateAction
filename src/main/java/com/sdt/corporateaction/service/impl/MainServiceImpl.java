package com.sdt.corporateaction.service.impl;

import com.sdt.corporateaction.entity.ClientList;
import com.sdt.corporateaction.entity.CorporateAction;
import com.sdt.corporateaction.repository.DataRepository;
import com.sdt.corporateaction.service.CorporateActionService;
import com.sdt.corporateaction.service.MainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class MainServiceImpl implements MainService {

    @Autowired
    CorporateActionService corporateActionService;

    @Autowired
    JdbcTemplate jdbcTemplate;
    private DataRepository dataRepository;


    @Override
    public void initiateCorporateAction()
    {
        List<CorporateAction> corporateActions = corporateActionService.getCorporateActions();
        if (corporateActions.size() > 0)
        {
            dataRepository = new DataRepository(jdbcTemplate);
            for (CorporateAction ca : corporateActions)
            {
                List<ClientList> clientLists = dataRepository.getClientsEligibleForCA(date(ca.getExDate()), ca.getSecurityCode());

                clientLists.forEach(cl -> {
                    if (cl.getClientId().equalsIgnoreCase("315481"))
                        System.out.println(cl.getClientId());
                });
            }
        } else log.info("No CA found for {}", LocalDate.now());
    }

    private String date(String expDate)
    {
        DateFormat inputFormat = new SimpleDateFormat("dd-MMM-yy");//11-Nov-22
        DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = inputFormat.parse(expDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputFormat.format(date);
    }
}
