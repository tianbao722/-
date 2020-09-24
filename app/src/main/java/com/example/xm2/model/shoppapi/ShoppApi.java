package com.example.xm2.model.shoppapi;

import com.example.xm2.bean.ShoppAddBean;
import com.example.xm2.bean.ShoppBean;
import com.example.xm2.bean.ShoppDeleteBean;
import com.example.xm2.bean.ShoppXiaDanBean;

import java.util.HashMap;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ShoppApi {
    //获取购物车数据
    @GET("cart/index")
    Flowable<ShoppBean> getShopp();


    //删除购物车某条数据
    @POST("cart/delete")
    @FormUrlEncoded
    Flowable<ShoppDeleteBean> getShoppDelete(@Header("X-Nideshop-Token") String token, @Field("productIds") String productIds);

    //下单界面数据
    @GET("cart/checkout?addressId=1&couponId=1")
    Flowable<ShoppXiaDanBean> getXiaDan(@Query("addressid") int addressid, @Query("couponid") int couponid);
}
