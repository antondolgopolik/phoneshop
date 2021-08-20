package com.es.core.dao.phone;

import com.es.core.model.phone.Phone;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class PhoneInStockRowMapper implements RowMapper<Phone> {
    private final PhoneDao phoneDao;

    public PhoneInStockRowMapper(PhoneDao phoneDao) {
        this.phoneDao = phoneDao;
    }

    @Override
    public Phone mapRow(ResultSet resultSet, int i) throws SQLException {
        Optional<Phone> phoneOptional = phoneDao.get(resultSet.getLong(1));
        return phoneOptional.orElseThrow();
    }
}
