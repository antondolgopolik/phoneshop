package com.es.core.dao.color;

import com.es.core.model.phone.Color;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class JdbcColorDao implements ColorDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public Set<Color> get(Long phoneId) {
        String sql = """
                SELECT c.*
                FROM colors c
                         INNER JOIN phone2color p2c on c.id = p2c.color_id
                where p2c.phone_id = ?
                """;
        return jdbcTemplate.query(sql, new ColorResultSetExtractor(), phoneId);
    }
}
