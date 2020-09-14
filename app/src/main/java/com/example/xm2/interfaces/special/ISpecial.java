package com.example.xm2.interfaces.special;


import com.example.xm2.interfaces.IBasePresenter;
import com.example.xm2.interfaces.IBaseView;

public interface ISpecial {
    interface View extends IBaseView {

    }

    interface Presenter extends IBasePresenter<View> {

    }
}
