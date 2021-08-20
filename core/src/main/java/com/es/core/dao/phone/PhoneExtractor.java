package com.es.core.dao.phone;

import com.es.core.dao.color.ColorDao;
import com.es.core.model.phone.Phone;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PhoneExtractor {
    private final ColorDao colorDao;

    public PhoneExtractor(ColorDao colorDao) {
        this.colorDao = colorDao;
    }

    public Phone extract(ResultSet resultSet) throws SQLException {
        Phone phone = new Phone();
        phone.setId(resultSet.getLong(1));
        phone.setBrand(resultSet.getString(2));
        phone.setModel(resultSet.getString(3));
        phone.setPrice(resultSet.getBigDecimal(4));
        phone.setDisplaySizeInches(resultSet.getBigDecimal(5));
        phone.setWeightGr(resultSet.getInt(6));
        phone.setLengthMm(resultSet.getBigDecimal(7));
        phone.setWidthMm(resultSet.getBigDecimal(8));
        phone.setHeightMm(resultSet.getBigDecimal(9));
        phone.setAnnounced(resultSet.getDate(10));
        phone.setDeviceType(resultSet.getString(11));
        phone.setOs(resultSet.getString(12));
        phone.setColors(colorDao.getByPhoneId(phone.getId()));
        phone.setDisplayResolution(resultSet.getString(13));
        phone.setPixelDensity(resultSet.getInt(14));
        phone.setDisplayTechnology(resultSet.getString(15));
        phone.setBackCameraMegapixels(resultSet.getBigDecimal(16));
        phone.setFrontCameraMegapixels(resultSet.getBigDecimal(17));
        phone.setRamGb(resultSet.getBigDecimal(18));
        phone.setInternalStorageGb(resultSet.getBigDecimal(19));
        phone.setBatteryCapacityMah(resultSet.getInt(20));
        phone.setTalkTimeHours(resultSet.getBigDecimal(21));
        phone.setStandByTimeHours(resultSet.getBigDecimal(22));
        phone.setBluetooth(resultSet.getString(23));
        phone.setPositioning(resultSet.getString(24));
        phone.setImageUrl(resultSet.getString(25));
        phone.setDescription(resultSet.getString(26));
        return phone;
    }
}
