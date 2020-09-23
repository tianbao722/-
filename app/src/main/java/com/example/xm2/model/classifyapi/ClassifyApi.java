package com.example.xm2.model.classifyapi;

import com.example.xm2.bean.ClassifyBean;
import com.example.xm2.bean.ClassifyRlvBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ClassifyApi {
    @GET("catalog/index")
    Flowable<ClassifyBean> getClassify();

    @GET("catalog/current")
    Flowable<ClassifyRlvBean> getClassifyResult(@Query("id") int id);
}
