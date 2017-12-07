package com.bdone.asus.bdvoucher.NetworkCall.models;

/**
 * Created by ASUS on 3/25/2017.
 */

public class CategoryWiseSendBody {

    int CompanyId;
    String VoucherEndDate;
    int CategoryId;
    int SubCategoryId;
    int LowerLimit;
    int UpperLimit;
    public CategoryWiseSendBody() {
    }
    public CategoryWiseSendBody(int companyId, String voucherEndDate, int categoryId, int subCategoryId, int lowerLimit, int upperLimit) {
        CompanyId = companyId;
        VoucherEndDate = voucherEndDate;
        CategoryId = categoryId;
        SubCategoryId = subCategoryId;
        LowerLimit = lowerLimit;
        UpperLimit = upperLimit;
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
        return "CategoryWiseSendBody{" +
                "CompanyId=" + CompanyId +
                ", VoucherEndDate='" + VoucherEndDate + '\'' +
                ", CategoryId=" + CategoryId +
                ", SubCategoryId=" + SubCategoryId +
                ", LowerLimit=" + LowerLimit +
                ", UpperLimit=" + UpperLimit +
                '}';
    }
}
