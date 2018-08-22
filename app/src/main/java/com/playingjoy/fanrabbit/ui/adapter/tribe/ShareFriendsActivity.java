package com.playingjoy.fanrabbit.ui.adapter.tribe;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.domain.impl.ShareMenuBean;
import com.playingjoy.fanrabbit.ui.dialog.ShareDialog;
import com.playingjoy.fanrabbit.ui.presenter.tribe.ShareFriendsPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Created by Ly on 2018/4/19.
 * 邀请加入
 */

public class ShareFriendsActivity extends BaseActivity<ShareFriendsPresenter> {
    @BindView(R.id.tv_invite_desc)
    TextView mTvInviteDesc;
    @BindView(R.id.tv_invite_share)
    TextView mTvInviteShare;

    @Override
    public void initData(Bundle savedInstanceState) {
        setTitleBar(getString(R.string.text_invite_join));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_share_friends;
    }

    @Override
    public ShareFriendsPresenter newP() {
        return new ShareFriendsPresenter();
    }

    public static void toInviteJoin(Activity a) {
        Router.newIntent(a).to(ShareFriendsActivity.class).launch();
    }


    @OnClick({R.id.tv_invite_desc, R.id.tv_invite_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_invite_desc:
                break;
            case R.id.tv_invite_share:
                ShareDialog.showDialog(ShareFriendsActivity.this, getString(R.string.text_share_to)).setOnItemClickListener((shareMenuBean, message) -> {

                });
                break;
        }
    }
}
