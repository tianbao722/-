package com.example.xm2.ui.my.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

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
import com.example.xm2.utiles.SpUtils;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity<IHome.RecommendPersenter> implements IHome.RecommendView {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.ll_login)
    LinearLayout llLogin;
    @BindView(R.id.tv_zhuce)
    TextView tvZhuce;
    @BindView(R.id.imv_pwd)
    ImageView imvPwd;


    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        tvTitle.setText("登录");
        tvTitle.setTextColor(Color.WHITE);
        etName.getBackground().setAlpha(140);
        etPwd.getBackground().setAlpha(140);
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
        //登录判断
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String pwd = etPwd.getText().toString();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pwd)) {
                    if (pwd.length() >= 6) {
                        HashMap<String, String> login = new HashMap<>();
                        login.put("username", name);
                        login.put("password", pwd);
                        mPresenter.getLogin(login);
                    } else {
                        Toast.makeText(LoginActivity.this, "密码小于六位", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //跳转到注册
        tvZhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ZhuCeActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        //是否显示密码
        imvPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etPwd.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {//不可见
                    imvPwd.setImageResource(R.mipmap.yanjingbi);
                    etPwd.setKeyListener(DigitsKeyListener.getInstance("1234567890"));
                    etPwd.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                } else {
                    etPwd.setKeyListener(DigitsKeyListener.getInstance("1234567890"));
                    imvPwd.setImageResource(R.mipmap.yanjingkai);
                    etPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
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
    protected IHome.RecommendPersenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 2) {
            String name = data.getStringExtra("name");
            String pwd = data.getStringExtra("pwd");
            etName.setText(name);
            etPwd.setText(pwd);
        }
    }

    @Override
    public void getHomeResult(List<HomeBean.HomeListBean> result) {

    }

    @Override
    public void getGoodDetailResult(HomeGoodDetailBean result) {

    }

    @Override
    public void getLoginResult(UserBean result) {
        if (result.getData().getCode() == 200) {
            SpUtils.getInstance().setValue("token", result.getData().getToken());
            SpUtils.getInstance().setValue("username", result.getData().getUserInfo().getUsername());
            SpUtils.getInstance().setValue("birthday", result.getData().getUserInfo().getBirthday());
            SpUtils.getInstance().setValue("avatar", result.getData().getUserInfo().getAvatar());
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, result.getData().getMsg(), Toast.LENGTH_SHORT).show();
        }
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

    }
}