package com.goldcn.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by jianhao84 on 2016/6/15.
 */
@ConfigurationProperties(prefix = "file")
public class PropertiesConfig {

    private String permitType;
    private String permitSize;
    private String measureType;
    private String goodsUrl;

    public String getGoodsUrl() {
        return goodsUrl;
    }

    public void setGoodsUrl(String goodsUrl) {
        this.goodsUrl = goodsUrl;
    }

    public String getPermitType() {
        return permitType;
    }

    public void setPermitType(String permitType) {
        this.permitType = permitType;
    }

    public Long getPermitSize() {
        return Integer.parseInt(permitSize)*1024L*1024L;
    }

    public void setPermitSize(String permitSize) {
        this.permitSize = permitSize;
    }

    public String getMeasureType() {
        return measureType;
    }

    public void setMeasureType(String measureType) {
        this.measureType = measureType;
    }
}
