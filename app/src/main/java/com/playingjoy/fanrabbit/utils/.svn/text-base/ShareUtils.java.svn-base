package com.playingjoy.fanrabbit.utils;

import android.app.Activity;
import android.content.Context;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

/**
 * Created by Ly on 2018/3/20.
 * 分享工具类
 */

public class ShareUtils {

    private static ShareUtils instance = new ShareUtils();

    private ShareUtils() {

    }

    public static synchronized ShareUtils getInstance() {
        return instance;
    }

    /**
     * 分享web
     *
     * @param context
     * @param shareMedia
     * @param url        分享的url
     * @param title      分享的title
     * @param img        分享的图片地址
     * @param desc       分享的描述
     */
    public void shareWeb(Activity context, SHARE_MEDIA shareMedia, String url, String title, String img, String desc, UMShareListener umShareListener) {
        UMWeb web = new UMWeb(url);
        web.setTitle(title);
        web.setThumb(new UMImage(context, img));
        web.setDescription(desc);
        new ShareAction(context)
                .setPlatform(SHARE_MEDIA.WEIXIN)
                .withMedia(web)
                .setPlatform(shareMedia)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 判断是否安装
     *
     * @param activity
     * @param shareMedia
     * @return
     */
    public boolean isInstall(Activity activity, SHARE_MEDIA shareMedia) {
        return UMShareAPI.get(activity).isInstall(activity, shareMedia);
    }

//    eg:
//            ShareUtils.getInstance().shareWeb(getActivity(), SHARE_MEDIA.QZONE, "http://www.baidu.com", "这是分享的title",
//            "https://www.baidu.com/img/bd_logo1.png", "这是分享的描述", null);

}
