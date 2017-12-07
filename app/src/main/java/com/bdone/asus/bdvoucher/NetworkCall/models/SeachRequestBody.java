package com.bdone.asus.bdvoucher.NetworkCall.models;

/**
 * Created by Rasel on 3/30/2017.
 */

public class SeachRequestBody {

    int CompanyId;
    String VoucherEndDate;
    int CategoryId;
    int SubCategoryId;
    String SearchData;
    int LowerLimit;
    int UpperLimit;

    public SeachRequestBody(int companyId, String voucherEndDate, int categoryId, int subCategoryId, String searchData, int lowerLimit, int upperLimit) {
        CompanyId = companyId;
        VoucherEndDate = voucherEndDate;
        CategoryId = categoryId;
        SubCategoryId = subCategoryId;
        SearchData = searchData;
        LowerLimit = lowerLimit;
        UpperLimit = upperLimit;
    }

    public SeachRequestBody() {
    }


    public int getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(int companyId) {
        CompanyId = companyId;
    }

    public String getVoucherEndDate() {
        return VoucherEndDate;
    }

    public void setVoucherEndDate(String voucherEndDate) {
        VoucherEndDate = voucherEndDate;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }

    public int getSubCategoryId() {
        return SubCategoryId;
    }

    public void setSubCategoryId(int subCategoryId) {
        SubCategoryId = subCategoryId;
    }

    public String getSearchData() {
        return SearchData;
    }

    public void setSearchData(String searchData) {
        SearchData = searchData;
    }

    public int getLowerLimit() {
        return LowerLimit;
    }

    public void setLowerLimit(int lowerLimit) {
        LowerLimit = lowerLimit;
    }

    public int getUpperLimit() {
        return UpperLimit;
    }

    public void setUpperLimit(int upperLimit) {
        UpperLimit = upperLimit;
    }


    @Override
    public String toString() {
        return "SeachRequestBody{" +
                "CompanyId=" + CompanyId +
                ", VoucherEndDate='" + VoucherEndDate + '\'' +
                ", CategoryId=" + CategoryId +
                ", SubCategoryId=" + SubCategoryId +
                ", SearchData='" + SearchData + '\'' +
                ", LowerLimit=" + LowerLimit +
                ", UpperLimit=" + UpperLimit +
                '}';
    }
}
