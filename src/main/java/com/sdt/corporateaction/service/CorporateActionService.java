package com.sdt.corporateaction.service;

import com.sdt.corporateaction.entity.CorporateAction;
import com.sdt.corporateaction.entity.GlobalDetailPnl;
import com.sdt.corporateaction.entity.GlobalDetailPnlTmp;

import java.util.List;

public interface CorporateActionService {

    List<CorporateAction> getCorporateActions();

    GlobalDetailPnl getDetailsForCorporateAction(String securityCode, String clientId, String expDate);

    void savePnlDataByCorporateAction(GlobalDetailPnlTmp globalDetailPnlTmp);
}
