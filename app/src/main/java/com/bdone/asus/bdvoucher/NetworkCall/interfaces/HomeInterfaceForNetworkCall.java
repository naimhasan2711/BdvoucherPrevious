package com.bdone.asus.bdvoucher.NetworkCall.interfaces;

import com.bdone.asus.bdvoucher.NetworkCall.models.HomeBlockModel;
import com.bdone.asus.bdvoucher.NetworkCall.models.RecomandedProducts;
import com.bdone.asus.bdvoucher.NetworkCall.models.TopCategoryPayLoad;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ASUS on 3/24/2017.
 */

public interface HomeInterfaceForNetworkCall {
    @GET("/Categories/LoadCategories")
    Call<List<TopCategoryPayLoad>> getCategories();


    /*
    @GET("/OrderAction/BookingInfo/{couponId}")
Call<BookingInfoModel>getBookingInfo(@Path("couponId") int couponId);*/
    @GET("/Home/LoadLatestProducts/15/{ishot}")
    Call<List<RecomandedProducts>> getRecomandedProducts(@Path("ishot") int isHot);

    @GET("/home/LoadCategoryWiseProducts/8/0")
    Call<List<HomeBlockModel>> getHomeBlocks();
}
