package com.es.core.model.phone;

import com.es.core.model.color.ColorDao;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SinglePhoneResultSetExtractor implements ResultSetExtractor<Phone> {
    private final PhoneExtractor phoneExtractor;

    public SinglePhoneResultSetExtractor(ColorDao colorDao) {
        phoneExtractor = new PhoneExtractor(colorDao);
    }

    @Override
    public Phone extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Phone phone = null;
        if (resultSet.next()) {
            phone = phoneExtractor.extract(resultSet);
        }
        return phone;
    }
}
