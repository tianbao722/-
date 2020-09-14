package com.example.xm2.interfaces.my;


import com.example.xm2.interfaces.IBasePresenter;
import com.example.xm2.interfaces.IBaseView;

public interface IMy {
    interface View extends IBaseView {

    }

    interface Presenter extends IBasePresenter<View> {

    }
}
