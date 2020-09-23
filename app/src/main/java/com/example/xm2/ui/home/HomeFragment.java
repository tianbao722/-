package com.example.xm2.ui.home;


import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.xm2.R;
import com.example.xm2.base.BaseFragment;
import com.example.xm2.base.ConStants;
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
import com.example.xm2.ui.home.activity.DetailGoodActivity;
import com.example.xm2.ui.home.activity.adapter.NewActivity;
import com.example.xm2.ui.home.adapter.HomeListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;


//首页
public class HomeFragment extends BaseFragment<IHome.RecommendPersenter> implements IHome.RecommendView {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_sousuo)
    EditText etSousuo;
    @BindView(R.id.rlv_home)
    RecyclerView rlvHome;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    private ArrayList<HomeBean.HomeListBean> list;
    private HomeListAdapter homeListAdapter;
    private ArrayList<HomeNewBean.DataBeanX.DataBean> dataBeans;
    private ArrayList<HomeNewBean.DataBeanX.FilterCategoryBean> filterCategoryBeans;

    @Override
    protected IHome.RecommendPersenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        initResult();
        ivBack.setVisibility(View.GONE);
        list = new ArrayList<>();
        homeListAdapter = new HomeListAdapter(list, getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        homeListAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int i) {
                int itemType = list.get(i).getItemType();
                switch (itemType) {
                    case HomeBean.ITEM_TYPE_BANNER:
                    case HomeBean.ITEM_TYPE_TAB:
                    case HomeBean.ITEM_TYPE_TITLE:
                    case HomeBean.ITEM_TYPE_TITLETOP:
                    case HomeBean.ITEM_TYPE_TOPIC:
                    case HomeBean.ITEM_TYPE_HOT:
                        return 2;
                    case HomeBean.ITEM_TYPE_BRAND:
                    case HomeBean.ITEM_TYPE_NEW:
                    case HomeBean.ITEM_TYPE_CATEGORY:
                        return 1;
                }
                return 0;
            }
        });
        rlvHome.setLayoutManager(gridLayoutManager);
        homeListAdapter.bindToRecyclerView(rlvHome);
    }

    private void initResult() {
        dataBeans = new ArrayList<>();
        filterCategoryBeans = new ArrayList<>();
        ConStants.dataBean = dataBeans;
        ConStants.filterCategoryBean = filterCategoryBeans;
    }

    @Override
    protected void initData() {
        mPresenter.getHome();
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
        homeListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int type = list.get(position).currentType;
                Intent intent = new Intent();
                switch (type) {
                    case HomeBean.ITEM_TYPE_BANNER:
                        break;
                    case HomeBean.ITEM_TYPE_BRAND:
                        break;
                    case HomeBean.ITEM_TYPE_HOT:
                        HomeBean.DataBean.HotGoodsListBean bean = (HomeBean.DataBean.HotGoodsListBean) list.get(position).data;
                        intent.putExtra("id", bean.getId());
                        intent.setClass(getActivity(), DetailGoodActivity.class);
                        startActivity(intent);
                        break;
                    case HomeBean.ITEM_TYPE_TITLE:
                        Intent intent1 = new Intent(getActivity(), NewActivity.class);
                        startActivity(intent1);
                        break;
                    case HomeBean.ITEM_TYPE_TITLETOP:

                        break;
                    case HomeBean.ITEM_TYPE_TOPIC:
                        break;
                    case HomeBean.ITEM_TYPE_CATEGORY:
                        break;
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
        list.addAll(result);
        homeListAdapter.notifyDataSetChanged();
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
        if (result.getData() != null){
            List<HomeNewBean.DataBeanX.DataBean> data = result.getData().getData();
            dataBeans.addAll(data);
            List<HomeNewBean.DataBeanX.FilterCategoryBean> filterCategory = result.getData().getFilterCategory();
            filterCategoryBeans.addAll(filterCategory);
        }
    }
}