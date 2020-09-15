package com.example.xm2.ui.home.adapter;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.database.sqlite.SQLiteReadOnlyDatabaseException;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.xm2.R;
import com.example.xm2.bean.HomeBean;
import com.example.xm2.utiles.SystemUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class HomeListAdapter extends BaseMultiItemQuickAdapter<HomeBean.HomeListBean, BaseViewHolder> {

    private Context context;
    private String priceWord;
    private TopicAdapter topicAdapter;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public HomeListAdapter(List<HomeBean.HomeListBean> data, Context context) {
        super(data);
        this.context = context;
        priceWord = context.getString(R.string.word_price_brand);
        //做UI绑定
        addItemType(HomeBean.ITEM_TYPE_BANNER, R.layout.layout_home_banner);
        addItemType(HomeBean.ITEM_TYPE_TAB, R.layout.layout_home_tab);
        addItemType(HomeBean.ITEM_TYPE_TITLETOP, R.layout.layout_title_top);
        addItemType(HomeBean.ITEM_TYPE_BRAND, R.layout.layout_home_brand);
        addItemType(HomeBean.ITEM_TYPE_TITLE, R.layout.layout_title);
        addItemType(HomeBean.ITEM_TYPE_NEW, R.layout.layout_home_newgood);
        addItemType(HomeBean.ITEM_TYPE_TITLETOP, R.layout.layout_title_top);
        addItemType(HomeBean.ITEM_TYPE_HOT, R.layout.layout_home_hot);
        addItemType(HomeBean.ITEM_TYPE_TITLETOP, R.layout.layout_title_top);
        addItemType(HomeBean.ITEM_TYPE_TOPIC, R.layout.layout_home_topiclist);
    }

    /**
     * 主要刷新数据，绑定数据  onbindviewholder
     *
     * @param helper viewholder 界面
     * @param item   数据
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void convert(BaseViewHolder helper, HomeBean.HomeListBean item) {
        switch (item.getItemType()) {
            case HomeBean.ITEM_TYPE_TITLE:
                updateTitle(helper, (String) item.data);
                break;
            case HomeBean.ITEM_TYPE_TITLETOP:
                updateTitle(helper, (String) item.data);
                break;
            case HomeBean.ITEM_TYPE_BANNER:
                updateBanner(helper, (List<HomeBean.DataBean.BannerBean>) item.data);
                break;
            case HomeBean.ITEM_TYPE_TAB:
                updateTab(helper, (List<HomeBean.DataBean.ChannelBean>) item.data);
                break;
            case HomeBean.ITEM_TYPE_BRAND:
                updateBrand(helper, (HomeBean.DataBean.BrandListBean) item.data);
                break;
            case HomeBean.ITEM_TYPE_NEW:
                updateNewGood(helper, (HomeBean.DataBean.NewGoodsListBean) item.data);
                break;
            case HomeBean.ITEM_TYPE_HOT:
                udpateHot(helper, (HomeBean.DataBean.HotGoodsListBean) item.data);
                break;
            case HomeBean.ITEM_TYPE_TOPIC:
                updateTopic(helper, (List<HomeBean.DataBean.TopicListBean>) item.data);
                break;
        }
    }

    /**
     * 刷新导航
     *
     * @param helper
     * @param data
     */
    private void updateDaoHang(BaseViewHolder helper, String data) {
        TextView canchu = helper.getView(R.id.tv_canchu);
        TextView fuzhuang = helper.getView(R.id.tv_fuzhuang);
        TextView jujia = helper.getView(R.id.tv_jujia);
        TextView peijian = helper.getView(R.id.tv_peijian);
        TextView zhiqu = helper.getView(R.id.tv_zhiqu);
        canchu.setText("餐厨");
        fuzhuang.setText("服装");
        jujia.setText("居家");
        peijian.setText("配件");
        zhiqu.setText("志趣");
    }

    /**
     * 刷新title
     *
     * @param viewHolder
     * @param title
     */
    private void updateTitle(BaseViewHolder viewHolder, String title) {
        viewHolder.setText(R.id.txt_title, title);
    }

    /**
     * 刷新banner
     *
     * @param viewHolder
     * @param bannsers
     */
    private void updateBanner(BaseViewHolder viewHolder, List<HomeBean.DataBean.BannerBean> bannsers) {
        Banner banner = viewHolder.getView(R.id.banner);
        if (banner.getTag() == null || (int) banner.getTag() == 0) {
            ArrayList<String> images = new ArrayList<>();
            ArrayList<String> titles = new ArrayList<>();
            for (int i = 0; i < bannsers.size(); i++) {
                String image_url = bannsers.get(i).getImage_url();
                String name = bannsers.get(i).getName();
                images.add(image_url);
                titles.add(name);
            }
            banner
                    .setImages(images)
                    .setBannerTitles(titles)
                    .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)
                    .setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            Glide.with(context).load(path).into(imageView);
                        }
                    })
                    .start();
        }
    }

    /**
     * 刷新channel
     *
     * @param viewHolder
     * @param channels
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void updateTab(BaseViewHolder viewHolder, List<HomeBean.DataBean.ChannelBean> channels) {
        LinearLayout layoutChannels = viewHolder.getView(R.id.layout_tab);
        //只让当前的布局内容添加一次 only one
        if (layoutChannels.getChildCount() == 0) {
            for (HomeBean.DataBean.ChannelBean item : channels) {
                TextView tab = new TextView(context);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1);
                int size = SystemUtils.dp2px(context, 14);
                tab.setTextSize(size);
                tab.setGravity(Gravity.CENTER);
                @SuppressLint("UseCompatLoadingForDrawables")
                Drawable icon = context.getDrawable(R.mipmap.ic_channel1);
                tab.setCompoundDrawables(null, icon, null, null);
                layoutChannels.addView(tab);
            }
        }
    }

    /**
     * 刷新品牌
     *
     * @param viewHolder
     * @param brands
     */
    private void updateBrand(BaseViewHolder viewHolder, HomeBean.DataBean.BrandListBean brands) {
        if (!TextUtils.isEmpty(brands.getNew_pic_url())) {
            Glide.with(context).load(brands.getNew_pic_url()).into((ImageView) viewHolder.getView(R.id.img_brand));
        }
        viewHolder.setText(R.id.txt_brand_name, brands.getName());
        String price = priceWord.replace("$", String.valueOf(brands.getFloor_price()));
        viewHolder.setText(R.id.txt_brand_price, price);
    }

    /**
     * 刷新新品数据
     *
     * @param viewHolder
     * @param newGoods
     */
    private void updateNewGood(BaseViewHolder viewHolder, HomeBean.DataBean.NewGoodsListBean newGoods) {
        ImageView img = viewHolder.getView(R.id.img_newgood);
        TextView name = viewHolder.getView(R.id.txt_newgood_name);
        TextView price = viewHolder.getView(R.id.txt_newgood_price);
        Glide.with(context).load(newGoods.getList_pic_url()).into(img);
        name.setText(newGoods.getName());
        price.setText("¥" + newGoods.getRetail_price() + "");
    }

    /**
     * 刷新人气数据
     */
    private void udpateHot(BaseViewHolder viewHolder, HomeBean.DataBean.HotGoodsListBean hotGoods) {
        ImageView img = viewHolder.getView(R.id.img_hot);
        TextView name = viewHolder.getView(R.id.txt_hot_name);
        TextView price = viewHolder.getView(R.id.txt_hot_price);
        TextView title = viewHolder.getView(R.id.txt_hot_title);
        Glide.with(context).load(hotGoods.getList_pic_url()).into(img);
        name.setText(hotGoods.getName());
        price.setText("¥" + hotGoods.getRetail_price());
        title.setText(hotGoods.getGoods_brief());
    }

    /**
     * 刷新专题
     *
     * @param viewHolder
     * @param topicGoods
     */
    private void updateTopic(BaseViewHolder viewHolder, List<HomeBean.DataBean.TopicListBean> topicGoods) {
        RecyclerView rlv = viewHolder.getView(R.id.recyclerviewTopic);
        if (topicAdapter == null) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            topicAdapter = new TopicAdapter(context, topicGoods);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rlv.setLayoutManager(linearLayoutManager);
            rlv.setAdapter(topicAdapter);
        } else if (rlv.getAdapter() == null) {
            rlv.setAdapter(topicAdapter);
        }
    }
}
