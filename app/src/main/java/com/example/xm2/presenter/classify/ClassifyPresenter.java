package com.example.xm2.presenter.classify;


import com.example.xm2.base.BasePresenter;
import com.example.xm2.bean.ClassifyBean;
import com.example.xm2.common.CommonSubScriBer;
import com.example.xm2.interfaces.classify.IClassify;
import com.example.xm2.model.HttpManager;
import com.example.xm2.utiles.RxUtils;

public class ClassifyPresenter extends BasePresenter<IClassify.View> implements IClassify.Presenter {
    @Override
    public void getClassify() {
        addSubscribe(
                HttpManager
                        .getInstance()
                        .getClassifyApi()
                        .getClassify()
                        .compose(RxUtils.<ClassifyBean>rxScheduler())
                        .subscribeWith(new CommonSubScriBer<ClassifyBean>(mView) {
                            @Override
                            public void onNext(ClassifyBean classifyBean) {
                                mView.getClassifyResult(classifyBean);
                            }
                        })
        );
    }
}
