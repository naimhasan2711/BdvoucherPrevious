package com.bdone.asus.bdvoucher.NetworkCall.models;

/**
 * Created by ASUS on 3/30/2017.
 */

public class OderPlacePayLoadModel {

    int OrderId;
    String MobileNumber;
    boolean isSmsSent;

    public OderPlacePayLoadModel(int orderId, String mobileNumber, boolean isSmsSent) {
        OrderId = orderId;
        MobileNumber = mobileNumber;
        this.isSmsSent = isSmsSent;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public boolean isSmsSent() {
        return isSmsSent;
    }

    public void setSmsSent(boolean smsSent) {
        isSmsSent = smsSent;
    }

    @Override
    public String toString() {
        return "OderPlacePayLoadModel{" +
                "OrderId=" + OrderId +
                ", MobileNumber='" + MobileNumber + '\'' +
                ", isSmsSent=" + isSmsSent +
                '}';
    }
}
