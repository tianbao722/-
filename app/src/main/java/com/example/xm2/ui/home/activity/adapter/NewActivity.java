package com.example.xm2.ui.home.activity.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.xm2.R;
import com.example.xm2.base.BaseActivity;
import com.example.xm2.base.BaseAdapter;
import com.example.xm2.bean.HomeBean;
import com.example.xm2.bean.HomeGoodDetailBean;
import com.example.xm2.bean.HomeNewBean;
import com.example.xm2.bean.HomeNewTopBean;
import com.example.xm2.bean.MyadressBean;
import com.example.xm2.bean.ShoppAddBean;
import com.example.xm2.bean.SpecialBean;
import com.example.xm2.bean.UserBean;
import com.example.xm2.interfaces.home.IHome;
import com.example.xm2.presenter.home.HomePresenter;
import com.example.xm2.utiles.SystemUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewActivity extends BaseActivity<IHome.RecommendPersenter> implements IHome.RecommendView {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_img_top)
    ImageView ivImgTop;
    @BindView(R.id.tv_title_top)
    TextView tvTitleTop;
    @BindView(R.id.rl_image)
    RelativeLayout rlImage;
    @BindView(R.id.tv_zonghe)
    TextView tvZonghe;
    @BindView(R.id.tv_jiage)
    TextView tvJiage;
    @BindView(R.id.iv_shang)
    ImageView ivShang;
    @BindView(R.id.iv_xia)
    ImageView ivXia;
    @BindView(R.id.tv_fenlei)
    TextView tvFenlei;
    @BindView(R.id.ll_gaohang)
    LinearLayout llGaohang;
    @BindView(R.id.v)
    View v;
    @BindView(R.id.rlv_new)
    RecyclerView rlvNew;
    private boolean jiage = false;//价格是否是从高到低
    private ArrayList<HomeNewBean.DataBeanX.DataBean> lsit;
    private NewRlvAdapter newRlvAdapter;
    private ArrayList<HomeNewBean.DataBeanX.FilterCategoryBean> fenlei;
    private RlvFenLeiAdapter rlvFenLeiAdapter;
    private View inflate;
    private PopupWindow pw;

    @Override
    protected IHome.RecommendPersenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_new;
    }

    @Override
    protected void initView() {
        lsit = new ArrayList<>();
        fenlei = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rlvNew.setLayoutManager(gridLayoutManager);
        rlvNew.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        rlvNew.addItemDecoration(new DividerItemDecoration(this, LinearLayout.HORIZONTAL));
        newRlvAdapter = new NewRlvAdapter(this, lsit);
        rlvNew.setAdapter(newRlvAdapter);

    }

    @Override
    protected void initData() {
        mPresenter.getNewTop();
        HashMap<String, String> map = new HashMap<>();
        map.put("isNew", "1");
        map.put("page", "1");
        map.put("size", "1000");
        map.put("order", "asc");
        map.put("sort", "default");
        map.put("categoryId", "0");
        mPresenter.getNew(map);
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
        if (result.getData() != null) {
            HomeNewTopBean.DataBean.BannerInfoBean bannerInfo = result.getData().getBannerInfo();
            String img_url = bannerInfo.getImg_url();
            String name = bannerInfo.getName();
            tvTitleTop.setText(name);
            Glide.with(NewActivity.this).load(img_url).into(ivImgTop);
        }
    }

    @Override
    public void getHomeNewResult(HomeNewBean result) {
        if (result.getData() != null) {
            List<HomeNewBean.DataBeanX.DataBean> data = result.getData().getData();
            lsit.addAll(data);
            newRlvAdapter.notifyDataSetChanged();
            fenlei.clear();
            if (pw != null && pw.isShowing()){
                List<HomeNewBean.DataBeanX.FilterCategoryBean> filterCategory = result.getData().getFilterCategory();
                fenlei.addAll(filterCategory);
                rlvFenLeiAdapter.notifyDataSetChanged();
            }
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

    @SuppressLint("ResourceAsColor")
    @OnClick({R.id.tv_zonghe, R.id.tv_jiage, R.id.tv_fenlei})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_zonghe:
                setZhongHeColor();
                lsit.clear();
                HashMap<String, String> map = new HashMap<>();
                map.put("isNew", "1");
                map.put("page", "1");
                map.put("size", "1000");
                map.put("order", "asc");
                map.put("sort", "default");
                map.put("categoryId", "0");
                mPresenter.getNew(map);
                break;
            case R.id.tv_jiage:
                if (jiage) {//价格高到低
                    setJiaGeShang();
                    lsit.clear();
                    HashMap<String, String> map1 = new HashMap<>();
                    map1.put("isNew", "1");
                    map1.put("page", "1");
                    map1.put("size", "1000");
                    map1.put("order", "asc");
                    map1.put("sort", "default");
                    map1.put("categoryId", "0");
                    mPresenter.getNew(map1);
                } else {//价格低到高
                    setJiaGeXia();
                    HashMap<String, String> map2 = new HashMap<>();
                    map2.put("isNew", "1");
                    map2.put("page", "1");
                    map2.put("size", "1000");
                    map2.put("order", "desc");
                    map2.put("sort", "default");
                    map2.put("categoryId", "0");
                    mPresenter.getNew(map2);
                }
                break;
            case R.id.tv_fenlei:
                setFenLei();
                HashMap<String, String> map2 = new HashMap<>();
                map2.put("isNew", "1");
                map2.put("page", "1");
                map2.put("size", "1000");
                map2.put("order", "desc");
                map2.put("sort", "default");
                map2.put("categoryId", "0");
                mPresenter.getNew(map2);
                inflate = LayoutInflater.from(NewActivity.this).inflate(R.layout.layout_new_popupwoindow, null);
                RecyclerView rlv = inflate.findViewById(R.id.rlv_new_fenli);
                GridLayoutManager gridLayoutManager1 = new GridLayoutManager(this, 5) {
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                };
                rlvFenLeiAdapter = new RlvFenLeiAdapter(this, fenlei);
                rlv.setLayoutManager(gridLayoutManager1);
                rlv.setAdapter(rlvFenLeiAdapter);
                DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                int width = displayMetrics.widthPixels;
                int i = SystemUtils.dp2px(NewActivity.this, 110);
                pw = new PopupWindow(inflate, width, i);
                pw.setOutsideTouchable(true);
                pw.setFocusable(true);
                pw.showAsDropDown(llGaohang, 0, 0);

                rlvFenLeiAdapter.setOnClick(new BaseAdapter.onClick() {
                    @Override
                    public void click(int pos) {
                        HashMap<String, String> map2 = new HashMap<>();
                        map2.put("isNew", "1");
                        map2.put("page", "1");
                        map2.put("size", "1000");
                        map2.put("order", "desc");
                        map2.put("sort", "default");
                        map2.put("categoryId", pos + "1");
                        mPresenter.getNew(map2);
                        pw.dismiss();
                    }
                });
                break;
        }
    }

    //分类的颜色选择器
    private void setFenLei() {
        tvZonghe.setTextColor(Color.rgb(101, 101, 101));
        tvJiage.setTextColor(Color.rgb(101, 101, 101));
        ivShang.setColorFilter(Color.rgb(101, 101, 101));
        ivXia.setColorFilter(Color.rgb(101, 101, 101));
        tvFenlei.setTextColor(Color.rgb(180, 40, 45));
    }

    //价格下的颜色选择器
    private void setJiaGeXia() {
        tvZonghe.setTextColor(Color.rgb(101, 101, 101));
        tvJiage.setTextColor(Color.rgb(180, 40, 45));
        ivShang.setColorFilter(Color.rgb(101, 101, 101));
        ivXia.setColorFilter(Color.rgb(180, 40, 45));
        tvFenlei.setTextColor(Color.rgb(101, 101, 101));
        jiage = true;
    }

    //价格上的颜色选择器
    private void setJiaGeShang() {
        tvZonghe.setTextColor(Color.rgb(101, 101, 101));
        tvJiage.setTextColor(Color.rgb(180, 40, 45));
        ivShang.setColorFilter(Color.rgb(180, 40, 45));
        ivXia.setColorFilter(Color.rgb(101, 101, 101));
        tvFenlei.setTextColor(Color.rgb(101, 101, 101));
        jiage = false;
    }

    //综合的颜色选择器
    private void setZhongHeColor() {
        tvZonghe.setTextColor(Color.rgb(180, 40, 45));
        tvJiage.setTextColor(Color.rgb(101, 101, 101));
        ivShang.setColorFilter(Color.rgb(101, 101, 101));
        ivXia.setColorFilter(Color.rgb(101, 101, 101));
        tvFenlei.setTextColor(Color.rgb(101, 101, 101));
    }
}