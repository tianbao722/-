package com.example.xm2.ui.classify;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.xm2.R;
import com.example.xm2.base.BaseFragment;
import com.example.xm2.bean.ClassifyBean;
import com.example.xm2.interfaces.classify.IClassify;
import com.example.xm2.presenter.classify.ClassifyPresenter;
import com.example.xm2.ui.classify.adapter.VpTabAdapter;
import com.example.xm2.ui.classify.fragment.ProjectFragment;
import com.example.xm2.zidingyiView.DefaultTransformer;
import com.example.xm2.zidingyiView.VerticalViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;

//分类
public class ClassifyFragment extends BaseFragment<IClassify.Presenter> implements IClassify.View {

    @BindView(R.id.tablayout)
    VerticalTabLayout tablayout;
    @BindView(R.id.vp_tab)
    VerticalViewPager vpTab;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_sousuo)
    EditText etSousuo;
    private ArrayList<String> titles;
    private ArrayList<Fragment> fragments;

    @Override
    protected IClassify.Presenter initPresenter() {
        return new ClassifyPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_classify;
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
        mPresenter.getClassify();
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
    public void getClassifyResult(ClassifyBean result) {
        if (result.getData() != null) {
            titles = new ArrayList<>();
            List<ClassifyBean.DataBean.CategoryListBean> categoryList = result.getData().getCategoryList();
            for (ClassifyBean.DataBean.CategoryListBean item : categoryList) {
                titles.add(item.getName());
            }
            fragments = new ArrayList<>();
            fragments.add(new ProjectFragment());
            fragments.add(new ProjectFragment());
            fragments.add(new ProjectFragment());
            fragments.add(new ProjectFragment());
            fragments.add(new ProjectFragment());
            fragments.add(new ProjectFragment());
            fragments.add(new ProjectFragment());
            fragments.add(new ProjectFragment());
            fragments.add(new ProjectFragment());
            VpTabAdapter vpTabAdapter = new VpTabAdapter(getFragmentManager(), titles, fragments);
            vpTab.setAdapter(vpTabAdapter);
            vpTab.setPageTransformer(true, new DefaultTransformer());
            tablayout.setupWithViewPager(vpTab);
        }
    }
}
