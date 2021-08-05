package com.es.core.model.phone;

import com.es.core.model.color.ColorDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Component
public class JdbcPhoneDao implements PhoneDao {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private ColorDao colorDao;

    public Optional<Phone> get(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID can't be null!");
        }
        String sql = "SELECT * FROM phones WHERE id=?";
        Phone phone = jdbcTemplate.queryForObject(sql, new PhoneRowMapper(colorDao), id);
        return Optional.ofNullable(phone);
    }

    public void save(Phone phone) {
        String sql = "INSERT INTO phones VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                sql, phone.getId(), phone.getBrand(), phone.getModel(),
                phone.getPrice(), phone.getDisplaySizeInches(), phone.getWeightGr(),
                phone.getLengthMm(), phone.getWidthMm(), phone.getHeightMm(),
                phone.getAnnounced(), phone.getDeviceType(), phone.getOs(),
                phone.getDisplayResolution(), phone.getPixelDensity(), phone.getDisplayTechnology(),
                phone.getBackCameraMegapixels(), phone.getFrontCameraMegapixels(), phone.getRamGb(),
                phone.getInternalStorageGb(), phone.getBatteryCapacityMah(), phone.getTalkTimeHours(),
                phone.getStandByTimeHours(), phone.getBluetooth(), phone.getPositioning(),
                phone.getImageUrl(), phone.getDescription()
        );
    }

    public List<Phone> findAll(int offset, int limit) {
        String sql = "SELECT * FROM phones OFFSET " + offset + " LIMIT " + limit;
        return jdbcTemplate.query(sql, new PhoneRowMapper(colorDao));
    }
}
