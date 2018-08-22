package com.playingjoy.fanrabbit.ui.activity.mine;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.ui.activity.chat.ChatFriendActivity;
import com.playingjoy.fanrabbit.ui.adapter.mine.MyFriendListAdapter;
import com.playingjoy.fanrabbit.ui.presenter.mine.MyFriendPresenter;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;

/**
 * 我的好友页面
 *
 * @author Morphine
 * @date 2018-04-02.
 */

public class MyFriendActivity extends BaseActivity<MyFriendPresenter> {
    @BindView(R.id.xlv_my_friend_list)
    XRecyclerContentLayout mXlvMyFriendList;
    MyFriendListAdapter myFriendListAdapter;

    @Override
    public void initData(Bundle savedInstanceState) {
        setTitleBar(getString(R.string.text_my_friends));

        mXlvMyFriendList.getRecyclerView().setLayoutManager(new LinearLayoutManager(context));
        myFriendListAdapter = new MyFriendListAdapter(context);
        mXlvMyFriendList.getRecyclerView().setAdapter(myFriendListAdapter);
        myFriendListAdapter.setRecItemClick(new RecyclerItemCallback<String, MyFriendListAdapter.ViewHolder>() {
            @Override
            public void onItemClick(int position, String model, int tag, MyFriendListAdapter.ViewHolder holder) {
                ChatFriendActivity.toChatFriendActivity(MyFriendActivity.this);
            }
        });
        getP().getMyFriendList();
    }

    public void setMyFriendListData() {
        myFriendListAdapter.setData(new String[]{"张三", "李四", "王五", "赵六", "刘七", "孙八", "钱九", "奥奥啊啊啊啊啊啊啊啊", "吾问无为谓无无无无无无"});
    }

    public static void toMyFriendActivity(Activity activity) {
        Router.newIntent(activity).to(MyFriendActivity.class).launch();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_friend;
    }

    @Override
    public MyFriendPresenter newP() {
        return new MyFriendPresenter();
    }
}
