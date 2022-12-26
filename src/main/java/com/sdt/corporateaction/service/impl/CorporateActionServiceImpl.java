package com.sdt.corporateaction.service.impl;

import com.sdt.corporateaction.constants.Headers;
import com.sdt.corporateaction.constants.Indices;
import com.sdt.corporateaction.entity.CorporateAction;
import com.sdt.corporateaction.service.CorporateActionService;
import com.sdt.corporateaction.service.GoogleSheetsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
@Slf4j
public class CorporateActionServiceImpl implements CorporateActionService {
    
    @Autowired
    GoogleSheetsService googleSheetsService;
    
    @Override
    public List<CorporateAction> getCorporateActions()
    {
        List<List<Object>> spreadSheetValues = new ArrayList<>();
        List<CorporateAction> corporateActions = new ArrayList<>();

        try {
            spreadSheetValues = googleSheetsService.getSpreadsheetValues();
        } catch (IOException | GeneralSecurityException e) {
            log.error("READING GOOGLE SHEET FAILED", e);
        }
        if (spreadSheetValues.size() > 0)
        {
            for ( List<Object> row : spreadSheetValues ) {
                if (spreadSheetValues.remove(0).get(Indices.PROCESSESD_DATE).equals(Headers.PROCESSESD_DATE)
                        && row.get(Indices.PROCESSESD_DATE).toString().equalsIgnoreCase(date()))
                {
                    CorporateAction corporateAction = new CorporateAction();
                    corporateAction.setRatio(row.get(Indices.Ratio).toString());
                    corporateAction.setExDate(row.get(Indices.Ex_Date).toString());
                    corporateAction.setCRType(row.get(Indices.CR_type).toString());
                    corporateAction.setPurpose(row.get(Indices.Purpose).toString());
                    corporateAction.setRecordDate(row.get(Indices.Record_Date).toString());
                    corporateAction.setCompanyName(row.get(Indices.Company_Name).toString());
                    corporateAction.setSecurityName(row.get(Indices.Security_Name).toString());
                    corporateAction.setSecurityCode(row.get(Indices.Security_Code).toString());
                    corporateAction.setProcessedDate(row.get(Indices.PROCESSESD_DATE).toString());

                    corporateAction.setClientId(row.get(Indices.sample_clients_to_chk).toString());
                    corporateActions.add(corporateAction);
                }
            }
        }
        return corporateActions;
    }

    private String date()
    {
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = inputFormat.parse(String.valueOf(LocalDate.now()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputFormat.format(date);
    }
}
