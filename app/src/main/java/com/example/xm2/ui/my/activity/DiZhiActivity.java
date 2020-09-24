package com.example.xm2.ui.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xm2.R;
import com.example.xm2.base.BaseActivity;
import com.example.xm2.bean.ShoppBean;
import com.example.xm2.bean.ShoppDeleteBean;
import com.example.xm2.bean.ShoppDiZhiBean;
import com.example.xm2.bean.ShoppXiaDanBean;
import com.example.xm2.interfaces.IBasePresenter;
import com.example.xm2.interfaces.shopp.IShopp;
import com.example.xm2.presenter.shopp.ShoppPresenter;
import com.example.xm2.ui.my.adapter.DiZhiAdapter;
import com.example.xm2.utiles.SpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiZhiActivity extends BaseActivity<IShopp.Presenter> implements IShopp.View {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rlv_dizhi)
    RecyclerView rlvDizhi;
    @BindView(R.id.btn_addDizhi)
    Button btnAddDizhi;
    private ArrayList<ShoppDiZhiBean.DataBean> list;
    private DiZhiAdapter diZhiAdapter;


    @Override
    protected int getLayout() {
        return R.layout.activity_di_zhi;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rlvDizhi.setLayoutManager(linearLayoutManager);
        diZhiAdapter = new DiZhiAdapter(this, list);
        rlvDizhi.setAdapter(diZhiAdapter);
    }

    @Override
    protected void initData() {
        String token = SpUtils.getInstance().getString("token");
        mPresenter.getShoppDiZhi(token);
    }

    @Override
    protected void initListener() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnAddDizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DiZhiActivity.this, AddDiZhiActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showTips(String tips) {

    }

    @Override
    public void showLoading(int visble) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected IShopp.Presenter initPresenter() {
        return new ShoppPresenter();
    }

    @Override
    public void getShoppResult(ShoppBean result) {

    }

    @Override
    public void getShoppDelete(ShoppDeleteBean result) {

    }

    @Override
    public void getShoppXiaDanResult(ShoppXiaDanBean result) {

    }

    @Override
    public void getShoppDiZhiResult(ShoppDiZhiBean result) {
        if (result.getData() != null) {
            List<ShoppDiZhiBean.DataBean> data = result.getData();
            list.addAll(data);
            diZhiAdapter.notifyDataSetChanged();
        }
    }
}