package com.example.xm2.ui.special;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xm2.R;
import com.example.xm2.base.BaseFragment;
import com.example.xm2.bean.HomeBean;
import com.example.xm2.bean.HomeGoodDetailBean;
import com.example.xm2.bean.ShoppAddBean;
import com.example.xm2.bean.SpecialBean;
import com.example.xm2.bean.UserBean;
import com.example.xm2.interfaces.home.IHome;
import com.example.xm2.presenter.home.HomePresenter;
import com.example.xm2.ui.special.adapter.SpecialAdapter;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

//专题
public class SpecialFragment extends BaseFragment<IHome.RecommendPersenter> implements IHome.RecommendView {

    @BindView(R.id.rlv_special)
    RecyclerView rlvSpecial;
    @BindView(R.id.btn_shang)
    Button btnShang;
    @BindView(R.id.btn_xia)
    Button btnXia;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private int page = 1;
    private int size = 10;
    private ArrayList<SpecialBean.DataBeanX.DataBean> list;
    private SpecialAdapter specialAdapter;

    @Override
    protected IHome.RecommendPersenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_special;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void initView() {
        btnShang.setTextColor(R.color.hui);
        ivBack.setVisibility(View.GONE);
        list = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rlvSpecial.setLayoutManager(linearLayoutManager);
        specialAdapter = new SpecialAdapter(getActivity(), list);
        rlvSpecial.setAdapter(specialAdapter);
    }

    @Override
    protected void initData() {
        mPresenter.getSpecial(page, size);
    }

    @Override
    protected void initListener() {
        btnShang.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                if (page > 1) {
                    page--;
                    btnShang.setTextColor(R.color.hui);
                    btnXia.setTextColor(Color.BLACK);
                    list.clear();
                    mPresenter.getSpecial(page, size);
                    specialAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getActivity(), "已是第一页", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnXia.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                if (page < 2){
                    page++;
                    list.clear();
                    btnShang.setTextColor(Color.BLACK);
                    btnXia.setTextColor(R.color.hui);
                    mPresenter.getSpecial(page,size);
                    specialAdapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(getActivity(), "已是最后一页", Toast.LENGTH_SHORT).show();
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
        if (result.getData().getData() != null) {
            List<SpecialBean.DataBeanX.DataBean> data = result.getData().getData();
            list.addAll(data);
            specialAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void addCartInfoReturn(ShoppAddBean result) {

    }
}
