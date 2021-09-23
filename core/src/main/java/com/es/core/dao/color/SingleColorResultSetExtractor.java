package com.es.core.dao.color;

import com.es.core.model.phone.Color;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SingleColorResultSetExtractor implements ResultSetExtractor<Color> {

    @Override
    public Color extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Color color = null;
        if (resultSet.next()) {
            color = new Color();
            color.setId(resultSet.getLong(1));
            color.setCode(resultSet.getString(2));
        }
        return color;
    }
}
