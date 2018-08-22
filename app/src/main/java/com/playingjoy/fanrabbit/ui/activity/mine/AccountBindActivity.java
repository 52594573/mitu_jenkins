package com.playingjoy.fanrabbit.ui.activity.mine;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.ui.activity.login.RegisterActivity;
import com.playingjoy.fanrabbit.ui.presenter.mine.AccountBindPresenter;
import com.playingjoy.fanrabbit.utils.GiftsDialogUtil;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Author: Ly
 * Data：2018/4/9-14:42
 * Description: 账号绑定
 */
public class AccountBindActivity extends BaseActivity<AccountBindPresenter> {
    @BindView(R.id.tv_account_phone)
    TextView mTvAccountPhone;
    @BindView(R.id.rl_account_phone)
    RelativeLayout mRlAccountPhone;
    @BindView(R.id.tv_account_wechat)
    TextView mTvAccountWechat;
    @BindView(R.id.rl_account_wechat)
    RelativeLayout mRlAccountWechat;
    @BindView(R.id.tv_account_sina)
    TextView mTvAccountSina;
    @BindView(R.id.rl_account_sina)
    RelativeLayout mRlAccountSina;
    @BindView(R.id.tv_account_qq)
    TextView mTvAccountQq;
    @BindView(R.id.rl_account_qq)
    RelativeLayout mRlAccountQq;


    private GiftsDialogUtil mGiftsDialogUtil;

    @Override
    public void initData(Bundle savedInstanceState) {
        setTitleBar(getString(R.string.text_account_bind));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activtity_account_binding;
    }

    @Override
    public AccountBindPresenter newP() {
        return new AccountBindPresenter();
    }


    @OnClick({R.id.rl_account_phone, R.id.rl_account_wechat, R.id.rl_account_sina, R.id.rl_account_qq})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_account_phone:
                RegisterActivity.toRegisterActivity(this, 4);
                break;
            case R.id.rl_account_wechat:
                if (mGiftsDialogUtil == null) {
                    mGiftsDialogUtil = new GiftsDialogUtil();
                }
                String title = String.format(getString(R.string.text_unbind_account), "微信");
                String container = String.format(getString(R.string.text_only_bind), "微信");
                String btnMsg = getString(R.string.text_i_see);
                mGiftsDialogUtil.getAccountBindDialog(AccountBindActivity.this,title,
                        container, btnMsg, v -> {

                        }).show();
                break;
            case R.id.rl_account_sina:
                break;
            case R.id.rl_account_qq:
                break;
        }
    }

    public static void toAccountBindActivity(Activity activity) {
        Router.newIntent(activity).to(AccountBindActivity.class).launch();
    }
}
