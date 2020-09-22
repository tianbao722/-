package com.example.xm2.model.homeapi;


import androidx.constraintlayout.helper.widget.Flow;

import com.example.xm2.bean.HomeBean;
import com.example.xm2.bean.HomeGoodDetailBean;
import com.example.xm2.bean.HomeNewBean;
import com.example.xm2.bean.HomeNewTopBean;
import com.example.xm2.bean.MyadressBean;
import com.example.xm2.bean.ShoppAddBean;
import com.example.xm2.bean.SpecialBean;
import com.example.xm2.bean.UserBean;

import java.util.HashMap;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

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

    //注册
    @POST("auth/register")
    @FormUrlEncoded
    Flowable<UserBean> getZhuCe(@FieldMap HashMap<String,String> map);

    //专题
    @GET("topic/list")
    Flowable<SpecialBean> getSpecial(@Query("page") int page,@Query("size") int size);

    //添加到购物车
    @POST("cart/add")
    @FormUrlEncoded
    Flowable<ShoppAddBean> addCart(@Field("goodsId") int goodsId, @Field("number") int number, @Field("productId") int productId);

    //加载省份数据
    @GET("region/list")
    Flowable<MyadressBean> getAdressById(@Query("parentId") int parentId);

    //新品首发Top数据
    @GET("goods/hot")
    Flowable<HomeNewTopBean> getNewTop();

    @GET("goods/list")
    Flowable<HomeNewBean> getNew(@QueryMap HashMap<String,String> map);
}
