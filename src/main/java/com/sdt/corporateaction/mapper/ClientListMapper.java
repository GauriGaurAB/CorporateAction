package com.sdt.corporateaction.mapper;

import com.sdt.corporateaction.entity.ClientList;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientListMapper implements RowMapper<ClientList> {

    @Override
    public ClientList mapRow(ResultSet rs, int rowNum) throws SQLException {
        ClientList client = new ClientList();
        client.setClientId(rs.getString("CLIENT_ID"));
        client.setBQty(Double.parseDouble(rs.getString("bQty")));
        client.setSQty(Double.parseDouble(rs.getString("sQty")));
        return client;
    }
}
