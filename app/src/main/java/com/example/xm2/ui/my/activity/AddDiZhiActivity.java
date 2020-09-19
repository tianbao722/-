package com.example.xm2.ui.my.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xm2.R;
import com.example.xm2.base.BaseActivity;
import com.example.xm2.interfaces.IBasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddDiZhiActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.cb_dizhi)
    CheckBox cbDizhi;
    @BindView(R.id.btn_finish_dizhi)
    Button btnFinishDizhi;
    @BindView(R.id.btn_baocun_dizhi)
    Button btnBaocunDizhi;
    @BindView(R.id.et_name_dizhi)
    EditText etNameDizhi;
    @BindView(R.id.et_phone_num)
    EditText etPhoneNum;
    @BindView(R.id.et_dizhi)
    EditText etDizhi;
    @BindView(R.id.et_xiangxidizhi)
    EditText etXiangxidizhi;

    @Override
    protected IBasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_add_di_zhi;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        //返回
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //返回
        btnFinishDizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnBaocunDizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //保存地址
            }
        });
        etDizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
}