package com.example.xm2.interfaces.classify;


import com.example.xm2.bean.ClassifyBean;
import com.example.xm2.interfaces.IBasePresenter;
import com.example.xm2.interfaces.IBaseView;
import com.example.xm2.model.classifyapi.ClassifyApi;

public interface IClassify {
    interface View extends IBaseView {
        //获取分类数据
        void getClassifyResult(ClassifyBean result);
    }

    interface Presenter extends IBasePresenter<View> {
        //请求分类数据
        void getClassify();
    }
}
