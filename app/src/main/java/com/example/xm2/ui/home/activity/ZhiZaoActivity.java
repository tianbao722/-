package com.example.xm2.ui.home.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xm2.R;
import com.example.xm2.base.BaseActivity;
import com.example.xm2.bean.HomeBean;
import com.example.xm2.bean.HomeGoodDetailBean;
import com.example.xm2.bean.HomeNewBean;
import com.example.xm2.bean.HomeNewTopBean;
import com.example.xm2.bean.HomeZhiZhaoBean;
import com.example.xm2.bean.MyadressBean;
import com.example.xm2.bean.ShoppAddBean;
import com.example.xm2.bean.SpecialBean;
import com.example.xm2.bean.UserBean;
import com.example.xm2.interfaces.home.IHome;
import com.example.xm2.presenter.home.HomePresenter;
import com.example.xm2.ui.home.activity.adapter.ZhiZaoRlvAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZhiZaoActivity extends BaseActivity<IHome.RecommendPersenter> implements IHome.RecommendView {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rlv_zhuanti)
    RecyclerView rlvZhuanti;
    private ArrayList<HomeZhiZhaoBean.DataBeanX.DataBean> list;
    private ZhiZaoRlvAdapter zhiZaoRlvAdapter;

    @Override
    protected IHome.RecommendPersenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_zhuan_ti;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rlvZhuanti.setLayoutManager(linearLayoutManager);
        zhiZaoRlvAdapter = new ZhiZaoRlvAdapter(this, list);
        rlvZhuanti.setAdapter(zhiZaoRlvAdapter);
    }

    @Override
    protected void initData() {
        mPresenter.getZhiZao(1, 1000);
    }

    @Override
    protected void initListener() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void getHomeResult(List<HomeBean.HomeListBean> result) {

    }

    @Override
    public void getGoodDetailResult(HomeGoodDetailBean result) {

    }

    @Override
    public void getLoginResult(UserBean result) {

    }

    @Override
    public void getZhuCeResult(UserBean result) {

    }

    @Override
    public void getSpecialResult(SpecialBean result) {

    }

    @Override
    public void addCartInfoReturn(ShoppAddBean result) {

    }

    @Override
    public void getAdressReturn(MyadressBean result) {

    }

    @Override
    public void getHomeNewTopResult(HomeNewTopBean result) {

    }

    @Override
    public void getHomeNewResult(HomeNewBean result) {

    }

    @Override
    public void getHomeZhiZaoResult(HomeZhiZhaoBean result) {
        if (result.getData() != null) {
            List<HomeZhiZhaoBean.DataBeanX.DataBean> data = result.getData().getData();
            list.addAll(data);
            zhiZaoRlvAdapter.notifyDataSetChanged();
        }
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
}