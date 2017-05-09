package com.xcode126.sidelistview;

/**
 * Created by sky on 2017/5/4.
 * 工厂Model
 */

public class FactoryBean {
    private String type;
    private String factoryName;
    private String townId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getTownId() {
        return townId;
    }

    public void setTownId(String townId) {
        this.townId = townId;
    }
}
