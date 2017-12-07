package com.bdone.asus.bdvoucher.NetworkCall.models;

/**
 * Created by ASUS on 3/30/2017.
 */

public class SignUpPayLoad {


    String Id;
    String Name;
    String Email;
    String Mobile;
    String CustomerAddress;
    String Password;

    public SignUpPayLoad(String id, String name, String email, String mobile, String customerAddress, String password) {
        Id = id;
        Name = name;
        Email = email;
        Mobile = mobile;
        CustomerAddress = customerAddress;
        Password = password;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getCustomerAddress() {
        return CustomerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        CustomerAddress = customerAddress;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public String toString() {
        return "SignUpPayLoad{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", Email='" + Email + '\'' +
                ", Mobile='" + Mobile + '\'' +
                ", CustomerAddress='" + CustomerAddress + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
