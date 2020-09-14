package com.example.xm2.common;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//设置RecclerView的子条目间距
public class VideoItemDecoration extends RecyclerView.ItemDecoration {
    private int pace;//item之间的间距

    public void setPace(int pace) {
        this.pace = pace;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        //不是第一个的格子都设一个左边和底部的间距
        //由于每行都只有2个，所以第一个都是2的倍数，把左边距设为0
        //左边的纵列item的左右两边都有空隙
        if (parent.getChildLayoutPosition(view) % 2==0) {
            outRect.left = pace;
            outRect.right = pace;
        }else{
            //右边的纵列左边空隙为0 右有pace空隙
            outRect.right = pace;
        }
        outRect.top = pace;
    }
}
