package com.example.xm2.ui.home.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.xm2.MainActivity;
import com.example.xm2.R;
import com.example.xm2.base.BaseActivity;
import com.example.xm2.base.BaseAdapter;
import com.example.xm2.base.ImageActivity;
import com.example.xm2.bean.HomeBean;
import com.example.xm2.bean.HomeGoodDetailBean;
import com.example.xm2.bean.ShoppAddBean;
import com.example.xm2.bean.SpecialBean;
import com.example.xm2.bean.UserBean;
import com.example.xm2.interfaces.home.IHome;
import com.example.xm2.presenter.home.HomePresenter;
import com.example.xm2.ui.home.activity.adapter.ImageAdapter;
import com.example.xm2.ui.my.activity.LoginActivity;
import com.example.xm2.utiles.SpUtils;
import com.example.xm2.utiles.SystemUtils;
import com.example.xm2.zidingyiView.CartCustomView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailGoodActivity extends BaseActivity<IHome.RecommendPersenter> implements IHome.RecommendView {
    @BindView(R.id.layout_back)
    RelativeLayout layoutBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.txt_des)
    TextView txtDes;
    @BindView(R.id.txt_price)
    TextView txtPrice;
    @BindView(R.id.txt_product)
    TextView txtProduct;
    @BindView(R.id.layout_norms)
    FrameLayout layoutNorms;
    @BindView(R.id.layout_comment)
    FrameLayout layoutComment;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.layout_collect)
    RelativeLayout layoutCollect;
    @BindView(R.id.img_cart)
    ImageView imgCart;
    @BindView(R.id.layout_cart)
    RelativeLayout layoutCart;
    @BindView(R.id.txt_buy)
    TextView txtBuy;
    @BindView(R.id.sv)
    ScrollView sv;
    @BindView(R.id.cl)
    ConstraintLayout cl;
    @BindView(R.id.tv_pinglun)
    TextView tvPinglun;
    @BindView(R.id.rlv_pinglun)
    RecyclerView rlvPinglun;
    @BindView(R.id.tv_time_pingjia)
    TextView tvTimePingjia;
    @BindView(R.id.tv_chichun)
    TextView tvChichun;
    @BindView(R.id.tv_chichun_num)
    TextView tvChichunNum;
    @BindView(R.id.tv_mianliao)
    TextView tvMianliao;
    @BindView(R.id.tv_mianliao_num)
    TextView tvMianliaoNum;
    @BindView(R.id.tv_tianchongwu)
    TextView tvTianchongwu;
    @BindView(R.id.tv_tianchongwu_num)
    TextView tvTianchongwuNum;
    @BindView(R.id.tv_zhongliang)
    TextView tvZhongliang;
    @BindView(R.id.tv_zhongliang_num)
    TextView tvZhongliangNum;
    @BindView(R.id.tv_tishi)
    TextView tvTishi;
    @BindView(R.id.tv_tishi_num)
    TextView tvTishiNum;
    @BindView(R.id.layout_bottom)
    LinearLayout layoutBottom;
    @BindView(R.id.tv_num_all)
    TextView tvNumAll;
    @BindView(R.id.tv_add)
    TextView tvAdd;
    private PopupWindow mPopWindow;
    private int currentNum = 1;
    private String html = "<html>\n" +
            "            <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\"/>\n" +
            "            <head>\n" +
            "                <style>\n" +
            "                    p{\n" +
            "                        margin:0px;\n" +
            "                    }\n" +
            "                    img{\n" +
            "                        width:100%;\n" +
            "                        height:auto;\n" +
            "                    }\n" +
            "                </style>\n" +
            "            </head>\n" +
            "            <body>\n" +
            "                $\n" +
            "            </body>\n" +
            "        </html>";
    private HomeGoodDetailBean goodDetailBean;
    private int productId;

    @Override
    protected IHome.RecommendPersenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_detail_good;
    }

    @Override
    protected void initView() {
        cl.getBackground().setAlpha(100);
    }

    @Override
    protected void initData() {
        int id = getIntent().getIntExtra("id", 0);
        mPresenter.getGoodDetail(id);
    }

    @SuppressLint("NewApi")
    @Override
    protected void initListener() {
        layoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        layoutNorms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initPopupWindow();
            }
        });
    }

    /**
     * 刷新banner
     */
    private void updateBanner(List<HomeGoodDetailBean.DataBeanX.GalleryBean> gallery) {
        ArrayList<String> images = new ArrayList<>();
        for (int i = 0; i < gallery.size(); i++) {
            String img_url = gallery.get(i).getImg_url();
            images.add(img_url);
        }
        banner
                .setImages(images)
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Glide.with(context).load(path).into(imageView);
                    }
                })
                .start();
    }

    /**
     * 刷新评论
     *
     * @param commentBean
     */
    private void updateComment(HomeGoodDetailBean.DataBeanX.CommentBean commentBean) {
        if (commentBean != null) {
            List<HomeGoodDetailBean.DataBeanX.CommentBean.DataBean.PicListBean> pic_list = commentBean.getData().getPic_list();
            ArrayList<String> images = new ArrayList<>();
            for (int i = 0; i < pic_list.size(); i++) {
                String pic_url = pic_list.get(i).getPic_url();
                images.add(pic_url);
            }
            tvTimePingjia.setText(commentBean.getData().getAdd_time());
            tvPinglun.setText(commentBean.getData().getContent());
            GridLayoutManager gridLayoutManager = new GridLayoutManager(DetailGoodActivity.this, 4);
            rlvPinglun.setLayoutManager(gridLayoutManager);
            ImageAdapter imageAdapter = new ImageAdapter(DetailGoodActivity.this, commentBean.getData().getPic_list());
            rlvPinglun.setAdapter(imageAdapter);
            imageAdapter.setOnClick(new BaseAdapter.onClick() {
                @Override
                public void click(int pos) {
                    Intent intent = new Intent(DetailGoodActivity.this, ImageActivity.class);
                    intent.putExtra("postion", pos);
                    intent.putExtra("list", images);
                    startActivity(intent);
                }
            });
        }
    }

    /**
     * 刷新参数的布局
     *
     * @param attributeBean
     */
    private void updateParameter(List<HomeGoodDetailBean.DataBeanX.AttributeBean> attributeBean) {
        if (attributeBean != null) {
            String name = attributeBean.get(0).getName();
            String value = attributeBean.get(0).getValue();
            String name1 = attributeBean.get(1).getName();
            String value1 = attributeBean.get(1).getValue();
            String name2 = attributeBean.get(2).getName();
            String value2 = attributeBean.get(2).getValue();
            String name3 = attributeBean.get(3).getName();
            String value3 = attributeBean.get(3).getValue();
            String name4 = attributeBean.get(4).getName();
            String value4 = attributeBean.get(4).getValue();
            tvChichun.setText(name);
            tvChichunNum.setText(value);
            tvMianliao.setText(name1);
            tvMianliaoNum.setText(value1);
            tvTianchongwu.setText(name2);
            tvTianchongwuNum.setText(value2);
            tvZhongliang.setText(name3);
            tvZhongliangNum.setText(value3);
            tvTishi.setText(name4);
            tvTishiNum.setText(value4);
        }
    }

    private void updateDetailInfo(HomeGoodDetailBean.DataBeanX.InfoBean infoBean) {
        if (!TextUtils.isEmpty(infoBean.getGoods_desc())) {
            String h5 = infoBean.getGoods_desc();
            html = html.replace("$", h5);
            webView.loadDataWithBaseURL("about:blank", html, "text/html", "utf-8", null);
            //webView.loadData(html,"text/html","utf-8");
        }
    }

    @Override
    public void getHomeResult(List<HomeBean.HomeListBean> result) {

    }

    @Override
    public void getGoodDetailResult(HomeGoodDetailBean result) {
        goodDetailBean = result;
        //banner刷新
        updateBanner(result.getData().getGallery());
        //评论
        if (result.getData().getComment().getCount() > 0) {
            layoutComment.setVisibility(View.VISIBLE);
            updateComment(result.getData().getComment());
        } else {
            layoutComment.setVisibility(View.GONE);
        }
        //设置参数
        updateParameter(result.getData().getAttribute());
        //详情信息的展示
        updateDetailInfo(result.getData().getInfo());
    }

    @Override
    public void getLoginResult(UserBean result) {

    }

    @Override
    public void getZhuCeResult(UserBean result) {

    }

    @Override
    public void getSpecialResult(SpecialBean result) {

    }

    //添加到购物车返回
    @Override
    public void addCartInfoReturn(ShoppAddBean result) {
        List<ShoppAddBean.DataBean.CartListBean> beans = result.getData().getCartList();
        for (int i = 0; i < beans.size(); i++) {
            int product_id = beans.get(i).getProduct_id();
            if (productId == product_id) {
                Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

    @Override
    public void showTips(String tips) {

    }

    @Override
    public void showLoading(int visble) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @SuppressLint("ResourceAsColor")
    public void initPopupWindow() {
        if (mPopWindow != null && mPopWindow.isShowing()) {

        } else {
            View contentView = LayoutInflater.from(this).inflate(R.layout.layout_popupwind_good, null);
            int height = SystemUtils.dp2px(this, 200);
            mPopWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, height);
//            mPopWindow.setFocusable(true);//点击空地popupwindow消失
//            mPopWindow.setOutsideTouchable(true);//点击空地popupwindow消失
            bgAlpha(0.5f);
//            mPopWindow.setContentView(contentView);
            mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    bgAlpha(1f);
                }
            });
            contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            CartCustomView cartCustomView = contentView.findViewById(R.id.ccv);
            String numAll = tvNumAll.getText().toString();
            int numall = Integer.parseInt(numAll);
            cartCustomView.initView();
            cartCustomView.setValue(numall);
            ImageView txtClose = contentView.findViewById(R.id.iv_cha);
            txtClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPopWindow.dismiss();
                    mPopWindow = null;
                }
            });
            int[] pt = new int[2];
