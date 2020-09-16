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
import com.example.xm2.bean.HomeBean;
import com.example.xm2.bean.HomeGoodDetailBean;
import com.example.xm2.bean.UserBean;
import com.example.xm2.interfaces.home.IHome;
import com.example.xm2.presenter.home.HomePresenter;
import com.example.xm2.ui.home.activity.DetailGoodActivity;
import com.example.xm2.ui.home.adapter.HomeListAdapter;

import java.util.ArrayList;
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

    @Override
    protected void initData() {
        mPresenter.getHome();
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
}