package com.bdone.asus.bdvoucher.NetworkCall.models;

/**
 * Created by Rasel on 5/21/2017.
 */

public class BannerModel {
    String bannerClickLink;
    int bannerId;
    String bannerImageLink;

    public BannerModel() {
    }

    public BannerModel(String bannerClickLink, int bannerId, String bannerImageLink) {
        this.bannerClickLink = bannerClickLink;
        this.bannerId = bannerId;
        this.bannerImageLink = bannerImageLink;
    }

    public String getBannerClickLink() {
        return bannerClickLink;
    }

    public void setBannerClickLink(String bannerClickLink) {
        this.bannerClickLink = bannerClickLink;
    }

    public int getBannerId() {
        return bannerId;
    }

    public void setBannerId(int bannerId) {
        this.bannerId = bannerId;
    }

    public String getBannerImageLink() {
        return bannerImageLink;
    }

    public void setBannerImageLink(String bannerImageLink) {
        this.bannerImageLink = bannerImageLink;
    }

    @Override
    public String toString() {
        return "BannerModel{" +
                "bannerClickLink='" + bannerClickLink + '\'' +
                ", bannerId=" + bannerId +
                ", bannerImageLink='" + bannerImageLink + '\'' +
                '}';
    }
}
