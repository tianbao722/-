package com.example.xm2.ui.my.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xm2.R;
import com.example.xm2.base.BaseActivity;
import com.example.xm2.bean.HomeBean;
import com.example.xm2.bean.HomeGoodDetailBean;
import com.example.xm2.bean.MyadressBean;
import com.example.xm2.bean.ShoppAddBean;
import com.example.xm2.bean.SpecialBean;
import com.example.xm2.bean.UserBean;
import com.example.xm2.interfaces.IBasePresenter;
import com.example.xm2.interfaces.home.IHome;
import com.example.xm2.presenter.home.HomePresenter;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZhuCeActivity extends BaseActivity<IHome.RecommendPersenter> implements IHome.RecommendView {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.et_pwd1)
    EditText etPwd1;
    @BindView(R.id.btn_zhuce)
    Button btnZhuce;
    @BindView(R.id.ll_login)
    LinearLayout llLogin;
    private String pwd;

    @Override
    protected IHome.RecommendPersenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_zhu_ce;
    }

    @Override
    protected void initView() {
        tvTitle.setText("注册");
        tvTitle.setTextColor(Color.WHITE);
        etName.getBackground().setAlpha(140);
        etPwd.getBackground().setAlpha(140);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        btnZhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                pwd = etPwd.getText().toString();
                String pwd1 = etPwd1.getText().toString();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pwd) && !TextUtils.isEmpty(pwd1)) {
                    if (pwd.equals(pwd1)) {
                        if (pwd.length() >= 6) {
                            HashMap<String, String> map = new HashMap<>();
                            map.put("username", name);
                            map.put("password", pwd);
                            mPresenter.getZhuce(map);
                        } else {
                            Toast.makeText(ZhuCeActivity.this, "密码长度小于六位", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ZhuCeActivity.this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ZhuCeActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
                }

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
        if (result.getData() != null) {
            Intent intent = getIntent();
            intent.putExtra("name",result.getData().getUserInfo().getUsername());
            intent.putExtra("pwd",pwd);
            setResult(2,intent);
            finish();
            Toast.makeText(this, "注册成功,前往登录界面", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, result.getErrmsg(), Toast.LENGTH_SHORT).show();
        }
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
}