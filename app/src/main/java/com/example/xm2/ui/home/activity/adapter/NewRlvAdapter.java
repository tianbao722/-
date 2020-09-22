package com.example.xm2.ui.home.activity.adapter;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xm2.R;
import com.example.xm2.base.BaseAdapter;
import com.example.xm2.bean.HomeNewBean;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;

public class NewRlvAdapter extends BaseAdapter {


    public NewRlvAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_new_rlv;
    }

    @Override
    protected void bindData(BaseViewHolder viewHolder, Object o) {
        ImageView img = (ImageView) viewHolder.getViewById(R.id.iv_img_new);
        TextView title = (TextView) viewHolder.getViewById(R.id.tv_title_new);
        TextView price = (TextView) viewHolder.getViewById(R.id.tv_txt);
        HomeNewBean.DataBeanX.DataBean bean = (HomeNewBean.DataBeanX.DataBean) o;
        Glide.with(context).load(bean.getList_pic_url()).into(img);
        price.setText("¥"+bean.getRetail_price());
        title.setText(bean.getName());

    }
}
