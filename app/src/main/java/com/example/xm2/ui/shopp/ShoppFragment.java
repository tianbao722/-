package com.example.xm2.ui.shopp;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import butterknife.OnClick;

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
    @BindView(R.id.txt_allPrice)
    TextView txtAllPrice;
    @BindView(R.id.txt_edit)
    TextView txtEdit;
    @BindView(R.id.txt_submit)
    TextView txtSubmit;
    private ShoppRlvAdapter shoppRlvAdapter;
    private ArrayList<ShoppBean.DataBean.CartListBean> listbeans;
    private int allNumber;
    private int allPrice;

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
        ivBack.setVisibility(View.GONE);
        tvQuanxuan.setText("全选(0)");
        txtAllPrice.setText("¥ 0");
        listbeans = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rlvShopp.setLayoutManager(linearLayoutManager);
        rlvShopp.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        shoppRlvAdapter = new ShoppRlvAdapter(getActivity(), listbeans);
        rlvShopp.setAdapter(shoppRlvAdapter);
        shoppRlvAdapter.setOnItemCheckBoxClickListener(new ShoppRlvAdapter.CheckBoxClick() {
            @Override
            public void checkChange() {
                //判断当前是否全选
                boolean bool = CheckSelectAll();
                tvQuanxuan.setText("全选(" + allNumber + ")");
                txtAllPrice.setText("￥" + allPrice);
                checkboxSelect.setSelected(bool);
                shoppRlvAdapter.notifyDataSetChanged();
            }
        });
    }
    @Override
    protected void initData() {
        mPresenter.getShopp();
    }

    //控件点击监听
    @OnClick({R.id.checkbox_select, R.id.txt_edit, R.id.txt_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.checkbox_select:
                selectAll();
                break;
            case R.id.txt_edit:
                clickEdit();
                break;
            case R.id.txt_submit:
                submitData();
                break;
        }
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
    public void getShoppDelete(ShoppDeleteBean result) {

    }

    /**
     * 全选状态的切换
     */
    private void selectAll() {
        //设置当前是否是权限
        resetSelect(!checkboxSelect.isChecked());
        checkboxSelect.setSelected(!checkboxSelect.isChecked());
        tvQuanxuan.setText("全选("+allNumber+")");
        txtAllPrice.setText("￥"+allPrice);
        shoppRlvAdapter.notifyDataSetChanged();
    }

    /**
     * 编辑状态的切换
     */
    private void clickEdit() {
        //当前是默认的状态---编辑状态
        if ("编辑".equals(txtEdit.getText())) {
            shoppRlvAdapter.isEditor = true;
            txtEdit.setText("完成");
            txtSubmit.setText("删除所选");
            txtAllPrice.setVisibility(View.GONE);
        } else if ("完成".equals(txtEdit.getText())) {   //编辑状态进入默认状态
            shoppRlvAdapter.isEditor = false;
            txtEdit.setText("编辑");
            txtSubmit.setText("下单");
            txtAllPrice.setVisibility(View.VISIBLE);
            txtAllPrice.setText("￥0");
        }
        resetSelect(false);
        shoppRlvAdapter.notifyDataSetChanged();
    }

    /**
     * 提交
     */
    private void submitData() {
        if ("下单".equals(txtSubmit.getText())) {
            //提交数据

        } else if ("删除所选".equals(txtSubmit.getText())) {
            StringBuilder sb = new StringBuilder();
            List<Integer> ids = getSelectProducts();
            for (Integer id : ids) {
                sb.append(id);
                sb.append(",");
            }
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
                String productIds = sb.toString();
                mPresenter.getShoppDelete(productIds);
                shoppRlvAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getActivity(), "没有选中要删除的商品", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 重置选中状态
     *
     * @param bool
     */
    private void resetSelect(boolean bool) {
        for (ShoppBean.DataBean.CartListBean item : listbeans) {
            item.select = bool;
            if (bool) {
                allNumber += item.getNumber();
                allPrice += item.getNumber() * item.getRetail_price();
            }
        }
        if (!bool) {
            allNumber = 0;
            allPrice = 0;
        }
    }

    /**
     * 获取当前选中的商品
     *
     * @return
     */
    private List<Integer> getSelectProducts() {
        List<Integer> ids = new ArrayList<>();
        for (ShoppBean.DataBean.CartListBean item : listbeans) {
            if (item.select) {
                ids.add(item.getProduct_id());
            }
        }
        return ids;
    }

    /**
     * 判断当前数据是否选中
     *
     * @return
     */
    private boolean CheckSelectAll() {
        boolean isSelectAll = true;
        for (ShoppBean.DataBean.CartListBean item : listbeans) {
            if (item.select) {
                allNumber += item.getNumber();
                allPrice += item.getNumber() * item.getRetail_price();
            }
            if (isSelectAll && !item.select) {
                isSelectAll = false;
            }
        }
        return isSelectAll;
    }


}
