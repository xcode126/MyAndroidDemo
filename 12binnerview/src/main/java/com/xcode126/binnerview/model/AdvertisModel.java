package com.xcode126.binnerview.model;

import java.io.Serializable;

/**
 * Created by sky on 2017/2/28.
 * 广告Model
 */

public class AdvertisModel implements Serializable {
    private String ad_id;/*广告ID*/
    private String ad_pid;/*广告位id暂定两种广告位1:滚动广告 2:本地服务广告*/
    private String ad_name;/*广告别名*/

    /**
     * 广告类型 (ad_type)1:外部网页链接广告 2:内部商品广告 3:内部店铺广告
     * 前端根据不同的广告类型进行不同的点击事件.
     * 外部网页链接广告需要使用网页打开ad_link对应的网站，
     * 内部商品广告需要打开APP商品详情页,
     * 内部店铺广告需要打开APP店铺首页
     */
    private String ad_type;
    private String ad_url;/*广告图片地址*/
    private String ad_link;/*跳转连接*/
    private String ad_goods_id;/*当广告为内部商品广告时的商品id*/
    private String ad_seller_id; /*当广告为内部店铺广告时的商家id*/

    public String getAd_id() {
        return ad_id;
    }

    public void setAd_id(String ad_id) {
        this.ad_id = ad_id;
    }

    public String getAd_pid() {
        return ad_pid;
    }

    public void setAd_pid(String ad_pid) {
        this.ad_pid = ad_pid;
    }

    public String getAd_name() {
        return ad_name;
    }

    public void setAd_name(String ad_name) {
        this.ad_name = ad_name;
    }

    public String getAd_type() {
        return ad_type;
    }

    public void setAd_type(String ad_type) {
        this.ad_type = ad_type;
    }

    public String getAd_url() {
        return ad_url;
    }

    public void setAd_url(String ad_url) {
        this.ad_url = ad_url;
    }

    public String getAd_link() {
        return ad_link;
    }

    public void setAd_link(String ad_link) {
        this.ad_link = ad_link;
    }

    public String getAd_goods_id() {
        return ad_goods_id;
    }

    public void setAd_goods_id(String ad_goods_id) {
        this.ad_goods_id = ad_goods_id;
    }

    public String getAd_seller_id() {
        return ad_seller_id;
    }

    public void setAd_seller_id(String ad_seller_id) {
        this.ad_seller_id = ad_seller_id;
    }
}
