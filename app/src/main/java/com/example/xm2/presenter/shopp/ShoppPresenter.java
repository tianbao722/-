package com.example.xm2.presenter.shopp;

import com.example.xm2.base.BasePresenter;
import com.example.xm2.bean.ShoppAddBean;
import com.example.xm2.bean.ShoppBean;
import com.example.xm2.bean.ShoppDeleteBean;
import com.example.xm2.common.CommonSubScriBer;
import com.example.xm2.interfaces.shopp.IShopp;
import com.example.xm2.model.HttpManager;
import com.example.xm2.utiles.RxUtils;

import java.util.HashMap;

public class ShoppPresenter extends BasePresenter<IShopp.View> implements IShopp.Presenter {
    @Override
    public void getShopp() {
        addSubscribe(
                HttpManager
                        .getInstance()
                        .getShoppApi()
                        .getShopp()
                        .compose(RxUtils.<ShoppBean>rxScheduler())
                        .subscribeWith(new CommonSubScriBer<ShoppBean>(mView) {
                            @Override
                            public void onNext(ShoppBean shoppBean) {
                                mView.getShoppResult(shoppBean);
                            }
                        })
        );
    }

    @Override
    public void getShoppDelete(String productIds) {
        addSubscribe(
                HttpManager
                        .getInstance()
                        .getShoppApi()
                        .getShoppDelete(productIds)
                        .compose(RxUtils.<ShoppDeleteBean>rxScheduler())
                        .subscribeWith(new CommonSubScriBer<ShoppDeleteBean>(mView) {
                            @Override
                            public void onNext(ShoppDeleteBean shoppDeleteBean) {
                                mView.getShoppDelete(shoppDeleteBean);
                            }
                        })
        );
    }
}
