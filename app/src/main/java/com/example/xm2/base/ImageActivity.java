package com.example.xm2.base;

import android.content.Intent;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;


import com.example.xm2.R;
import com.example.xm2.adapter.ImageMagnifyAdapter;
import com.example.xm2.interfaces.IBasePresenter;

import java.util.ArrayList;

import butterknife.BindView;
//图片大图
public class ImageActivity extends BaseActivity {
    @BindView(R.id.vp_pop)
    ViewPager vpPop;
    @BindView(R.id.tv_pop)
    TextView tvPop;

    @Override
    protected IBasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_image;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        int postion1 = intent.getIntExtra("postion", 0);
        ArrayList<String> list = intent.getStringArrayListExtra("list");
        ImageMagnifyAdapter imgVpAda = new ImageMagnifyAdapter(this, list);
        vpPop.setAdapter(imgVpAda);
        tvPop.setText(postion1+1+"/"+list.size());
        vpPop.setCurrentItem(postion1);
        imgVpAda.setCallBack(new ImageMagnifyAdapter.onCallBack() {
            @Override
            public void onItemClick() {
                finish();
            }
        });
        vpPop.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tvPop.setText(position+1+"/"+list.size());
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initData() {

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
}