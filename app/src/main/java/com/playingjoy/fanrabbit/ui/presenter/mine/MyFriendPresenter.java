package com.playingjoy.fanrabbit.ui.presenter.mine;

import com.playingjoy.fanrabbit.base.BasePresenter;
import com.playingjoy.fanrabbit.ui.activity.mine.MyFriendActivity;

/**
 * 我的好友页面控制器
 *
 * @author Morphine
 * @date 2018-04-02.
 */

public class MyFriendPresenter extends BasePresenter<MyFriendActivity> {

    /**
     * 获取我的好友列表
     */
    public void getMyFriendList() {
        getV().setMyFriendListData();
    }
}
