package com.playingjoy.fanrabbit.ui.activity.chat;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.ui.presenter.chat.AddFriendPresenter;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * @author Morphine
 * @date 2018-04-10.
 * 添加好友申请页面
 */

public class AddFriendActivity extends BaseActivity<AddFriendPresenter> {
    @BindView(R.id.iv_avatar)
    ImageView mIvAvatar;
    @BindView(R.id.tv_user_name)
    TextView mTvUserName;
    @BindView(R.id.tv_user_level)
    TextView mTvUserLevel;
    @BindView(R.id.iv_user_sex)
    ImageView mIvUserSex;
    @BindView(R.id.et_apply_info)
    EditText mEtApplyInfo;
    @BindView(R.id.et_remark)
    EditText mEtRemark;

    @Override
    public void initData(Bundle savedInstanceState) {
        setTitleBarRightMsg(getString(R.string.add_friend), getString(R.string.text_send), v -> getP().sendFriendApply());
        initUserInfo();
    }

    /**
     * 初始化好友信息
     */
    private void initUserInfo() {

    }

    public static void toAddFriendActivity(Activity activity) {
        Router.newIntent(activity).to(AddFriendActivity.class).launch();
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_add_friend;
    }

    @Override
    public AddFriendPresenter newP() {
        return new AddFriendPresenter();
    }


}
