package com.example.xm2.ui.classify.fragment;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.xm2.R;
import com.example.xm2.base.BaseFragment;
import com.example.xm2.bean.ClassifyBean;
import com.example.xm2.bean.ClassifyRlvBean;
import com.example.xm2.interfaces.classify.IClassify;
import com.example.xm2.presenter.classify.ClassifyPresenter;
import com.example.xm2.ui.classify.adapter.ClassifyRlvAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ProjectFragment extends BaseFragment<IClassify.Presenter> implements IClassify.View {
    @BindView(R.id.tv_title_classify)
    TextView tvTitleClassify;
    @BindView(R.id.rlv_project)
    RecyclerView rlvProject;
    @BindView(R.id.iv_img_classify)
    ImageView ivImgClassify;
    @BindView(R.id.tv_img_title)
    TextView tvImgTitle;
    @BindView(R.id.rlimage_classify)
    RelativeLayout rlimageClassify;
    private int id = 1005000;
    private ArrayList<ClassifyRlvBean.DataBean.CurrentCategoryBean.SubCategoryListBean> list;
    private ClassifyRlvAdapter classifyRlvAdapter;

    public ProjectFragment(int id) {
        this.id = id;
    }

    @Override
    protected IClassify.Presenter initPresenter() {
        return new ClassifyPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_project;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rlvProject.setLayoutManager(gridLayoutManager);
        classifyRlvAdapter = new ClassifyRlvAdapter(getActivity(), list);
        rlvProject.setAdapter(classifyRlvAdapter);
    }

    @Override
    protected void initData() {
        mPresenter.getClassifyRlv(id);
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void getClassifyResult(ClassifyBean result) {

    }

    @Override
    public void getClassifyRlvResult(ClassifyRlvBean result) {
        if (result.getData().getCurrentCategory() != null) {
            ClassifyRlvBean.DataBean.CurrentCategoryBean currentCategory = result.getData().getCurrentCategory();
            tvTitleClassify.setText("----" + currentCategory.getName() + "分类----");
            Glide.with(getActivity()).load(currentCategory.getBanner_url()).into(ivImgClassify);
            tvImgTitle.setText(currentCategory.getFront_name());
            List<ClassifyRlvBean.DataBean.CurrentCategoryBean.SubCategoryListBean> subCategoryList = result.getData().getCurrentCategory().getSubCategoryList();
            list.addAll(subCategoryList);
            classifyRlvAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showTips(String tips) {

    }

    @Override
    public void showLoading(int visble) {

    }
}
