package com.example.xm2.ui.classify.fragment;

import com.example.xm2.R;
import com.example.xm2.base.BaseFragment;
import com.example.xm2.bean.ClassifyBean;
import com.example.xm2.interfaces.classify.IClassify;
import com.example.xm2.presenter.classify.ClassifyPresenter;

public class ProjectFragment extends BaseFragment<IClassify.Presenter> implements IClassify.View {
    @Override
    protected IClassify.Presenter initPresenter() {
        return new ClassifyPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_project;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void getClassifyResult(ClassifyBean result) {

    }

    @Override
    public void showTips(String tips) {

    }

    @Override
    public void showLoading(int visble) {

    }
}
