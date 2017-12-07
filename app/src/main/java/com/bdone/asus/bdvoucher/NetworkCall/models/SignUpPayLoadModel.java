package com.bdone.asus.bdvoucher.NetworkCall.models;

/**
 * Created by ASUS on 3/29/2017.
 */

public class SignUpPayLoadModel {
    String Name;
    String Email;
    String mobile;
    String Password;
    String PostedOn;

    public SignUpPayLoadModel(String name, String email, String mobile, String password, String postedOn) {
        Name = name;
        Email = email;
        this.mobile = mobile;
        Password = password;
        PostedOn = postedOn;
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
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPostedOn() {
        return PostedOn;
    }

    public void setPostedOn(String postedOn) {
        PostedOn = postedOn;
    }


    @Override
    public String toString() {
        return "SignUpPayLoadModel{" +
                "Name='" + Name + '\'' +
                ", Email='" + Email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", Password='" + Password + '\'' +
                ", PostedOn='" + PostedOn + '\'' +
                '}';
    }
}
