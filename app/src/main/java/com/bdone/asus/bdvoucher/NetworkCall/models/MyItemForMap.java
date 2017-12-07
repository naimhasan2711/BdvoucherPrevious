package com.bdone.asus.bdvoucher.NetworkCall.models;

/**
 * Created by Rasel on 5/21/2017.
 */

public class MyItemForMap {
    double lat;
    double lng;
    String title;
    String snippet;
    int CompanyId;
    String CompanyName;
    int Id;
    int ImageCount;
    String OfferDetails;
    String ProductCode;
    String ProductDetails;
    String PromotionalUrl;

    public MyItemForMap() {
    }

    public MyItemForMap(double lat, double lng, String title, String snippet, int companyId, String companyName, int id, int imageCount, String offerDetails, String productCode, String productDetails, String promotionalUrl) {
        this.lat = lat;
        this.lng = lng;
        this.title = title;
        this.snippet = snippet;
        CompanyId = companyId;
        CompanyName = companyName;
        Id = id;
        ImageCount = imageCount;
        OfferDetails = offerDetails;
        ProductCode = productCode;
        ProductDetails = productDetails;
        PromotionalUrl = promotionalUrl;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public int getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(int companyId) {
        CompanyId = companyId;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getImageCount() {
        return ImageCount;
    }

    public void setImageCount(int imageCount) {
        ImageCount = imageCount;
    }

    public String getOfferDetails() {
        return OfferDetails;
    }

    public void setOfferDetails(String offerDetails) {
        OfferDetails = offerDetails;
    }

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String productCode) {
        ProductCode = productCode;
    }

    public String getProductDetails() {
        return ProductDetails;
    }

    public void setProductDetails(String productDetails) {
        ProductDetails = productDetails;
    }

    public String getPromotionalUrl() {
        return PromotionalUrl;
    }

    public void setPromotionalUrl(String promotionalUrl) {
        PromotionalUrl = promotionalUrl;
    }
}


