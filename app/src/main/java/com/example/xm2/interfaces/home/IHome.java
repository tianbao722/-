package com.example.xm2.interfaces.home;


import com.example.xm2.bean.HomeBean;
import com.example.xm2.interfaces.IBasePresenter;
import com.example.xm2.interfaces.IBaseView;

public interface IHome {
    interface RecommendView extends IBaseView {
        //首页数据
        void getHomeResult(HomeBean result);
    }

    interface RecommendPersenter extends IBasePresenter<RecommendView> {
        //请求首页数据
        void getHome();
    }

}
