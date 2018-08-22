package com.playingjoy.fanrabbit.ui.presenter.tribe;

import com.playingjoy.fanrabbit.base.BasePresenter;
import com.playingjoy.fanrabbit.ui.activity.tribe.TribeBlacklistSearchActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 部落黑名单搜索控制器
 *
 * @author Morphine
 * @date 2018-04-18.
 */

public class TribeBlacklistSearchPresenter extends BasePresenter<TribeBlacklistSearchActivity> {

    /**
     * 搜索黑名单
     *
     * @param keyword
     */
    public void searchBlacklist(String keyword) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("这是搜索的黑名单" + i);
        }
        getV().setSearchResult(list);
    }

    /**
     * 解除黑名单
     */
    public void relieveBlacklist() {
        getV().showTs("解除成功");
    }
}
