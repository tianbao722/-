package com.example.xm2.ui.home.activity.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.xm2.R;
import com.example.xm2.base.BaseAdapter;
import com.example.xm2.bean.HomeGoodDetailBean;

import java.util.List;

public class ImageAdapter extends BaseAdapter {
    public ImageAdapter(Context context, List data) {
        super(context, data);
    }


    @Override
    protected int getLayout() {
        return R.layout.layout_image;
    }

    @Override
    protected void bindData(BaseViewHolder viewHolder, Object o) {
        ImageView img = (ImageView) viewHolder.getViewById(R.id.iv_img);
        HomeGoodDetailBean.DataBeanX.CommentBean.DataBean.PicListBean bean = (HomeGoodDetailBean.DataBeanX.CommentBean.DataBean.PicListBean) o;
        Glide.with(context).load(bean.getPic_url()).into(img);
    }
}
