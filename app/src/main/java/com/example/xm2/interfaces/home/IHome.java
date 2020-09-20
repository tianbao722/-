package com.example.xm2.interfaces.home;


import com.example.xm2.bean.HomeBean;
import com.example.xm2.bean.HomeGoodDetailBean;
import com.example.xm2.bean.MyadressBean;
import com.example.xm2.bean.ShoppAddBean;
import com.example.xm2.bean.SpecialBean;
import com.example.xm2.bean.UserBean;
import com.example.xm2.interfaces.IBasePresenter;
import com.example.xm2.interfaces.IBaseView;

import java.util.HashMap;
import java.util.List;

public interface IHome {
    interface RecommendView extends IBaseView {
        //首页数据
        void getHomeResult(List<HomeBean.HomeListBean> result);

        //商品购买详情
        void getGoodDetailResult(HomeGoodDetailBean result);

        //登录
        void getLoginResult(UserBean result);

        //注册
        void getZhuCeResult(UserBean result);

        //专题
        void getSpecialResult(SpecialBean result);

        //添加商品信息返回
        void addCartInfoReturn(ShoppAddBean result);

        //加载地址信息
        void getAdressReturn(MyadressBean result);

    }

    interface RecommendPersenter extends IBasePresenter<RecommendView> {
        //请求首页数据
        void getHome();

        //请求商品购买详情数据
        void getGoodDetail(int id);

        //请求登录
        void getLogin(HashMap<String, String> map);

        //请求注册
        void getZhuce(HashMap<String, String> map);

        //请求专题数据
        void getSpecial(int page, int size);

        //添加到购物车
        void addCart(int goodsId, int number, int productId);

        //请求省份数据
        void getAdress(int parentId);
    }

}
