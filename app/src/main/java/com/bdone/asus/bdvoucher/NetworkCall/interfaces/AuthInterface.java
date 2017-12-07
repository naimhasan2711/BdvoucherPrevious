package com.bdone.asus.bdvoucher.NetworkCall.interfaces;


import com.bdone.asus.bdvoucher.NetworkCall.models.SignInPayload;
import com.bdone.asus.bdvoucher.NetworkCall.models.SignUpPayLoad;
import com.bdone.asus.bdvoucher.NetworkCall.models.SignUpPayLoadModel;
import com.bdone.asus.bdvoucher.NetworkCall.models.signInRequestBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Rasel on 3/28/2017.
 */

public interface AuthInterface {


    @POST("/CustomerAccess/Login")
    Call<SignInPayload> signIn(@Body signInRequestBody signInRequestBody);


    @POST("/CustomerAccess/SignUp")
    Call<SignUpPayLoad> signUp(@Body SignUpPayLoadModel signInRequestBody);
}
