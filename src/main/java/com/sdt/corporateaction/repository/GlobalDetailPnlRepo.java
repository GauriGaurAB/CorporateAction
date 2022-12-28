package com.sdt.corporateaction.repository;

import com.sdt.corporateaction.entity.GlobalDetailPnl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GlobalDetailPnlRepo extends JpaRepository<GlobalDetailPnl, Long> {

    @Query(value = "SELECT * FROM tech_bo.GLOBAL_DETAIL_PNL where company_code='BSE_CASH' and SCRIP_SYMBOL like %:securityCode% and CLIENT_ID = :clientId and TRADE_DATEDT <= :expDate order by id desc limit 1;", nativeQuery = true)
    GlobalDetailPnl getDetailsForCorporateAction(@Param("securityCode") String securityCode, @Param("clientId") String clientId, @Param("expDate") String expDate);
}
