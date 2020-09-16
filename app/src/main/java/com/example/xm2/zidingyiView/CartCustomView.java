package com.example.xm2.zidingyiView;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class CartCustomView extends LinearLayout {
    private Context context;
    public CartCustomView(Context context) {
        super(context);
    }

    public CartCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CartCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CartCustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    private void initView(Context context){
        this.context = context;

    }
}
