package com.bdone.asus.bdvoucher.NetworkCall.models;

/**
 * Created by ASUS on 3/30/2017.
 */

public class OderPlaceBodyModel {


    int CustomerId;
    String OrderCode;
    int CompanyId;
    String OrderSource;
    int OrderAmount;
    int Quanitity;
    int UnitPrice;
    int ProductId;
    String MobileNumber;

    public OderPlaceBodyModel(int customerId, String orderCode, int companyId, String orderSource, int orderAmount, int quanitity, int unitPrice, int productId, String mobileNumber) {
        CustomerId = customerId;
        OrderCode = orderCode;
        CompanyId = companyId;
        OrderSource = orderSource;
        OrderAmount = orderAmount;
        Quanitity = quanitity;
        UnitPrice = unitPrice;
        ProductId = productId;
        MobileNumber = mobileNumber;
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        CustomerId = customerId;
    }

    public String getOrderCode() {
        return OrderCode;
    }

    public void setOrderCode(String orderCode) {
        OrderCode = orderCode;
    }

    public int getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(int companyId) {
        CompanyId = companyId;
    }

    public String getOrderSource() {
        return OrderSource;
    }

    public void setOrderSource(String orderSource) {
        OrderSource = orderSource;
    }

    public int getOrderAmount() {
        return OrderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        OrderAmount = orderAmount;
    }

    public int getQuanitity() {
        return Quanitity;
    }

    public void setQuanitity(int quanitity) {
        Quanitity = quanitity;
    }

    public int getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        UnitPrice = unitPrice;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return "OderPlaceBodyModel{" +
                "CustomerId=" + CustomerId +
                ", OrderCode='" + OrderCode + '\'' +
                ", CompanyId=" + CompanyId +
                ", OrderSource='" + OrderSource + '\'' +
                ", OrderAmount=" + OrderAmount +
                ", Quanitity=" + Quanitity +
                ", UnitPrice=" + UnitPrice +
                ", ProductId=" + ProductId +
                ", MobileNumber='" + MobileNumber + '\'' +
                '}';
    }
}
