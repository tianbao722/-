package com.example.xm2.common;

import android.text.TextUtils;

import com.example.xm2.interfaces.IBaseView;

import io.reactivex.subscribers.ResourceSubscriber;

public abstract class CommonSubScriBer<T> extends ResourceSubscriber<T> {
    private IBaseView mView;
    private String errormsg;
    private boolean isShowErrorState = false;

    public CommonSubScriBer(IBaseView mView) {
        this.mView = mView;
    }

    public CommonSubScriBer(IBaseView mView, String errormsg) {
        this.mView = mView;
        this.errormsg = errormsg;
    }

    public CommonSubScriBer(IBaseView mView, boolean isShowErrorState) {
        this.mView = mView;
        this.isShowErrorState = isShowErrorState;
    }

    public CommonSubScriBer(IBaseView mView, String errormsg, boolean isShowErrorState) {
        this.mView = mView;
        this.errormsg = errormsg;
        this.isShowErrorState = isShowErrorState;
    }

    @Override
    public void onError(Throwable t) {
        //LoadingUtil.getInstance().hideLoading();
        if (mView == null)return;
        if (errormsg != null && TextUtils.isEmpty(errormsg)){
            mView.showTips(errormsg);
        }
    }
    @Override
    public void onComplete() {
        //LoadingUtil.getInstance().hideLoading();
        //mView.showLoading(View.GONE);
    }
}
