package com.example.xm2.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter {
    public Context context;
    public List<T> data;
    public onClick onClick;

    public void setOnClick(BaseAdapter.onClick onClick) {
        this.onClick = onClick;
    }

    public BaseAdapter(Context context, List<T> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(getLayout(), parent,false);
        BaseViewHolder baseViewHolder = new BaseViewHolder(inflate);
        baseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClick != null) {
                    onClick.click(baseViewHolder.getLayoutPosition());//获取下标
                }
            }
        });
        return baseViewHolder;
    }

    protected abstract int getLayout();

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BaseViewHolder viewHolder = (BaseViewHolder) holder;
        T t = data.get(position);
        bindData(viewHolder, t);
    }

    /**
     * 绑定数据
     *
     * @param viewHolder
     * @param t
     */
    protected abstract void bindData(BaseViewHolder viewHolder, T t);

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface onClick {
        void click(int pos);
    }

    /**
     * 基类适配器
     */
    public class BaseViewHolder extends RecyclerView.ViewHolder {
        SparseArray views = new SparseArray();

        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);

        }

        /**
         * 通过id获取适配器的组件
         *
         * @param id
         * @return
         */
        public View getViewById(int id) {
            View view = (View) views.get(id);
            if (view == null) {
                view = itemView.findViewById(id);
                views.append(id, view);
            }
            return view;
        }
    }
}
