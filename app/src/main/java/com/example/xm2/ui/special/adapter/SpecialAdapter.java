package com.example.xm2.ui.special.adapter;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xm2.R;
import com.example.xm2.base.BaseAdapter;
import com.example.xm2.bean.SpecialBean;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;

public class SpecialAdapter extends BaseAdapter {


    public SpecialAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_special_item;
    }

    @Override
    protected void bindData(BaseViewHolder viewHolder, Object o) {
        SpecialBean.DataBeanX.DataBean bean = (SpecialBean.DataBeanX.DataBean) o;
        ImageView img = (ImageView) viewHolder.getViewById(R.id.iv_img_special);
        TextView name = (TextView) viewHolder.getViewById(R.id.tv_name_special);
        TextView jieshao = (TextView) viewHolder.getViewById(R.id.tv_jieshao);
        TextView price = (TextView) viewHolder.getViewById(R.id.tv_price_special);
        Glide.with(context).load(bean.getScene_pic_url()).into(img);
        name.setText(bean.getTitle());
        jieshao.setText(bean.getSubtitle());
        price.setText(bean.getPrice_info() + "元起");
    }
}
