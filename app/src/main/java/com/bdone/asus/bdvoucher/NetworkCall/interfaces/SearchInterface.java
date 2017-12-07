package com.bdone.asus.bdvoucher.NetworkCall.interfaces;

import com.bdone.asus.bdvoucher.NetworkCall.models.RecomandedProducts;
import com.bdone.asus.bdvoucher.NetworkCall.models.SeachRequestBody;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Rasel on 3/30/2017.
 */

public interface SearchInterface {

    @POST("/Home/Products/")
    Call<List<RecomandedProducts>> getSearch(@Body SeachRequestBody seachRequestBody);
}
