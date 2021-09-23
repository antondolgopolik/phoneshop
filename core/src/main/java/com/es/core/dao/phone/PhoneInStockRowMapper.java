package com.es.core.dao.phone;

import com.es.core.model.phone.Phone;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PhoneInStockRowMapper implements RowMapper<Phone> {
    private final PhoneExtractor phoneExtractor = new PhoneExtractor();

    @Override
    public Phone mapRow(ResultSet resultSet, int i) throws SQLException {
        return phoneExtractor.extract(resultSet);
    }
}
