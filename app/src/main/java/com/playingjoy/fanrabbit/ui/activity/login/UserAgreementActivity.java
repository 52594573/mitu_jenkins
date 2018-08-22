package com.playingjoy.fanrabbit.ui.activity.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.ui.presenter.login.UserAgreementPresenter;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Author: Ly
 * Data：2018/3/22-14:43
 * Description:
 */
public class UserAgreementActivity extends BaseActivity<UserAgreementPresenter> {
    @BindView(R.id.wv_web_page)
    WebView mWvWebPage;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void initData(Bundle savedInstanceState) {
        setTitleBar(getString(R.string.text_agreement));
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
    public UserAgreementPresenter newP() {
        return new UserAgreementPresenter();
    }

    public static void toUserAgreementActivity(Activity activity) {
        Router.newIntent(activity).to(UserAgreementActivity.class).launch();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWvWebPage.loadUrl(null);
        mWvWebPage.destroy();
        mWvWebPage = null;
    }
}
