package com.example.xm2.model;

import android.util.Log;


import com.example.xm2.base.ConStants;
import com.example.xm2.model.classifyapi.ClassifyApi;
import com.example.xm2.model.homeapi.HomeApi;
import com.example.xm2.model.shoppapi.ShoppApi;
import com.example.xm2.utiles.DigestUtils;
import com.example.xm2.utiles.SpUtils;
import com.example.xm2.utiles.SystemUtils;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * http协议
 * 负责网络请求
 */
public class HttpManager {
    private static HttpManager instance;

    public static HttpManager getInstance() {
        if (instance == null) {
            synchronized (HttpManager.class) {
                if (instance == null) {
                    instance = new HttpManager();
                }
            }
        }
        return instance;
    }

    /**
     * 网络请求i接口
     */
    HomeApi homeApi;

    private static Retrofit getRetrofit(String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }

    private static OkHttpClient getOkHttpClient() {
        //定义本地缓存文件
//        File file = new File(ConStants.PATH_CACHE);
        //cache缓存文件的操作
//        Cache cache = new Cache(file, 1024 * 1204 * 100);
        OkHttpClient client = new OkHttpClient.Builder()
//                .cache(cache)
                .addInterceptor(new LoggingInterceptor())
                .addInterceptor(new HeaderInterceptor())
                .addNetworkInterceptor(new NetInterceptor())
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        return client;
    }

    /**
     * 日志的拦截器打印报文信息
     */
    static class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
            Log.i("interceptor", String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            Log.i("Received:", String.format("Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers()));
            if (response.header("session_id") != null) {
                //Constant.session_id = response.header("session_id");
            }
            return response;
        }
    }

    private static class NetInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!SystemUtils.checkNetWork()) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response response = chain.proceed(request);
            //通过判断网络连接是否存在获取本地或者服务器的数据
            if (!SystemUtils.checkNetWork()) {
                int maxAge = 0;
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public ,max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28;//设置缓存数据的保存时间
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, onlyif-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    }

    /**
     * 获取网络接口请求的接口类
     *
     * @return
     */
    public HomeApi getHomeApi() {
        if (homeApi == null) {
            homeApi = getRetrofit(ConStants.Base_HomeUrl).create(HomeApi.class);
        }
        return homeApi;
    }

    private ShoppApi shoppApi;

    public ShoppApi getShoppApi() {
        if (shoppApi == null) {
            shoppApi = getRetrofit(ConStants.Base_HomeUrl).create(ShoppApi.class);
        }
        return shoppApi;
    }

    private ClassifyApi classifyApi;

    public ClassifyApi getClassifyApi() {
        if (classifyApi == null) {
            classifyApi = getRetrofit(ConStants.Base_HomeUrl).create(ClassifyApi.class);
        }
        return classifyApi;
    }

    /**
     * 添加头拦截器
     */
    //需要头参数的接口
    private static String[] outHeaderUrl = {"user/login", "user/register"};
    private static String signUrl = "cdwan.cn"; //需要签名的域名

    static class HeaderInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            String url = chain.request().url().toString();
            String host = chain.request().url().host();
            //判断对应的url接口是否需要添加头信息
            boolean bool = false;
            for (int i = 0; i < outHeaderUrl.length; i++) {
                if (url.indexOf(outHeaderUrl[i]) != -1) {
                    bool = true;
                    break;
                }
            }
            if (signUrl.equals(host) && !bool) {
                String token = SpUtils.getInstance().getString("token");
                //签名处理
                int timestamp = (int) (new Date().getTime() / 1000);
                //随机字符
                String nonce = UUID.randomUUID().toString();
                //保存需要签名验证的参数
                //消息头
                Map<String, String> map = new HashMap<>();
                map.put("timestamp", String.valueOf(timestamp));
                map.put("nonce", nonce);
                map.put("token", token);

                //地址中的参数获取
                HttpUrl.Builder urlBuilder = chain.request().url().newBuilder();
                //获取get请求的地址参数
                Set<String> paramKeys = urlBuilder.build().queryParameterNames();
                for (String key : paramKeys) {
                    String value = urlBuilder.build().queryParameter(key);
                    map.put(key, URLDecoder.decode(value));
                }

                //如果当前是post请求
                if ("POST".equals(chain.request().method())) {
                    //获取当前网络请求中的参数和值
                    FormBody formBody = (FormBody) chain.request().body();
                    if (formBody != null) {
                        for (int i = 0; i < formBody.size(); i++) {
                            map.put(formBody.encodedName(i), URLDecoder.decode(formBody.encodedValue(i)));
                        }
                    }
                }

                //获取需要签名的参数字符串
                String source = DigestUtils.getSignSource(map);
                //通过参数字符串获取对应的签名
                String sign = DigestUtils.Encrypt(source, null);
                //获取请求对象
                Request.Builder builder = chain.request().newBuilder();
                builder.addHeader("token", token);
                builder.addHeader("sign", sign);
                builder.addHeader("timestamp", String.valueOf(timestamp));
                builder.addHeader("nonce", nonce);
                Request request = builder.build();
                Response response = chain.proceed(request);
                //Log.i("response:",response.body().string());
                return response;
            } else {
                return chain.proceed(chain.request());
            }
        }
    }
}
