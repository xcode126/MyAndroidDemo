package com.xcode126.sidelistview;

import java.util.List;

/**
 * Created by sky on 2017/5/4.
 * 镇Model
 */

public class TownBean {
    private String townName;
    private List<FactoryBean> list;
    private String townId;

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public List<FactoryBean> getList() {
        return list;
    }

    public void setList(List<FactoryBean> list) {
        this.list = list;
    }

    public String getTownId() {
        return townId;
    }

    public void setTownId(String townId) {
        this.townId = townId;
    }
}
