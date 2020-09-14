package com.example.xm2.interfaces.classify;


import com.example.xm2.interfaces.IBasePresenter;
import com.example.xm2.interfaces.IBaseView;

public interface IClassify {
    interface View extends IBaseView {

    }

    interface Presenter extends IBasePresenter<View> {

    }
}
