package com.playingjoy.fanrabbit.ui.activity.mine;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.ui.presenter.mine.ModifyNickNamePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Author: Ly
 * Data：2018/4/4-18:24
 * Description: 修改用户昵称的Activity
 */
public class ModifyNickNameActivity extends BaseActivity<ModifyNickNamePresenter> {
    @BindView(R.id.et_nickname_str)
    EditText mEtNicknameStr;

    @Override
    public void initData(Bundle savedInstanceState) {
        setTitleBarRightMsg(getString(R.string.text_nickname), getString(R.string.text_save), v -> {
            // 保存
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_modify_nick_name;
    }

    @Override
    public ModifyNickNamePresenter newP() {
        return new ModifyNickNamePresenter();
    }

    /**
     * 修改用户名
     *
     * @param activity
     * @param defNickName
     */
    public static void toModifyUserNickNameActivity(Activity activity, String defNickName) {
        Router.newIntent(activity).putString("def_nickName", defNickName).to(ModifyNickNameActivity.class).launch();
    }


    @OnClick(R.id.iv_nickname_delete)
    public void onViewClicked() {
        mEtNicknameStr.setText(null);
    }
}
