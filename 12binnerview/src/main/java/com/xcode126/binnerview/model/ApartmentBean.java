package com.xcode126.binnerview.model;

import java.io.Serializable;

/**
 * Created by sky on 2017/3/1.
 */

public class ApartmentBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String imageUrl;
    private String apartmentTitle;
    private String apartmentArea;
    private String apartmentPrice;
    private String apartmentFeaturedOne;
    private String apartmentFeaturedTwo;
    private String apartmentFeaturedThree;

    public ApartmentBean() {
    }

    public ApartmentBean(String apartmentTitle, String apartmentArea, String apartmentPrice, String apartmentFeaturedOne, String apartmentFeaturedTwo, String apartmentFeaturedThree) {
        this.apartmentTitle = apartmentTitle;
        this.apartmentArea = apartmentArea;
        this.apartmentPrice = apartmentPrice;
        this.apartmentFeaturedOne = apartmentFeaturedOne;
        this.apartmentFeaturedTwo = apartmentFeaturedTwo;
        this.apartmentFeaturedThree = apartmentFeaturedThree;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getApartmentTitle() {
        return apartmentTitle;
    }

    public void setApartmentTitle(String apartmentTitle) {
        this.apartmentTitle = apartmentTitle;
    }

    public String getApartmentArea() {
        return apartmentArea;
    }

    public void setApartmentArea(String apartmentArea) {
        this.apartmentArea = apartmentArea;
    }

    public String getApartmentPrice() {
        return apartmentPrice;
    }

    public void setApartmentPrice(String apartmentPrice) {
        this.apartmentPrice = apartmentPrice;
    }

    public String getApartmentFeaturedOne() {
        return apartmentFeaturedOne;
    }

    public void setApartmentFeaturedOne(String apartmentFeaturedOne) {
        this.apartmentFeaturedOne = apartmentFeaturedOne;
    }

    public String getApartmentFeaturedTwo() {
        return apartmentFeaturedTwo;
    }

    public void setApartmentFeaturedTwo(String apartmentFeaturedTwo) {
        this.apartmentFeaturedTwo = apartmentFeaturedTwo;
    }

    public String getApartmentFeaturedThree() {
        return apartmentFeaturedThree;
    }

    public void setApartmentFeaturedThree(String apartmentFeaturedThree) {
        this.apartmentFeaturedThree = apartmentFeaturedThree;
    }
}
