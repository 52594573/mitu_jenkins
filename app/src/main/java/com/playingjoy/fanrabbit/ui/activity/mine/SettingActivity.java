package com.playingjoy.fanrabbit.ui.activity.mine;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.domain.impl.ShareMenuBean;
import com.playingjoy.fanrabbit.ui.dialog.ShareDialog;
import com.playingjoy.fanrabbit.ui.presenter.mine.SettingPresenter;
import com.playingjoy.fanrabbit.utils.GiftsDialogUtil;
import com.playingjoy.fanrabbit.utils.ShareUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Author: Ly
 * Data：2018/4/10-11:33
 * Description: 设置页面
 */
public class SettingActivity extends BaseActivity<SettingPresenter> {
    @BindView(R.id.sw_settings_notification)
    Switch mSwSettingsNotification;
    @BindView(R.id.rl_settings_notification)
    RelativeLayout mRlSettingsNotification;
    @BindView(R.id.rl_settings_share)
    RelativeLayout mRlSettingsShare;
    @BindView(R.id.rl_settings_check)
    RelativeLayout mRlSettingsCheck;
    @BindView(R.id.rl_settings_about)
    RelativeLayout mRlSettingsAbout;

    private GiftsDialogUtil mGiftsDialogUtil;

    @Override
    public void initData(Bundle savedInstanceState) {
        setTitleBar(getString(R.string.text_settings));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_settings;
    }

    @Override
    public SettingPresenter newP() {
        return new SettingPresenter();
    }

    public static void toSettingActivity(Activity activity) {
        Router.newIntent(activity).to(SettingActivity.class).launch();
    }


    @OnClick({R.id.rl_settings_share, R.id.rl_settings_check, R.id.rl_settings_about, R.id.tv_settings_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_settings_share:
                ShareDialog.showDialog(this, getString(R.string.text_share_to)).setOnItemClickListener((shareMenuBean, message) -> {

                });
                break;
            case R.id.rl_settings_check:
                showHaveUpdateDialog();
                break;
            case R.id.rl_settings_about:
                AboutUsActivity.toAboutUsActivity(context);
                break;
            case R.id.tv_settings_logout:
                showLogOutDialog();
                break;
        }
    }

    /**
     * 已经是最新，没得更新了的dialog
     */
    private void showNoUpdateDialog() {
        View viewUpdate = LayoutInflater.from(this).inflate(R.layout.view_update_no_update_dialog, null);
        TextView tvVersion = viewUpdate.findViewById(R.id.tv_version);
        tvVersion.setText(String.format(getString(R.string.text_metoo_version), Kits.Package.getVersionName(this)));
        getGiftsDialogUtil().getUpdateDialog(this, getString(R.string.text_new_version), viewUpdate, getString(R.string.text_i_see),
                v -> {

                }).show();
    }

    /**
     * 还有得更新
     */
    private void showHaveUpdateDialog() {
        View viewUpdate = LayoutInflater.from(this).inflate(R.layout.view_update_have_update_dialog, null);
        TextView tvVersion = viewUpdate.findViewById(R.id.tv_version);
        tvVersion.setText(String.format(getString(R.string.text_metoo_version), Kits.Package.getVersionName(this)));
        getGiftsDialogUtil().getUpdateDialog(this, getString(R.string.text_new_version), viewUpdate, getString(R.string.text_update_now),
                v -> {

                }).show();
    }

    /**
     * 显示退出登录dialog
     */
    private void showLogOutDialog() {
        getGiftsDialogUtil().getAccountBindDialog(this, getString(R.string.text_logout), "退出迷兔后，你将收不到来自迷兔的 消息",
                getString(R.string.text_submit), v -> {
                }).show();
    }

    /**
     * 获取mGiftsDialogUtil
     *
     * @return
     */
    private GiftsDialogUtil getGiftsDialogUtil() {
        if (mGiftsDialogUtil == null) {
            mGiftsDialogUtil = new GiftsDialogUtil();
        }
        return mGiftsDialogUtil;
    }
}
