package com.example.xm2.ui.classify.adapter;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xm2.R;
import com.example.xm2.base.BaseAdapter;
import com.example.xm2.bean.ClassifyRlvBean;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;

public class ClassifyRlvAdapter extends BaseAdapter {


    public ClassifyRlvAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_rlv_classify;
    }

    @Override
    protected void bindData(BaseViewHolder viewHolder, Object o) {
        ImageView img = (ImageView) viewHolder.getViewById(R.id.iv_img_classify_rlv);
        TextView name = (TextView) viewHolder.getViewById(R.id.tv_name_classify);
        ClassifyRlvBean.DataBean.CurrentCategoryBean.SubCategoryListBean bean = (ClassifyRlvBean.DataBean.CurrentCategoryBean.SubCategoryListBean) o;
        Glide.with(context).load(bean.getWap_banner_url()).into(img);
        name.setText(bean.getName());
    }
}
