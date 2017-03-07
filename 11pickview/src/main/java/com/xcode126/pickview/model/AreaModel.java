package com.xcode126.pickview.model;

import java.io.Serializable;

/**
 * 区、县Model
 * Created by Administrator on 2016/10/17.
 */

public class AreaModel implements Serializable{

    private String areas_id;
    private String areas_name;
    private String areas_key;
    private String areas_type;
    private String areas_parent_id;
    private String areas_sort;

    public String getAreas_id() {
        return areas_id;
    }

    public void setAreas_id(String areas_id) {
        this.areas_id = areas_id;
    }

    public String getAreas_name() {
        return areas_name;
    }

    public void setAreas_name(String areas_name) {
        this.areas_name = areas_name;
    }

    public String getAreas_key() {
        return areas_key;
    }

    public void setAreas_key(String areas_key) {
        this.areas_key = areas_key;
    }

    public String getAreas_type() {
        return areas_type;
    }

    public void setAreas_type(String areas_type) {
        this.areas_type = areas_type;
    }

    public String getAreas_parent_id() {
        return areas_parent_id;
    }

    public void setAreas_parent_id(String areas_parent_id) {
        this.areas_parent_id = areas_parent_id;
    }

    public String getAreas_sort() {
        return areas_sort;
    }

    public void setAreas_sort(String areas_sort) {
        this.areas_sort = areas_sort;
    }
}
