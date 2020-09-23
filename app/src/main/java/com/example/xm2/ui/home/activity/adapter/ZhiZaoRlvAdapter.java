package com.example.xm2.ui.home.activity.adapter;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xm2.R;
import com.example.xm2.base.BaseAdapter;
import com.example.xm2.bean.HomeZhiZhaoBean;

import java.util.List;

import butterknife.BindView;

public class ZhiZaoRlvAdapter extends BaseAdapter {

    public ZhiZaoRlvAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_zhizao_rlv;
    }

    @Override
    protected void bindData(BaseViewHolder viewHolder, Object o) {
        ImageView img = (ImageView) viewHolder.getViewById(R.id.iv_img_zhizao);
        TextView title = (TextView) viewHolder.getViewById(R.id.tv_title_zhizao);
        TextView price = (TextView) viewHolder.getViewById(R.id.tv_price_zhizao);
        HomeZhiZhaoBean.DataBeanX.DataBean bean = (HomeZhiZhaoBean.DataBeanX.DataBean) o;
        Glide.with(context).load(bean.getApp_list_pic_url()).into(img);
        title.setText(bean.getName());
        price.setText(" | " + bean.getFloor_price() + "元起");
    }
}
