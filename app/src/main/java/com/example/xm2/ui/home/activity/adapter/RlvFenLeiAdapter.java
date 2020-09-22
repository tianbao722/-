package com.example.xm2.ui.home.activity.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.xm2.R;
import com.example.xm2.base.BaseAdapter;
import com.example.xm2.bean.HomeNewBean;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;

public class RlvFenLeiAdapter extends BaseAdapter {


    public RlvFenLeiAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_rlv_fenlei;
    }

    @Override
    protected void bindData(BaseViewHolder viewHolder, Object o) {
        TextView title = (TextView) viewHolder.getViewById(R.id.tv_title_new_feneli);
        HomeNewBean.DataBeanX.FilterCategoryBean bean = (HomeNewBean.DataBeanX.FilterCategoryBean) o;
        title.setText(bean.getName());
    }
}
