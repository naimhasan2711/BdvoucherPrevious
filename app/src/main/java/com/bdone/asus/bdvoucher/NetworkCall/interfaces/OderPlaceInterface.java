package com.bdone.asus.bdvoucher.NetworkCall.interfaces;

import com.bdone.asus.bdvoucher.NetworkCall.models.OderPlaceBodyModel;
import com.bdone.asus.bdvoucher.NetworkCall.models.OderPlacePayLoadModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by ASUS on 3/30/2017.
 */

public interface OderPlaceInterface {


    @POST("/Order/CustomerOrder")
    Call<OderPlacePayLoadModel> oderPlace(@Body OderPlaceBodyModel oderPlaceBodyModel);



}
