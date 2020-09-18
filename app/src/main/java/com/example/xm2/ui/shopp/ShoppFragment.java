package com.example.xm2.ui.shopp;

import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xm2.R;
import com.example.xm2.base.BaseFragment;
import com.example.xm2.bean.ShoppAddBean;
import com.example.xm2.bean.ShoppBean;
import com.example.xm2.bean.ShoppDeleteBean;
import com.example.xm2.interfaces.shopp.IShopp;
import com.example.xm2.presenter.shopp.ShoppPresenter;
import com.example.xm2.ui.shopp.adapter.ShoppRlvAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

//购物车
public class ShoppFragment extends BaseFragment<IShopp.Presenter> implements IShopp.View {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rlv_shopp)
    RecyclerView rlvShopp;
    @BindView(R.id.checkbox_select)
    CheckBox checkboxSelect;
    @BindView(R.id.tv_quanxuan)
    TextView tvQuanxuan;
    @BindView(R.id.ll_cb)
    LinearLayout llCb;
    @BindView(R.id.txt_allPrice)
    TextView txtAllPrice;
    @BindView(R.id.txt_edit)
    TextView txtEdit;
    @BindView(R.id.txt_submit)
    TextView txtSubmit;
    private ShoppRlvAdapter shoppRlvAdapter;
    private ArrayList<ShoppBean.DataBean.CartListBean> listbeans;

    @Override
    protected IShopp.Presenter initPresenter() {
        return new ShoppPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_shopp;
    }

    @Override
    protected void initView() {
        setRlv();
    }

    private void setRlv() {
        listbeans = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rlvShopp.setLayoutManager(linearLayoutManager);
        rlvShopp.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        shoppRlvAdapter = new ShoppRlvAdapter(getActivity(), listbeans);
        rlvShopp.setAdapter(shoppRlvAdapter);
    }

    @Override
    protected void initData() {
        mPresenter.getShopp();
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

    @Override
    public void getShoppResult(ShoppBean result) {
        if (result.getData().getCartList() != null) {
            List<ShoppBean.DataBean.CartListBean> cartList = result.getData().getCartList();
            listbeans.addAll(cartList);
            shoppRlvAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getShoppAdd(ShoppAddBean result) {

    }

    @Override
    public void getShoppDelete(ShoppDeleteBean result) {

    }
}
