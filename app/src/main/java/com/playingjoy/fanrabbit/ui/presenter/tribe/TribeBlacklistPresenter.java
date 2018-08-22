package com.playingjoy.fanrabbit.ui.presenter.tribe;

import com.playingjoy.fanrabbit.base.BasePresenter;
import com.playingjoy.fanrabbit.ui.activity.tribe.TribeBlacklistActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 部落黑名单控制器
 *
 * @author Morphine
 * @date 2018-04-18.
 */

public class TribeBlacklistPresenter extends BasePresenter<TribeBlacklistActivity> {

    /**
     * 获取黑名单列表
     */
    public void getBlacklist() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("这是黑名单" + i);
        }
        getV().setBlacklist(list);
    }

    /**
     * 解除黑名单
     */
    public void relieveBlacklist() {
        getV().showTs("解除成功");
    }
}
