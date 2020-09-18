package com.example.xm2.ui.shopp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xm2.R;
import com.example.xm2.base.BaseAdapter;
import com.example.xm2.bean.ShoppBean;
import com.example.xm2.zidingyiView.CartCustomView;
import com.google.android.material.appbar.AppBarLayout;

import java.util.List;

import butterknife.BindView;

public class ShoppRlvAdapter extends BaseAdapter {
    public ShoppRlvAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_shopp_rlv;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void bindData(BaseViewHolder viewHolder, Object o) {
        ShoppBean.DataBean.CartListBean bean = (ShoppBean.DataBean.CartListBean) o;
        CheckBox check = (CheckBox) viewHolder.getViewById(R.id.checkbox_select);
        ImageView img = (ImageView) viewHolder.getViewById(R.id.img_icon);
        TextView name = (TextView) viewHolder.getViewById(R.id.txt_name);
        TextView number = (TextView) viewHolder.getViewById(R.id.txt_number);
        TextView price = (TextView) viewHolder.getViewById(R.id.txct_price);
        TextView select = (TextView) viewHolder.getViewById(R.id.txt_select);
        TextView subtract = (TextView) viewHolder.getViewById(R.id.tv_subtract);
        TextView num = (TextView) viewHolder.getViewById(R.id.tv_num);
        TextView jia = (TextView) viewHolder.getViewById(R.id.tv_jia);
        CartCustomView shuliang = (CartCustomView) viewHolder.getViewById(R.id.view_number);
        shuliang.setVisibility(View.GONE);
        select.setVisibility(View.GONE);
        number.setText("X" +bean.getNumber());
        Glide.with(context).load(bean.getList_pic_url()).into(img);
        name.setText(bean.getGoods_name());
        price.setText("¥ " + bean.getMarket_price());
    }
}
