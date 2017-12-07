package com.bdone.asus.bdvoucher.homePage.homeModels;

/**
 * Created by ASUS on 3/10/2017.
 */

public class RecommendedVoucherModel {


    int imageLink;
    String sortDetails;

    public RecommendedVoucherModel(int imageLink, String sortDetails) {
        this.imageLink = imageLink;
        this.sortDetails = sortDetails;
    }

    public int getImageLink() {
        return imageLink;
    }

    public void setImageLink(int imageLink) {
        this.imageLink = imageLink;
    }

    public String getSortDetails() {
        return sortDetails;
    }

    public void setSortDetails(String sortDetails) {
        this.sortDetails = sortDetails;
    }
}
