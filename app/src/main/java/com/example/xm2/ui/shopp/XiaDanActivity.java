package com.example.xm2.ui.shopp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.xm2.R;
import com.example.xm2.base.BaseActivity;
import com.example.xm2.bean.ShoppBean;
import com.example.xm2.bean.ShoppDeleteBean;
import com.example.xm2.bean.ShoppDiZhiBean;
import com.example.xm2.bean.ShoppXiaDanBean;
import com.example.xm2.interfaces.shopp.IShopp;
import com.example.xm2.presenter.shopp.ShoppPresenter;
import com.example.xm2.ui.my.activity.DiZhiActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class XiaDanActivity extends BaseActivity<IShopp.Presenter> implements IShopp.View {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.V_xiadan)
    View VXiadan;
    @BindView(R.id.tv_youhuiquan)
    TextView tvYouhuiquan;
    @BindView(R.id.rl_youhuiquan)
    RelativeLayout rlYouhuiquan;
    @BindView(R.id.tv_heji)
    TextView tvHeji;
    @BindView(R.id.tv_yunfei)
    TextView tvYunfei;
    @BindView(R.id.tv_youhuiquan_num)
    TextView tvYouhuiquanNum;
    @BindView(R.id.ll_liebiao)
    LinearLayout llLiebiao;
    @BindView(R.id.tv_shifu)
    TextView tvShifu;
    @BindView(R.id.btn_fukuan)
    Button btnFukuan;
    @BindView(R.id.tv_moren)
    TextView tvMoren;
    @BindView(R.id.tv_xuexiao)
    TextView tvXuexiao;
    @BindView(R.id.tv_dizhi)
    TextView tvDizhi;
    @BindView(R.id.tv_xiangxidizhi)
    TextView tvXiangxidizhi;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.cl_dingdan)
    ConstraintLayout clDingdan;

    @Override
    protected IShopp.Presenter initPresenter() {
        return new ShoppPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_xia_dan;
    }

    @Override
    protected void initView() {
        //处理电话号码中间四位*号显示
        String string = tvPhone.getText().toString();
        setPhone(string);
    }

    private void setPhone(String string) {

        String substring = string.substring(0, 3);
        String substring1 = string.substring(7, 11);
        tvPhone.setText(substring + "****" + substring1);
    }

    @Override
    protected void initData() {
        mPresenter.getShoppXiaDan(1, 1);
    }

    @Override
    protected void initListener() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        clDingdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(XiaDanActivity.this, DiZhiActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void getShoppResult(ShoppBean result) {

    }

    @Override
    public void getShoppDelete(ShoppDeleteBean result) {

    }

    @Override
    public void getShoppXiaDanResult(ShoppXiaDanBean result) {
        if (result.getData() != null){
            ShoppXiaDanBean.DataBean.CheckedAddressBean checkedAddress = result.getData().getCheckedAddress();
            tvDizhi.setText(checkedAddress.getFull_region());
            tvXiangxidizhi.setText(checkedAddress.getAddress());
            tvName.setText(checkedAddress.getName());
            String mobile = checkedAddress.getMobile();
            setPhone(mobile);
            tvPhone.setText(checkedAddress.getMobile());
        }
    }

    @Override
    public void getShoppDiZhiResult(ShoppDiZhiBean result) {

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