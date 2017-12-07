package com.bdone.asus.bdvoucher.homePage.homeModels;

/**
 * Created by Rasel on 3/5/2017.
 */

public class Category {
    String catName;
    String catId;
    int imgeLink;

    public Category(String catName, String catId, int imgeLink) {
        this.catName = catName;
        this.catId = catId;
        this.imgeLink = imgeLink;
    }
    public Category(String catName, int imgeLink) {
        this.catName = catName;
        this.catId = catId;
        this.imgeLink = imgeLink;
    }


    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public int getImgeLink() {
        return imgeLink;
    }

    public void setImgeLink(int imgeLink) {
        this.imgeLink = imgeLink;
    }
}
