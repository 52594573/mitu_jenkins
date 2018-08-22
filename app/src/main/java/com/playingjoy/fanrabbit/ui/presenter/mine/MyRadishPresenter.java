package com.playingjoy.fanrabbit.ui.presenter.mine;

import com.playingjoy.fanrabbit.base.BasePresenter;
import com.playingjoy.fanrabbit.ui.activity.mine.MyRadishActivity;

/**
 * 我的萝卜页面控制器
 *
 * @author Morphine
 * @date 2018-04-11.
 */

public class MyRadishPresenter extends BasePresenter<MyRadishActivity> {
    public void getRadishData() {

    }

    /**
     * 提现
     *
     * @param withdrawAmount
     */
    public void doWithdraw(int withdrawAmount) {
        getV().showTs("提现"+withdrawAmount);
    }
}
