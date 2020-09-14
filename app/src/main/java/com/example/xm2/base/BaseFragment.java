package com.example.xm2.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.xm2.interfaces.IBasePresenter;
import com.example.xm2.interfaces.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends IBasePresenter> extends Fragment implements IBaseView {
    public P mPresenter;
    Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(getLayout(),null);
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this,view);
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
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null){
            unbinder.unbind();
        }
        if (mPresenter != null){
            mPresenter.detachView();
        }
    }
}
