package com.playingjoy.fanrabbit.ui.activity.index;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.ui.adapter.index.GroupChatTipsListAdapter;
import com.playingjoy.fanrabbit.ui.presenter.message.MyGroupChatPresenter;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;

/**
 * Author: Ly
 * Data：2018/3/28-11:10
 * Description: 我的群聊界面
 */
public class MyGroupChatActivity extends BaseActivity<MyGroupChatPresenter> {
    @BindView(R.id.xlv_group_list)
    XRecyclerContentLayout mXlvGroupList;

    private GroupChatTipsListAdapter mGroupChatTipsListAdapter;
    @Override
    public void initData(Bundle savedInstanceState) {
        setTitleBar(getString(R.string.text_my_chat_group));
        setDefConfRecyclerView(mXlvGroupList);
        mXlvGroupList.getRecyclerView().setLayoutManager(new LinearLayoutManager(this));
        mGroupChatTipsListAdapter=new GroupChatTipsListAdapter(this);
        mXlvGroupList.getRecyclerView().setAdapter(mGroupChatTipsListAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_group_chat;
    }

    @Override
    public MyGroupChatPresenter newP() {
        return new MyGroupChatPresenter();
    }

    public static void toMyGroupChatActivity(Activity activity) {
        Router.newIntent(activity).to(MyGroupChatActivity.class).launch();
    }

}
