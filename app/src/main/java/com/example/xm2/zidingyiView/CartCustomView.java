package com.example.xm2.zidingyiView;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.xm2.R;

public class CartCustomView extends LinearLayout implements View.OnClickListener {
    private Context context;
    private TextView jian;
    private TextView txtnum;
    private TextView add;
    private IClick iClick;
    private int num1 = 1;
    private int min = 1;
    private int max = 99;

    //设置接口回调
    public void setiClick(IClick iClick) {
        this.iClick = iClick;
    }

    public CartCustomView(Context context) {
        super(context);
        this.context = context;
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

    //初始化界面有外部控制
    public void initView() {
        jian = findViewById(R.id.tv_subtract);
        txtnum = findViewById(R.id.tv_num);
        add = findViewById(R.id.tv_jia);
        if (jian != null && txtnum != null && add != null) {
            jian.setOnClickListener(this);
            add.setOnClickListener(this);
        } else {
            Toast.makeText(context, "初始化调用出错", Toast.LENGTH_SHORT).show();
        }
    }

    public void initView(int min, int max) {
        this.min = min;
        this.max = max;
        initView();
    }


    /**
     * 设置值
     *
     * @param num
     */
    public void setValue(int num) {
        this.num1 = num;
        txtnum.setText(this.num1+"");
    }

    @Override
    public void onClick(View view) {
        String string = txtnum.getText().toString();
        num1 = Integer.parseInt(string);
        switch (view.getId()) {
            case R.id.tv_jia://加
                num1++;
                if (num1 > max) {
                    num1 = max;
                } else {
                    if (iClick != null) {
                        iClick.iClickNum(num1);
                    }
                }
                txtnum.setText(String.valueOf(num1));

                break;
            case R.id.tv_subtract://减
                num1--;
                if (num1 < 1) {
                    num1 = 1;
                } else {
                    txtnum.setText(String.valueOf(num1));
                    if (iClick != null) {
                        iClick.iClickNum(num1);
                    }
                }
                break;
        }
    }

    public interface IClick {
        void iClickNum(int num);
    }
}
