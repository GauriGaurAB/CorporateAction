package com.sdt.corporateaction.service.impl;

import com.sdt.corporateaction.entity.ClientList;
import com.sdt.corporateaction.entity.CorporateAction;
import com.sdt.corporateaction.entity.GlobalDetailPnl;
import com.sdt.corporateaction.entity.GlobalDetailPnlTmp;
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
    private final String YYYYMMDD = "yyyy-MM-dd";//2022-11-11
    private final String DDMMYYYY = "dd/MM/yyyy";//11/11/2022

    @Override
    public void initiateCorporateAction()
    {
        List<CorporateAction> corporateActions = corporateActionService.getCorporateActions();
        if (corporateActions.size() > 0)
        {
            dataRepository = new DataRepository(jdbcTemplate);
            for (CorporateAction ca : corporateActions)
            {
                System.out.println("in corporateActions");
//                if (!ca.getCRType().equalsIgnoreCase("Bonus"))
//                    continue;

                List<ClientList> clientLists = dataRepository.getClientsEligibleForCA(date(ca.getExDate(), YYYYMMDD), ca.getSecurityCode());
                log.info("Total Clients for corporate action - {}", clientLists.size());
                clientLists.forEach(cl -> {
//                    if (cl.getClientId().equalsIgnoreCase(ca.getClientId()))
//                    {
                        GlobalDetailPnl pnlData = corporateActionService.getDetailsForCorporateAction(ca.getSecurityCode(), cl.getClientId(), date(ca.getExDate(), YYYYMMDD));
                        if (pnlData != null)
                        {
                            GlobalDetailPnlTmp pnlDataNew = new GlobalDetailPnlTmp();
                            double qty = cl.getBQty()- cl.getSQty();
                            String narration = "BY CORPORATE ACTION ";
                            String[] ratio = ca.getRatio().split(":");
                            qty = Math.floor(qty/Double.parseDouble(ratio[0])) * Double.parseDouble(ratio[1]);

                            if (qty > 0)
                            {
                                if (ca.getCRType().equalsIgnoreCase("Bonus"))
                                    narration = narration + " (BONUS) RATIO " + ratio[0] + ".0000:" + ratio[1] + ".0000";
                                else
                                    narration = narration + " (SPLIT) RATIO " + ratio[0] + ".0000:" + ratio[1] + ".0000 FROM ISIN: TO : ";

                                pnlDataNew.setBQty(String.valueOf(qty));
                                pnlDataNew.setNetQty(String.valueOf(qty));
                                pnlDataNew.setNarration(narration);

                                pnlDataNew.setTradeDateDt(date(ca.getExDate(), YYYYMMDD));
                                pnlDataNew.setTradeDate(date(ca.getExDate(), DDMMYYYY));
                                pnlDataNew.setCreatedAt(String.valueOf(LocalDate.now()));

                                pnlDataNew.setCompanyCode("NSE_CASH");
                                pnlDataNew.setExchange(pnlData.getExchange());
                                pnlDataNew.setClientId(cl.getClientId());
                                pnlDataNew.setMarket(pnlData.getMarket());
                                pnlDataNew.setScripName(pnlData.getScripName());
                                pnlDataNew.setClientName(pnlData.getClientName());
                                pnlDataNew.setBranchName(pnlData.getBranchName());
                                pnlDataNew.setBranchCode(pnlData.getBranchCode());
                                pnlDataNew.setExpiryDate(pnlData.getExpiryDate());
                                pnlDataNew.setBranchCode1(pnlData.getBranchCode1());
                                pnlDataNew.setScripSymbol(pnlData.getScripSymbol());
                                pnlDataNew.setClientIdMail(pnlData.getClientIdMail());
                                pnlDataNew.setBranchCodeMail(pnlData.getBranchCodeMail());
                                log.info("Adding data for client {} . QTY - {} . ", cl.getClientId(), qty);
                                corporateActionService.savePnlDataByCorporateAction(pnlDataNew);
                            }
                        }
//                    }
                });
            }
        } else log.info("No CA found for {}", LocalDate.now());
    }

    private String date(String expDate, String format)
    {
        DateFormat inputFormat = new SimpleDateFormat("dd-MMM-yy");//11-Nov-22
        DateFormat outputFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = inputFormat.parse(expDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputFormat.format(date);
    }
}
