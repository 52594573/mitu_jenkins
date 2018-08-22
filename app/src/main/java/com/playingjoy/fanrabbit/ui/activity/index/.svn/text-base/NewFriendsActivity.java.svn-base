package com.playingjoy.fanrabbit.ui.activity.index;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.ui.adapter.index.NewFriendsListAdapter;
import com.playingjoy.fanrabbit.ui.presenter.message.NewFriendsPresenter;
import com.playingjoy.fanrabbit.widget.FriendEmptyView;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;

/**
 * Author: Ly
 * Data：2018/3/27-17:58
 * Description: 新朋友界面
 */
public class NewFriendsActivity extends BaseActivity<NewFriendsPresenter> {
    @BindView(R.id.rlv_new_friends_list)
    XRecyclerContentLayout mRlvNewFriendsList;

    private NewFriendsListAdapter mNewFriendsListAdapter;
    private FriendEmptyView mFriendEmptyView;
    @Override
    public void initData(Bundle savedInstanceState) {
        setTitleBar(getString(R.string.text_new_friends));
        mFriendEmptyView= new FriendEmptyView(this);
        mRlvNewFriendsList.emptyView(mFriendEmptyView);

        mRlvNewFriendsList.getRecyclerView().setLayoutManager(new LinearLayoutManager(this));
        mNewFriendsListAdapter = new NewFriendsListAdapter(this);
        mRlvNewFriendsList.getRecyclerView().setAdapter(mNewFriendsListAdapter);
        mNewFriendsListAdapter.setRecItemClick(new RecyclerItemCallback<String, NewFriendsListAdapter.NewFriendsListViewHolder>() {
            @Override
            public void onItemClick(int position, String model, int tag, NewFriendsListAdapter.NewFriendsListViewHolder holder) {
                super.onItemClick(position, model, tag, holder);
                Toast.makeText(NewFriendsActivity.this, tag == 0 ? "删除" : "拒绝", Toast.LENGTH_SHORT).show();
            }
        });
        mRlvNewFriendsList.showEmpty();
        mFriendEmptyView.setOnClickListener(v -> mRlvNewFriendsList.showContent());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_new_friends;
    }

    @Override
    public NewFriendsPresenter newP() {
        return new NewFriendsPresenter();
    }

    public static void toNewFriendsActivity(Activity activity) {
        Router.newIntent(activity).to(NewFriendsActivity.class).launch();
    }
}
