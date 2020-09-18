package com.example.xm2.model.shoppapi;

import com.example.xm2.bean.ShoppAddBean;
import com.example.xm2.bean.ShoppBean;
import com.example.xm2.bean.ShoppDeleteBean;

import java.util.HashMap;

import io.reactivex.Flowable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ShoppApi {
    //获取购物车数据
    @GET("cart/index")
    Flowable<ShoppBean> getShopp();

    //添加数据到购物车
    @POST("cart/add")
    @FormUrlEncoded
    Flowable<ShoppAddBean> getShoppAdd(@Header ("X-Nideshop-Token") String token, @FieldMap HashMap<String,String> map);

    //删除购物车某条数据
    @POST("cart/delete?productIds=171")
    Flowable<ShoppDeleteBean> getShoppDelete(@Header ("X-Nideshop-Token") String token, @Query("productIds") String productIds);
}
