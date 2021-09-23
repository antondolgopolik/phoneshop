package com.es.core.model.phone;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

public class Phone {
    private Long id;
    private String brand;
    private String model;
    private BigDecimal price;
    private BigDecimal displaySize;
    private Integer weight;
    private BigDecimal length;
    private BigDecimal width;
    private BigDecimal height;
    private Date announced;
    private String deviceType;
    private String os;
    private Set<Color> colors;
    private String displayResolution;
    private Integer pixelDensity;
    private String displayTechnology;
    private BigDecimal backCameraMegapixels;
    private BigDecimal frontCameraMegapixels;
    private BigDecimal ram;
    private BigDecimal internalStorage;
    private Integer batteryCapacity;
    private BigDecimal talkTime;
    private BigDecimal standByTime;
    private String bluetooth;
    private String positioning;
    private String imageUrl;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDisplaySize() {
        return displaySize;
    }

    public void setDisplaySize(BigDecimal displaySize) {
        this.displaySize = displaySize;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public Date getAnnounced() {
        return announced;
    }

    public void setAnnounced(Date announced) {
        this.announced = announced;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public Set<Color> getColors() {
        return colors;
    }

    public void setColors(Set<Color> colors) {
        this.colors = colors;
    }

    public String getDisplayResolution() {
        return displayResolution;
    }

    public void setDisplayResolution(String displayResolution) {
        this.displayResolution = displayResolution;
    }

    public Integer getPixelDensity() {
        return pixelDensity;
    }

    public void setPixelDensity(Integer pixelDensity) {
        this.pixelDensity = pixelDensity;
    }

    public String getDisplayTechnology() {
        return displayTechnology;
    }

    public void setDisplayTechnology(String displayTechnology) {
        this.displayTechnology = displayTechnology;
    }

    public BigDecimal getBackCameraMegapixels() {
        return backCameraMegapixels;
    }

    public void setBackCameraMegapixels(BigDecimal backCameraMegapixels) {
        this.backCameraMegapixels = backCameraMegapixels;
    }

    public BigDecimal getFrontCameraMegapixels() {
        return frontCameraMegapixels;
    }

    public void setFrontCameraMegapixels(BigDecimal frontCameraMegapixels) {
        this.frontCameraMegapixels = frontCameraMegapixels;
    }

    public BigDecimal getRam() {
        return ram;
    }

    public void setRam(BigDecimal ram) {
        this.ram = ram;
    }

    public BigDecimal getInternalStorage() {
        return internalStorage;
    }

    public void setInternalStorage(BigDecimal internalStorage) {
        this.internalStorage = internalStorage;
    }

    public Integer getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(Integer batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public BigDecimal getTalkTime() {
        return talkTime;
    }

    public void setTalkTime(BigDecimal talkTime) {
        this.talkTime = talkTime;
    }

    public BigDecimal getStandByTime() {
        return standByTime;
    }

    public void setStandByTime(BigDecimal standByTime) {
        this.standByTime = standByTime;
    }

    public String getBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(String bluetooth) {
        this.bluetooth = bluetooth;
    }

    public String getPositioning() {
        return positioning;
    }

    public void setPositioning(String positioning) {
        this.positioning = positioning;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
