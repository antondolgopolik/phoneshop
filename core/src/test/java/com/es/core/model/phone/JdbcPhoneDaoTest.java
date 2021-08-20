package com.es.core.model.phone;

import com.es.core.dao.phone.PhoneDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/context/test-applicationContext-core.xml")
public class JdbcPhoneDaoTest {
    @Resource
    private PhoneDao phoneDao;

    @Test
    public void saveTest() {
        Phone phone = new Phone();
        phone.setId(1L);
        phone.setBrand("Brand");
        phone.setModel("Model");
        phone.setPrice(new BigDecimal(1));
        phone.setDisplaySizeInches(new BigDecimal(1));
        phone.setWeightGr(1);
        phone.setLengthMm(new BigDecimal(1));
        phone.setWidthMm(new BigDecimal(1));
        phone.setHeightMm(new BigDecimal(1));
        phone.setAnnounced(new Date());
        phone.setDeviceType("DeviceType");
        phone.setOs("Os");
        phone.setDisplayResolution("DisplayResolution");
        phone.setPixelDensity(1);
        phone.setDisplayTechnology("DisplayTechnology");
        phone.setBackCameraMegapixels(new BigDecimal(1));
        phone.setFrontCameraMegapixels(new BigDecimal(1));
        phone.setRamGb(new BigDecimal(1));
        phone.setInternalStorageGb(new BigDecimal(1));
        phone.setBatteryCapacityMah(1);
        phone.setTalkTimeHours(new BigDecimal(1));
        phone.setStandByTimeHours(new BigDecimal(1));
        phone.setBluetooth("Bluetooth");
        phone.setPositioning("Positioning");
        phone.setImageUrl("ImageUrl");
        phone.setDescription("Description");
        phoneDao.save(phone);
    }

    @Test
    public void getTest() {
        Phone phone = phoneDao.get(1000L).get();
        Assert.assertEquals(phone.getId(), Long.valueOf(1000));
    }
}
