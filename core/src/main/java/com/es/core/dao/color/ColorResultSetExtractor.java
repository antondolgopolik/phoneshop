package com.es.core.dao.color;

import com.es.core.model.phone.Color;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ColorResultSetExtractor implements ResultSetExtractor<Set<Color>> {

    @Override
    public Set<Color> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Set<Color> colors = new HashSet<>();
        while (resultSet.next()) {
            Color color = new Color();
            color.setId(resultSet.getLong(1));
            color.setCode(resultSet.getString(2));
            colors.add(color);
        }
        return colors;
    }
}
