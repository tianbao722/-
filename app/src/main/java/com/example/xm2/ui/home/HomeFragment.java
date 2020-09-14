package com.example.xm2.ui.home;


import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.xm2.R;
import com.example.xm2.base.BaseFragment;
import com.example.xm2.interfaces.IBasePresenter;

import butterknife.BindView;

//首页
public class HomeFragment extends BaseFragment {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_sousuo)
    EditText etSousuo;
    @BindView(R.id.rlv_home)
    RecyclerView rlvHome;

    @Override
    protected IBasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
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
    public void showTips(String tips) {

    }

    @Override
    public void showLoading(int visble) {

    }
}