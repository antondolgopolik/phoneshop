package com.es.core.dao.stock;

import com.es.core.model.phone.Stock;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SingleStockResultSetExtractor implements ResultSetExtractor<Stock> {

    @Override
    public Stock extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Stock stock = null;
        if (resultSet.next()) {
            stock = new Stock();
            stock.setPhoneId(resultSet.getLong(1));
            stock.setStock(resultSet.getInt(2));
            stock.setReserved(resultSet.getInt(3));
        }
        return stock;
    }
}
