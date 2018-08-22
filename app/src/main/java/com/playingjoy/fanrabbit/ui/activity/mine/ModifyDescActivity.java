package com.playingjoy.fanrabbit.ui.activity.mine;

import android.app.Activity;
import android.os.Bundle;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.ui.presenter.mine.ModifyDescPresenter;

import cn.droidlover.xdroidmvp.router.Router;

/**
 * Author: Ly
 * Data：2018/4/8-9:52
 * Description: 个人简介页面
 */
public class ModifyDescActivity extends BaseActivity<ModifyDescPresenter> {
    @Override
    public void initData(Bundle savedInstanceState) {
        setTitleBarRightMsg(getString(R.string.text_desc), getString(R.string.save), v -> {
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_modify_desc;
    }

    @Override
    public ModifyDescPresenter newP() {
        return new ModifyDescPresenter();
    }


    public static void toModifyDescActivity(Activity activity, String defStr) {
        Router.newIntent(activity).putString("defStr", defStr).to(ModifyDescActivity.class).launch();
    }
}
