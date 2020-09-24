package com.example.xm2.ui.my.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xm2.R;
import com.example.xm2.base.BaseAdapter;
import com.example.xm2.bean.ShoppDiZhiBean;
import com.example.xm2.ui.my.activity.AddDiZhiActivity;
import com.example.xm2.ui.my.activity.DiZhiActivity;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;

public class DiZhiAdapter extends BaseAdapter {

    public DiZhiAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_dizhi;
    }

    @Override
    protected void bindData(BaseViewHolder viewHolder, Object o) {
        TextView name = (TextView) viewHolder.getViewById(R.id.tv_name_dizhi);
        TextView phone = (TextView) viewHolder.getViewById(R.id.tv_phone_dizhi);
        TextView moren = (TextView) viewHolder.getViewById(R.id.tv_moren);
        TextView dizhi = (TextView) viewHolder.getViewById(R.id.tv_dizhi_dizhi);
        ImageView bianji = (ImageView) viewHolder.getViewById(R.id.iv_bianji);
        ShoppDiZhiBean.DataBean bean = (ShoppDiZhiBean.DataBean) o;
        moren.setVisibility(View.GONE);
        name.setText(bean.getName());
        String mobile = bean.getMobile();
        phone.setText(setPhone(mobile));
        dizhi.setText(bean.getFull_region() + "" + bean.getAddress());
        if (bean.getName().equals("zq")) {
            moren.setVisibility(View.VISIBLE);
        }
        bianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddDiZhiActivity.class);
                context.startActivity(intent);
            }
        });
    }

    private String setPhone(String mobile) {
        String substring = mobile.substring(0, 3);
        String substring1 = mobile.substring(7, 11);
        String phone = substring + "****" + substring1;
        return phone;
    }
}
