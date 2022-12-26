package com.sdt.corporateaction.repository;

import com.sdt.corporateaction.entity.ClientList;
import com.sdt.corporateaction.mapper.ClientListMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class DataRepository {

    private final JdbcTemplate jdbcTemplate;

    public DataRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<ClientList> getClientsEligibleForCA(String expDate, String securityCode)
    {
        String sql = "select sum(SQTY) as sQty, sum(BQTY) as bQty, CLIENT_ID from tech_bo.GLOBAL_DETAIL_PNL where company_code='BSE_CASH' and TRADE_DATEDT <= ? and SCRIP_SYMBOL like ? group by CLIENT_ID having (bQty-sQty) > 0";
        List<ClientList> clients = new ArrayList<>();
        try {
            clients = jdbcTemplate.query(sql, ps -> {
                ps.setString(1, expDate);
                ps.setString(2, "%" + securityCode + "%");
            }, new ClientListMapper());
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
        }
        return clients;
    }

}
