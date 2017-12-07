package com.bdone.asus.bdvoucher.NetworkCall.models;

import java.util.List;

/**
 * Created by ASUS on 3/24/2017.
 */

public class HomeBlockModel {
    int CategoryId;
    String CategoryNameEng;
    String CategoryNameBng;
    List<HomeBlockSingleIteam> ProductData;

    public HomeBlockModel(int categoryId, String categoryNameEng, String categoryNameBng, List<HomeBlockSingleIteam> productData) {
        CategoryId = categoryId;
        CategoryNameEng = categoryNameEng;
        CategoryNameBng = categoryNameBng;
        ProductData = productData;
    }


    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }

    public String getCategoryNameEng() {
        return CategoryNameEng;
    }

    public void setCategoryNameEng(String categoryNameEng) {
        CategoryNameEng = categoryNameEng;
    }

    public String getCategoryNameBng() {
        return CategoryNameBng;
    }

    public void setCategoryNameBng(String categoryNameBng) {
        CategoryNameBng = categoryNameBng;
    }

    public List<HomeBlockSingleIteam> getProductData() {
        return ProductData;
    }

    public void setProductData(List<HomeBlockSingleIteam> productData) {
        ProductData = productData;
    }

    @Override
    public String toString() {
        return "HomeBlockModel{" +
                "CategoryId=" + CategoryId +
                ", CategoryNameEng='" + CategoryNameEng + '\'' +
                ", CategoryNameBng='" + CategoryNameBng + '\'' +
                ", ProductData=" + ProductData +
                '}';
    }
}
