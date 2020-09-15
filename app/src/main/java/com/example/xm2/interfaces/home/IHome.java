package com.example.xm2.interfaces.home;


import com.example.xm2.bean.HomeBean;
import com.example.xm2.bean.HomeGoodDetailBean;
import com.example.xm2.interfaces.IBasePresenter;
import com.example.xm2.interfaces.IBaseView;

import java.util.List;

public interface IHome {
    interface RecommendView extends IBaseView {
        //首页数据
        void getHomeResult(List<HomeBean.HomeListBean> result);

        //商品购买详情
        void getGoodDetailResult(HomeGoodDetailBean result);
    }

    interface RecommendPersenter extends IBasePresenter<RecommendView> {
        //请求首页数据
        void getHome();

        //请求商品购买详情数据
        void getGoodDetail(int id);
    }

}
