package com.example.xm2.model.homeapi;



import com.example.xm2.bean.HomeBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface HomeApi {
    @GET("api/index")
    Flowable<HomeBean> getHome();
}
