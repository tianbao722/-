package com.example.xm2.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.xm2.interfaces.IBasePresenter;
import com.example.xm2.interfaces.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity<P extends IBasePresenter> extends AppCompatActivity implements IBaseView {
    public P mPresenter;
    Unbinder unbinder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设计模式中的模板方法调用
        setContentView(getLayout());
        unbinder = ButterKnife.bind(this);
        initView();
        mPresenter = initPresenter();
        if (mPresenter != null){
            mPresenter.attachView(this);
            initData();
        }
        initListener();
    }

    protected abstract P initPresenter();

    protected abstract int getLayout();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null){
            unbinder.unbind();
        }
        if (mPresenter != null){
            mPresenter.detachView();
        }
    }
}
