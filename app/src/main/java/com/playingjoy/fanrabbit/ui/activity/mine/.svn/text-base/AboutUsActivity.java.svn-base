package com.playingjoy.fanrabbit.ui.activity.mine;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.ui.presenter.mine.AboutUsPresenter;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Author: Ly
 * Data：2018/4/10-15:09
 * Description: 关于我们
 */
public class AboutUsActivity extends BaseActivity<AboutUsPresenter> {
    @BindView(R.id.wv_web_page)
    WebView mWvWebPage;

    @Override
    public void initData(Bundle savedInstanceState) {
        setTitleBar(getString(R.string.text_about_us));
        mWvWebPage.getSettings().setJavaScriptEnabled(true);
        mWvWebPage.setWebChromeClient(new WebChromeClient());
        mWvWebPage.setWebViewClient(new WebViewClient());
        mWvWebPage.loadUrl("https://www.baidu.com");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    public AboutUsPresenter newP() {
        return new AboutUsPresenter();
    }


    public static void toAboutUsActivity(Activity activity){
        Router.newIntent(activity).to(AboutUsActivity.class).launch();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWvWebPage.loadUrl(null);
        mWvWebPage.destroy();
        mWvWebPage = null;
    }
}