//            获取到的屏幕宽高(除开了当前组件的宽高）
            layoutBottom.getLocationInWindow(pt);
            // Display display = getWindowManager().getDefaultDisplay();
            // int activityheight = display.getHeight();
            mPopWindow.showAtLocation(layoutBottom, Gravity.NO_GRAVITY, 0, pt[1] - height);
            cartCustomView.setiClick(new CartCustomView.IClick() {
                @Override
                public void iClickNum(int num) {
                    tvNumAll.setText(num + "");
                }
            });
        }
    }

    public void bgAlpha(float bgalpha) {
        WindowManager.LayoutParams ab = getWindow().getAttributes();
        ab.alpha = bgalpha;
        getWindow().setAttributes(ab);
    }

    @OnClick({R.id.layout_collect, R.id.tv_add, R.id.layout_cart})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.layout_collect:

                break;
            case R.id.tv_add:
                addCart();
                break;
            case R.id.layout_cart:
                setResult(1000);
                finish();
                break;
        }
    }

    /**
     * 添加到购物车
     */
    private void addCart() {
        String token = SpUtils.getInstance().getString("token");
        if (!TextUtils.isEmpty(token)) {
            //判断当前的规格弹框是否打开
            if (mPopWindow != null && mPopWindow.isShowing()) {
                //添加到购物车的操作
                if (goodDetailBean != null && goodDetailBean.getData().getProductList().size() > 0) {
                    int goodsId = goodDetailBean.getData().getProductList().get(0).getGoods_id();
                    productId = goodDetailBean.getData().getProductList().get(0).getId();
                    mPresenter.addCart(goodsId, currentNum, productId);
                    mPopWindow.dismiss();
                    mPopWindow = null;
                } else {
                    Toast.makeText(this, "没有产品数据", Toast.LENGTH_SHORT).show();
                }
            } else {
                initPopupWindow();
            }
        } else {
            Toast.makeText(this, "未登录", Toast.LENGTH_SHORT).show();
            //Intent跳转到登录
            Intent intent = new Intent(DetailGoodActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }


}