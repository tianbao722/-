package com.example.xm2.presenter.classify;


import com.example.xm2.base.BasePresenter;
import com.example.xm2.bean.ClassifyBean;
import com.example.xm2.bean.ClassifyRlvBean;
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

    @Override
    public void getClassifyRlv(int id) {
        addSubscribe(
                HttpManager
                        .getInstance()
                        .getClassifyApi()
                        .getClassifyResult(id)
                        .compose(RxUtils.<ClassifyRlvBean>rxScheduler())
                        .subscribeWith(new CommonSubScriBer<ClassifyRlvBean>(mView) {
                            @Override
                            public void onNext(ClassifyRlvBean classifyRlvBean) {
                                mView.getClassifyRlvResult(classifyRlvBean);
                            }
                        })
        );
    }
}
