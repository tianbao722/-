package com.example.xm2.presenter.home;


import com.example.xm2.base.BasePresenter;
import com.example.xm2.bean.HomeBean;
import com.example.xm2.common.CommonSubScriBer;
import com.example.xm2.interfaces.home.IHome;
import com.example.xm2.model.HttpManager;
import com.example.xm2.utiles.RxUtils;

public class HomePresenter extends BasePresenter<IHome.RecommendView> implements IHome.RecommendPersenter {
    @Override
    public void getHome() {
        addSubscribe(
                HttpManager
                        .getInstance()
                        .getHomeApi()
                        .getHome()
                        .compose(RxUtils.<HomeBean>rxScheduler())
                        .subscribeWith(new CommonSubScriBer<HomeBean>(mView) {
                            @Override
                            public void onNext(HomeBean homeBean) {
                                mView.getHomeResult(homeBean);
                            }
                        })
        );
    }
}