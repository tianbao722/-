package com.example.xm2.model.classifyapi;

import com.example.xm2.bean.ClassifyBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface ClassifyApi {
    @GET("catalog/index")
    Flowable<ClassifyBean> getClassify();

}
