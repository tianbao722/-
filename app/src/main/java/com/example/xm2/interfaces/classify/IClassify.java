package com.example.xm2.interfaces.classify;


import com.example.xm2.bean.ClassifyBean;
import com.example.xm2.bean.ClassifyRlvBean;
import com.example.xm2.interfaces.IBasePresenter;
import com.example.xm2.interfaces.IBaseView;
import com.example.xm2.model.classifyapi.ClassifyApi;

public interface IClassify {
    interface View extends IBaseView {
        //获取分类数据
        void getClassifyResult(ClassifyBean result);

        //获取分类列表数据
        void getClassifyRlvResult(ClassifyRlvBean result);
    }

    interface Presenter extends IBasePresenter<View> {
        //请求分类数据
        void getClassify();

        //请求分类列表数据
        void getClassifyRlv(int id);
    }
}
