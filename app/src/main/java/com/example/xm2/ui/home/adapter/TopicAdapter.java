package com.example.xm2.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.xm2.R;
import com.example.xm2.bean.HomeBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCBuriedPoint;

public class TopicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<HomeBean.DataBean.TopicListBean> listBeans;

    public TopicAdapter(Context context, List<HomeBean.DataBean.TopicListBean> listBeans) {
        this.context = context;
        this.listBeans = listBeans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_home_topic, parent, false);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        Glide.with(context).load(listBeans.get(position).getItem_pic_url()).into(viewHolder.imgTopic);
        viewHolder.txtTopicName.setText(listBeans.get(position).getTitle());
        viewHolder.txtTopicPrice.setText(listBeans.get(position).getPrice_info() + "起");
        viewHolder.tvTitle.setText(listBeans.get(position).getSubtitle());
    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_topic)
        ImageView imgTopic;
        @BindView(R.id.txt_topic_name)
        TextView txtTopicName;
        @BindView(R.id.txt_topic_price)
        TextView txtTopicPrice;
        @BindView(R.id.tv_title)
        TextView tvTitle;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
