package com.example.xm2.ui.my;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.xm2.R;
import com.example.xm2.base.BaseFragment;
import com.example.xm2.interfaces.IBasePresenter;
import com.example.xm2.ui.my.activity.LoginActivity;

import butterknife.BindView;

//我的
public class MyFragment extends BaseFragment {
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_denglu)
    TextView tvDenglu;
    @BindView(R.id.iv_jinru)
    ImageView ivJinru;
    @BindView(R.id.cl_my)
    ConstraintLayout clMy;
    @BindView(R.id.ll_dingdan)
    LinearLayout llDingdan;
    @BindView(R.id.ll_youhuiquan)
    LinearLayout llYouhuiquan;
    @BindView(R.id.ll_lipinka)
    LinearLayout llLipinka;
    @BindView(R.id.ll_shoucang)
    LinearLayout llShoucang;
    @BindView(R.id.ll_zuji)
    LinearLayout llZuji;
    @BindView(R.id.ll_huiyuan)
    LinearLayout llHuiyuan;
    @BindView(R.id.ll_dizhi)
    LinearLayout llDizhi;
    @BindView(R.id.ll_zhanghao)
    LinearLayout llZhanghao;
    @BindView(R.id.ll_kefu)
    LinearLayout llKefu;
    @BindView(R.id.ll_bangzhu)
    LinearLayout llBangzhu;
    @BindView(R.id.ll_yijian)
    LinearLayout llYijian;

    @Override
    protected IBasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        tvDenglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
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
}