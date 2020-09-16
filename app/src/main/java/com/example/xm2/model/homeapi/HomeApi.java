package com.example.xm2.model.homeapi;


import com.example.xm2.bean.HomeBean;
import com.example.xm2.bean.HomeGoodDetailBean;
import com.example.xm2.bean.UserBean;

import java.util.HashMap;

import io.reactivex.Flowable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface HomeApi {
    @GET("index")
    Flowable<HomeBean> getHome();

    //商品购买页详情
    @GET("goods/detail")
    Flowable<HomeGoodDetailBean> getGoodDetail(@Query("id") int id);

    //登录
    @POST("auth/login")
    @FormUrlEncoded
    Flowable<UserBean> getLogin(@FieldMap HashMap<String, String> map);
}
