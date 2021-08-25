package com.es.core.model.color;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class JdbcColorDao implements ColorDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Color> get(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID can't be null!");
        }
        String sql = "SELECT * FROM colors WHERE id=?";
        Color color = jdbcTemplate.query(sql, new SingleColorResultSetExtractor(), id);
        return Optional.ofNullable(color);
    }

    @Override
    public Set<Color> getByPhoneId(Long phoneId) {
        Set<Color> colors = new HashSet<>();
        for (Long colorId : getColorIdsByPhoneId(phoneId)) {
            get(colorId).ifPresent(colors::add);
        }
        return colors;
    }

    private List<Long> getColorIdsByPhoneId(Long phoneId) {
        String sql = "SELECT * FROM phone2color WHERE phoneId=?";
        return jdbcTemplate.query(sql, (resultSet, i) -> resultSet.getLong(2), phoneId);
    }
}
