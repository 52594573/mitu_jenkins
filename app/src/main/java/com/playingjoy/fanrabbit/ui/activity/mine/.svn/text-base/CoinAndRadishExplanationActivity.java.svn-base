package com.playingjoy.fanrabbit.ui.activity.mine;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.ui.presenter.mine.CoinAndRadishExplanationPresenter;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * 兔币和萝卜说明页面
 *
 * @author Morphine
 * @date 2018-04-11.
 */

public class CoinAndRadishExplanationActivity extends BaseActivity<CoinAndRadishExplanationPresenter> {
    /**
     * 兔币说明页面标识
     */
    public static final int FLAG_COIN_EXPLANATION = 1;

    /**
     * 萝卜说明页面标识
     */
    public static final int FLAG_RADISH_EXPLANATION = 2;


    @BindView(R.id.wv_web_page)
    WebView mWvWebPage;

    @Override
    public void initData(Bundle savedInstanceState) {
        int flag = getIntent().getIntExtra("flag", FLAG_COIN_EXPLANATION);
        setTitleBar(getString(flag == FLAG_COIN_EXPLANATION ? R.string.text_coin_explanation : R.string.text_radish_explanation));
        getP().getExplanationData();
    }

    /**
     * @param activity
     * @param flag     跳转标识
     */
    public static void toCoinAndRadishExplanationActivity(Activity activity, int flag) {
        Router.newIntent(activity).putInt("flag", flag).to(CoinAndRadishExplanationActivity.class).launch();
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    public CoinAndRadishExplanationPresenter newP() {
        return new CoinAndRadishExplanationPresenter();
    }

}
