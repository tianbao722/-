package com.example.xm2.app;

import java.io.File;

public class ConStants {
    public static final String Base_YunUrl = "http://cdwan.cn:7000/tongpao/";
    public static final String Base_UserUrl = "http://cdwan.cn:9001/"; //用户信息相关的基础地址

    public static final String Base_UploadUrl = "http://yun918.cn/study/public/";  //资源上传的基础地址
    //网络缓存地址
    public static final String PATH_DATA = MyApp.app.getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/XM";
}
