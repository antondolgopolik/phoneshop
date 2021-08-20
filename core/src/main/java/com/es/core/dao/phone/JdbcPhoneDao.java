package com.es.core.dao.phone;

import com.es.core.model.phone.Phone;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcPhoneDao implements PhoneDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Phone> get(Long id) {
        String sql = """
                SELECT p.*, ARRAY_AGG(c.id), ARRAY_AGG(c.code)
                FROM phones p
                         INNER JOIN phone2color p2c on p.id = p2c.phoneId
                         INNER JOIN colors c on c.id = p2c.colorId
                WHERE p.id = ?
                GROUP BY p.id;
                """;
        Phone phone = jdbcTemplate.query(sql, new SinglePhoneResultSetExtractor(), id);
        return Optional.ofNullable(phone);
    }

    @Override
    public void save(Phone phone) {
        String sql = """
                INSERT INTO phones
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;
        Object[] args = new Object[]{
                phone.getId(), phone.getBrand(), phone.getModel(),
                phone.getPrice(), phone.getDisplaySizeInches(), phone.getWeightGr(),
                phone.getLengthMm(), phone.getWidthMm(), phone.getHeightMm(),
                phone.getAnnounced(), phone.getDeviceType(), phone.getOs(),
                phone.getDisplayResolution(), phone.getPixelDensity(), phone.getDisplayTechnology(),
                phone.getBackCameraMegapixels(), phone.getFrontCameraMegapixels(), phone.getRamGb(),
                phone.getInternalStorageGb(), phone.getBatteryCapacityMah(), phone.getTalkTimeHours(),
                phone.getStandByTimeHours(), phone.getBluetooth(), phone.getPositioning(),
                phone.getImageUrl(), phone.getDescription()
        };
        int[] argTypes = new int[]{
                Types.BIGINT, Types.VARCHAR, Types.VARCHAR, Types.FLOAT, Types.FLOAT,
                Types.SMALLINT, Types.FLOAT, Types.FLOAT, Types.FLOAT, Types.DATE,
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.SMALLINT, Types.VARCHAR,
                Types.FLOAT, Types.FLOAT, Types.FLOAT, Types.FLOAT, Types.SMALLINT, Types.FLOAT,
                Types.FLOAT, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR
        };
        jdbcTemplate.update(sql, args, argTypes);
    }

    @Override
    public List<Phone> findInStock(String request,
                                   PhoneSortType phoneSortType,
                                   SortDirection sortDirection,
                                   int offset,
                                   int limit) {
        // Build query
        PhoneQueryBuilder phoneQueryBuilder = new PhoneQueryBuilder();
        phoneQueryBuilder.setRequest(request);
        phoneQueryBuilder.setPhoneSortType(phoneSortType);
        phoneQueryBuilder.setSortDirection(sortDirection);
        phoneQueryBuilder.setOffset(offset);
        phoneQueryBuilder.setLimit(limit);
        // Make query
        return jdbcTemplate.query(phoneQueryBuilder.build(), new PhoneInStockRowMapper());
    }

    @Override
    public Integer getPhonesInStockNumber() {
        String sql = "SELECT COUNT(*) FROM stocks";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
