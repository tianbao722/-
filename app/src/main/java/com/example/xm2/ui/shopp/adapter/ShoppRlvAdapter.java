package com.example.xm2.ui.shopp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xm2.R;
import com.example.xm2.base.BaseAdapter;
import com.example.xm2.bean.ShoppBean;
import com.example.xm2.zidingyiView.CartCustomView;

import java.util.List;

public class ShoppRlvAdapter extends BaseAdapter {
    public boolean isEditor; //是否是编辑状态
    private CheckBoxClick click;

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
        CheckBox checkBox = (CheckBox) viewHolder.getViewById(R.id.checkbox_select);
        ImageView img = (ImageView) viewHolder.getViewById(R.id.img_icon);
        TextView txtName = (TextView) viewHolder.getViewById(R.id.txt_name);
        TextView txtNumber = (TextView) viewHolder.getViewById(R.id.txt_number);
        TextView txtPrice = (TextView) viewHolder.getViewById(R.id.txct_price);
        TextView txtSelect = (TextView) viewHolder.getViewById(R.id.txt_select);
        TextView subtract = (TextView) viewHolder.getViewById(R.id.tv_subtract);
        TextView num = (TextView) viewHolder.getViewById(R.id.tv_num);
        TextView jia = (TextView) viewHolder.getViewById(R.id.tv_jia);
        CartCustomView cartCustomView = (CartCustomView) viewHolder.getViewById(R.id.view_number);
        //通过编辑状态控制界面组件
        if (isEditor) {
            txtName.setVisibility(View.GONE);
            txtNumber.setVisibility(View.GONE);
            txtSelect.setVisibility(View.VISIBLE);
            cartCustomView.setVisibility(View.VISIBLE);
        } else {
            txtName.setVisibility(View.VISIBLE);
            txtNumber.setVisibility(View.VISIBLE);
            txtSelect.setVisibility(View.GONE);
            cartCustomView.setVisibility(View.GONE);
        }
        txtName.setText(bean.getGoods_name());
        txtNumber.setText("X" + bean.getNumber());
        txtPrice.setText("￥" + bean.getRetail_price());
        Glide.with(context).load(bean.getList_pic_url()).into(img);
        cartCustomView.initView();
        cartCustomView.setValue(bean.getNumber());
        cartCustomView.setiClick(new CartCustomView.IClick() {
            @Override
            public void iClickNum(int num) {
                bean.setNumber(num);
            }
        });
        checkBox.setChecked(bean.select);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                bean.select = isChecked;
                if (click != null) {
                    click.checkChange();
                }
            }
        });


    }

    public void setOnItemCheckBoxClickListener(CheckBoxClick click) {
        this.click = click;
    }


    public interface CheckBoxClick {
        void checkChange();
    }
}
