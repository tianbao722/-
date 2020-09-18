package com.example.xm2.interfaces.shopp;


import com.example.xm2.bean.ShoppAddBean;
import com.example.xm2.bean.ShoppBean;
import com.example.xm2.bean.ShoppDeleteBean;
import com.example.xm2.interfaces.IBasePresenter;
import com.example.xm2.interfaces.IBaseView;

import java.util.HashMap;

public interface IShopp {
    interface View extends IBaseView {
        //获取购物车数据
        void getShoppResult(ShoppBean result);

        //删除购物车中的某条数据
        void getShoppDelete(ShoppDeleteBean result);
    }

    interface Presenter extends IBasePresenter<View> {
        //请求购物车数据
        void getShopp();

        //请求删除购物车中的某条数据
        void getShoppDelete(String token,String productIds);

    }
}
