package com.bdone.asus.bdvoucher.NetworkCall.models;

/**
 * Created by ASUS on 3/25/2017.
 */

public class CateogrywisePayloadModel {

    int Id;
    String ProductTitle;
    int CompanyId;
    String ProductDetails;
    String OfferDetails;
    String OfferStartTime;
    String OfferEndTime;
    int BrandId;
    int UnitPrice;
    String ProductCode;
    String CompanyName;
    int ImageCount;

    public CateogrywisePayloadModel() {
    }

    public CateogrywisePayloadModel(int id, String productTitle, int companyId, String productDetails, String offerDetails, String offerStartTime, String offerEndTime, int brandId, int unitPrice, String productCode, String companyName, int imageCount) {
        Id = id;
        ProductTitle = productTitle;
        CompanyId = companyId;
        ProductDetails = productDetails;
        OfferDetails = offerDetails;
        OfferStartTime = offerStartTime;
        OfferEndTime = offerEndTime;
        BrandId = brandId;
        UnitPrice = unitPrice;
        ProductCode = productCode;
        CompanyName = companyName;
        ImageCount = imageCount;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getProductTitle() {
        return ProductTitle;
    }

    public void setProductTitle(String productTitle) {
        ProductTitle = productTitle;
    }

    public int getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(int companyId) {
        CompanyId = companyId;
    }

    public String getProductDetails() {
        return ProductDetails;
    }

    public void setProductDetails(String productDetails) {
        ProductDetails = productDetails;
    }

    public String getOfferDetails() {
        return OfferDetails;
    }

    public void setOfferDetails(String offerDetails) {
        OfferDetails = offerDetails;
    }

    public String getOfferStartTime() {
        return OfferStartTime;
    }

    public void setOfferStartTime(String offerStartTime) {
        OfferStartTime = offerStartTime;
    }

    public String getOfferEndTime() {
        return OfferEndTime;
    }

    public void setOfferEndTime(String offerEndTime) {
        OfferEndTime = offerEndTime;
    }

    public int getBrandId() {
        return BrandId;
    }

    public void setBrandId(int brandId) {
        BrandId = brandId;
    }

    public int getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        UnitPrice = unitPrice;
    }

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String productCode) {
        ProductCode = productCode;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public int getImageCount() {
        return ImageCount;
    }

    public void setImageCount(int imageCount) {
        ImageCount = imageCount;
    }

    @Override
    public String toString() {
        return "CateogrywisePayloadModel{" +
                "Id=" + Id +
                ", ProductTitle='" + ProductTitle + '\'' +
                ", CompanyId=" + CompanyId +
                ", ProductDetails='" + ProductDetails + '\'' +
                ", OfferDetails='" + OfferDetails + '\'' +
                ", OfferStartTime='" + OfferStartTime + '\'' +
                ", OfferEndTime='" + OfferEndTime + '\'' +
                ", BrandId=" + BrandId +
                ", UnitPrice=" + UnitPrice +
                ", ProductCode='" + ProductCode + '\'' +
                ", CompanyName='" + CompanyName + '\'' +
                ", ImageCount=" + ImageCount +
                '}';
    }
}
