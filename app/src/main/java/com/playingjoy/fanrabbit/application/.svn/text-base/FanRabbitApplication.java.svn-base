package com.playingjoy.fanrabbit.application;

import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.connection.FileDownloadUrlConnection;
import com.playingjoy.fanrabbit.utils.db.DbHelper;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.NetProvider;
import cn.droidlover.xdroidmvp.net.RequestHandler;
import cn.droidlover.xdroidmvp.net.XApi;
import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * 作者：Ly
 * 时间：2018/3/9  10:45
 * 描述：这是一个类，用于全局的Application
 */

public class FanRabbitApplication extends MultiDexApplication {

    private static FanRabbitApplication sFanRabbitApplication;

    private static final String TAG = "FanRabbitApplication";


    static {
        PlatformConfig.setWeixin("wxfb1ca05fe8aa2dd2", "bcf2d2af831ab4fb4e17e6b86b6ac02f");
        PlatformConfig.setQQZone("1106747400", "DGT7M6CwTe78gDsy");
        PlatformConfig.setSinaWeibo("1553746033", "304b34e203fa9ec39749c487311cc464", "http://sns.whalecloud.com/sina2/callback");
        Config.DEBUG = false;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initDownLoadConf();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            //Android7.0拍照问题处理
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            builder.detectFileUriExposure();
        }
        UMShareAPI.get(this);
        sFanRabbitApplication = this;
        initMvp();
        initGreenDaoConf();
        initEM();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    private void initMvp() {
        XApi.registerProvider(new NetProvider() {
            @Override
            public Interceptor[] configInterceptors() {
                return new Interceptor[0];
            }

            @Override
            public void configHttps(OkHttpClient.Builder builder) {

            }

            @Override
            public CookieJar configCookie() {
                return null;
            }

            @Override
            public RequestHandler configHandler() {
                return new RequestHandler() {
                    @Override
                    public Request onBeforeRequest(Request request, Interceptor.Chain chain) {
                        if (request != null) {
                            XLog.json(request.toString());
                        }
                        return request;
                    }

                    @Override
                    public Response onAfterRequest(Response response, Interceptor.Chain chain) {
                        XLog.e(response.toString());
                        return response;
                    }
                };
            }

            @Override
            public long configConnectTimeoutMills() {
                return 0;
            }

            @Override
            public long configReadTimeoutMills() {
                return 0;
            }

            @Override
            public boolean configLogEnable() {
                return true;
            }

            @Override
            public boolean handleError(NetError error) {
                return false;
            }

            @Override
            public boolean dispatchProgressEnable() {
                return false;
            }
        });
    }

    /**
     * 初始化环信
     */
    private void initEM() {
        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
        // 是否自动将消息附件上传到环信服务器，默认为True是使用环信服务器上传下载，如果设为 false，需要开发者自己处理附件消息的上传和下载
        options.setAutoTransferMessageAttachments(true);
        // 是否自动下载附件类消息的缩略图等，默认为 true 这里和上边这个参数相关联
        options.setAutoDownloadThumbnail(true);
        //设置小米推送
        options.setMipushConfig("2882303761517766293", "5981776634293");
        options.setAppKey("morphine#xx");
        //初始化
        EaseUI.getInstance().init(getApplicationContext(), options);
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
//        EaseUI.getInstance().getEmClient().setDebugMode(true);
    }

    /**
     * 初始化下载
     */
    private void initDownLoadConf() {
        FileDownloader.setupOnApplicationOnCreate(this)
                .connectionCreator(new FileDownloadUrlConnection
                        .Creator(new FileDownloadUrlConnection.Configuration()
                        .connectTimeout(15_000) // set connection timeout.
                        .readTimeout(15_000) // set read timeout.
                ))
                .commit();
    }

    /**
     * 配置绿刀
     */
    private void initGreenDaoConf() {
        DbHelper.initDatabase(this);
    }
    public static FanRabbitApplication getInstance() {
        return sFanRabbitApplication;
    }
}
