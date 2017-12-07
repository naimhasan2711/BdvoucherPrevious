package com.bdone.asus.bdvoucher.NetworkCall.interfaces;

import com.bdone.asus.bdvoucher.NetworkCall.models.CategoryWiseSendBody;

import com.bdone.asus.bdvoucher.NetworkCall.models.RecomandedProducts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by ASUS on 3/25/2017.
 */

public interface InterfaceCategoryWiseProducts {

    @POST("/Home/Products/")
    Call<List<RecomandedProducts>> getCategoryWiseProduct(@Body CategoryWiseSendBody categoryWiseSendBody);


}
