package com.example.xm2.ui.my.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xm2.R;
import com.example.xm2.base.BaseActivity;
import com.example.xm2.bean.HomeBean;
import com.example.xm2.bean.HomeGoodDetailBean;
import com.example.xm2.bean.HomeNewBean;
import com.example.xm2.bean.HomeNewTopBean;
import com.example.xm2.bean.HomeZhiZhaoBean;
import com.example.xm2.bean.MyadressBean;
import com.example.xm2.bean.ShoppAddBean;
import com.example.xm2.bean.SpecialBean;
import com.example.xm2.bean.UserBean;
import com.example.xm2.interfaces.IBasePresenter;
import com.example.xm2.interfaces.home.IHome;
import com.example.xm2.presenter.home.HomePresenter;
import com.example.xm2.utiles.SystemUtils;
import com.weigan.loopview.LoopView;
import com.weigan.loopview.OnItemSelectedListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddDiZhiActivity extends BaseActivity<IHome.RecommendPersenter> implements IHome.RecommendView {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.cb_dizhi)
    CheckBox cbDizhi;
    @BindView(R.id.btn_finish_dizhi)
    Button btnFinishDizhi;
    @BindView(R.id.btn_baocun_dizhi)
    Button btnBaocunDizhi;
    @BindView(R.id.et_name_dizhi)
    EditText etNameDizhi;
    @BindView(R.id.et_phone_num)
    EditText etPhoneNum;
    @BindView(R.id.et_dizhi)
    TextView etDizhi;
    @BindView(R.id.et_xiangxidizhi)
    EditText etXiangxidizhi;

    private Map<Integer, List<MyadressBean.DataBean>> map;
    private LoopView province;
    private LoopView city;
    private LoopView area;
    private int curProvinceId, curCityId, curAreaId; //当前省市区的ID
    private TextView shengfen;
    private TextView chengshi;
    private TextView quxian;

    @Override
    protected IHome.RecommendPersenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_add_di_zhi;
    }

    @Override
    protected void initView() {
        map = new HashMap<>();
    }

    @Override
    protected void initData() {
        mPresenter.getAdress(1);
    }

    @Override
    protected void initListener() {
        //返回
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //返回
        btnFinishDizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //保存地址
        btnBaocunDizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //保存地址



            }
        });
        //选择地址popupwindow
        etDizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPopupWindow();
            }
        });
    }

    private void setPopupWindow() {
        final String[] dizhi = new String[3];
        //地址选择
        View inflate = LayoutInflater.from(AddDiZhiActivity.this).inflate(R.layout.layout_popupwind_adddizhi, null);
        TextView queding = inflate.findViewById(R.id.tv_queding);//确定
        //省份
        shengfen = inflate.findViewById(R.id.tv_shengfen);
        //城市
        chengshi = inflate.findViewById(R.id.tv_chengshi);
        //区县
        quxian = inflate.findViewById(R.id.tv_quxian);
        //选择省份
        province = inflate.findViewById(R.id.adress_province);
        //选择城市
        city = inflate.findViewById(R.id.adress_city);
        //选择区县
        area = inflate.findViewById(R.id.adress_area);
        shengfen.setTextColor(Color.RED);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int height = SystemUtils.dp2px(AddDiZhiActivity.this, 300);
        int windowWidth = displayMetrics.widthPixels;//屏幕宽度
        PopupWindow pw = new PopupWindow(inflate, windowWidth, height);
        pw.setOutsideTouchable(true);
        pw.setFocusable(true);
        bgAlpha(0.5f);
        //设置屏幕透明度
        pw.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                bgAlpha(1f);
            }
        });
        pw.showAtLocation(btnBaocunDizhi, Gravity.BOTTOM, 0, 0);
        //确定
        queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String shengfen1 = dizhi[0];
                String chengshi1 = dizhi[1];
                String quxian1 = dizhi[2];
                if (shengfen1 != null && chengshi1 != null && quxian1 != null) {
                    String name = shengfen1 + chengshi1 + quxian1;
                    etDizhi.setText(name);
                    pw.dismiss();
                } else {
                    Toast.makeText(AddDiZhiActivity.this, "请选择地址", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //选择省份
        province.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                List<MyadressBean.DataBean> proviceList = map.get(1); //key为1固定为省的数据
                MyadressBean.DataBean dataBean = proviceList.get(index);
                if (dataBean == null) {
                    Toast.makeText(AddDiZhiActivity.this, "请稍后...", Toast.LENGTH_SHORT).show();
                } else {
                    curProvinceId = dataBean.getId();
                    String name = dataBean.getName();
                    dizhi[0] = name;
                    mPresenter.getAdress(curProvinceId);
                    List<String> items = new ArrayList<>();
                    items.add("请选择");
                    city.setItems(items);
                    shengfen.setText(dataBean.getName());
                    chengshi.setText("城市");
                    quxian.setText("区县");
                }
                shengfen.setTextColor(Color.BLACK);
                chengshi.setTextColor(Color.RED);
                quxian.setTextColor(Color.BLACK);
            }
        });
        //选择城市
        city.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                List<MyadressBean.DataBean> cityList = map.get(curProvinceId); //key省份id
                MyadressBean.DataBean dataBean = cityList.get(index);
                if (dataBean == null) {
                    Toast.makeText(AddDiZhiActivity.this, "请稍后...", Toast.LENGTH_SHORT).show();
                } else {
                    curCityId = dataBean.getId();
                    dizhi[1] = dataBean.getName();
                    mPresenter.getAdress(curCityId);
                    area.setItems(new ArrayList<>());
                    chengshi.setText(dataBean.getName());
                    quxian.setText("区县");
                }
                shengfen.setTextColor(Color.BLACK);
                chengshi.setTextColor(Color.BLACK);
                quxian.setTextColor(Color.RED);
            }
        });
        //选择区县
        area.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                List<MyadressBean.DataBean> areaList = map.get(curCityId); //key省份id
                MyadressBean.DataBean dataBean = areaList.get(index);
                if (dataBean == null) {
                    Toast.makeText(AddDiZhiActivity.this, "请稍后...", Toast.LENGTH_SHORT).show();
                } else {
                    curAreaId = dataBean.getId();
                    dizhi[2] = dataBean.getName();
                    quxian.setText(dataBean.getName());
                }
                shengfen.setTextColor(Color.BLACK);
                chengshi.setTextColor(Color.BLACK);
                quxian.setTextColor(Color.RED);
            }
        });
        //初始化省份的数据
        List<MyadressBean.DataBean> pList = map.get(1);
        if (pList == null) return;
        List<String> adresses = getAdressStrings(pList);
        if (pList == null || adresses.size() == 0) {
            mPresenter.getAdress(1);
        } else {
            province.setItems(adresses);
            curProvinceId = pList.get(0).getId();
            shengfen.setText(adresses.get(0));
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

    public void bgAlpha(float bgalpha) {
        WindowManager.LayoutParams ab = getWindow().getAttributes();
        ab.alpha = bgalpha;
        getWindow().setAttributes(ab);
    }

    @Override
    public void getHomeResult(List<HomeBean.HomeListBean> result) {

    }

    @Override
    public void getGoodDetailResult(HomeGoodDetailBean result) {

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

    @Override
    public void addCartInfoReturn(ShoppAddBean result) {

    }

    //省份，城市，区县返回的数据
    @Override
    public void getAdressReturn(MyadressBean result) {
        List<MyadressBean.DataBean> list = null;
        int type = 0;
        for (MyadressBean.DataBean item : result.getData()) {
            int key = item.getParent_id();
            list = map.get(key);
            if (list == null) {
                list = new ArrayList<>();
                map.put(key, list);
            }
            boolean bool = hasList(item.getId(), list);
            if (!bool) list.add(item);
            if (type == 0) {
                type = item.getType();
            }
        }
        if (list == null) return;
        List<String> adresses = getAdressStrings(list);
        if (type == 1) {
            //刷新省的数据
            if (province != null) {
                curProvinceId = list.get(0).getId();
                shengfen.setText(list.get(0).getName());
                province.setItems(adresses);
            }
        } else if (type == 2) {
            //刷新市的数据
            if (city != null) {
                curCityId = list.get(0).getId();
                chengshi.setText(list.get(0).getName());
                city.setItems(adresses);
            }
        } else {
            //区
            if (area != null) {
                curAreaId = list.get(0).getId();
                quxian.setText(list.get(0).getName());
                area.setItems(adresses);
            }
        }
    }

    @Override
    public void getHomeNewTopResult(HomeNewTopBean result) {

    }

    @Override
    public void getHomeNewResult(HomeNewBean result) {

    }

    @Override
    public void getHomeZhiZaoResult(HomeZhiZhaoBean result) {

    }

    /**
     * 判断当前的地址列表中是否有这个地址
     *
     * @param id
     * @param list
     * @return
     */
    private boolean hasList(int id, List<MyadressBean.DataBean> list) {
        boolean bool = false;
        for (MyadressBean.DataBean item : list) {
            if (item.getId() == id) {
                bool = true;
                break;
            }
        }
        return bool;
    }

    /**
     * 提取省市区的名字
     *
     * @param list
     * @return
     */
    private List<String> getAdressStrings(List<MyadressBean.DataBean> list) {
        List<String> adresses = new ArrayList<>();
        for (MyadressBean.DataBean item : list) {
            adresses.add(item.getName());
        }
        return adresses;
    }

    /**
     * 通过id获取当前数据中的对象
     *
     * @param id
     * @param list
     * @return
     */
    private MyadressBean.DataBean getDataBeanById(int id, List<MyadressBean.DataBean> list) {
        for (MyadressBean.DataBean item : list) {
            if (item.getId() == id) return item;
        }
        return null;
    }
}