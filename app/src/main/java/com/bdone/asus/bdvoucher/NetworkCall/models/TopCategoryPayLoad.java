package com.bdone.asus.bdvoucher.NetworkCall.models;

/**
 * Created by ASUS on 3/24/2017.
 */

public class TopCategoryPayLoad {

    private int CategoryId;
    private String CategoryNameEng;
    private String CategoryNameBng;
    private int IsActive;


    public TopCategoryPayLoad(int categoryId, String categoryNameEng, String categoryNameBng, int isActive) {

        CategoryId = categoryId;
        CategoryNameEng = categoryNameEng;
        CategoryNameBng = categoryNameBng;
        IsActive = isActive;
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

    public int getIsActive() {
        return IsActive;
    }

    public void setIsActive(int isActive) {
        IsActive = isActive;
    }

    @Override
    public String toString() {
        return "TopCategoryPayLoad{" +
                "CategoryId=" + CategoryId +
                ", CategoryNameEng='" + CategoryNameEng + '\'' +
                ", CategoryNameBng='" + CategoryNameBng + '\'' +
                ", IsActive=" + IsActive +
                '}';
    }
}
