package com.example.xm2.model.homeapi;



import com.example.xm2.bean.HomeBean;
import com.example.xm2.bean.HomeGoodDetailBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HomeApi {
    @GET("index")
    Flowable<HomeBean> getHome();

    //商品购买页详情
    @GET("goods/detail")
    Flowable<HomeGoodDetailBean> getGoodDetail(@Query("id") int id);
}
