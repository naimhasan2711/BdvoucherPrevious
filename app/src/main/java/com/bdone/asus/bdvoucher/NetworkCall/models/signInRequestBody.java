package com.bdone.asus.bdvoucher.NetworkCall.models;

/**
 * Created by Rasel on 3/28/2017.
 */

public class signInRequestBody {
    String CustomerEmail;
    String CustomerPassword;

    public signInRequestBody(String customerEmail, String customerPassword) {
        CustomerEmail = customerEmail;
        CustomerPassword = customerPassword;
    }

    public String getCustomerEmail() {
        return CustomerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        CustomerEmail = customerEmail;
    }

    public String getCustomerPassword() {
        return CustomerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        CustomerPassword = customerPassword;
    }

    @Override
    public String toString() {
        return "signInRequestBody{" +
                "CustomerEmail='" + CustomerEmail + '\'' +
                ", CustomerPassword='" + CustomerPassword + '\'' +
                '}';
    }
}
