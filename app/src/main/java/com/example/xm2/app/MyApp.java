package com.example.xm2.app;

import android.app.Application;
import android.content.Context;

import com.danikula.videocache.HttpProxyCacheServer;
import com.danikula.videocache.file.FileNameGenerator;

public class MyApp extends Application {
    public static Context app;
    private HttpProxyCacheServer proxy;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }
    public static HttpProxyCacheServer getProxy(Context context){
        MyApp myApp = (MyApp) context.getApplicationContext();
        return myApp.proxy == null ?(myApp.proxy = myApp.newProxy()):myApp.proxy;

    }

    private HttpProxyCacheServer newProxy() {
        return new HttpProxyCacheServer.Builder(this)
                .maxCacheSize(1024*1024*1024)
                .fileNameGenerator(new MyFileNameGenerator())
                .build();
    }

    private class MyFileNameGenerator implements FileNameGenerator {
        @Override
        public String generate(String url) {
            return url;
        }
    }
}
