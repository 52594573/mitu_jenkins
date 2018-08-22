package com.playingjoy.fanrabbit.ui.activity.index;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.ui.activity.mine.MyFriendActivity;
import com.playingjoy.fanrabbit.ui.adapter.index.MessageFriendListAdapter;
import com.playingjoy.fanrabbit.ui.adapter.index.MessageGroupListAdapter;
import com.playingjoy.fanrabbit.ui.adapter.index.MessageMyListAdapter;
import com.playingjoy.fanrabbit.ui.presenter.message.MessageCenterPresenter;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;


/**
 * Author: Ly
 * Data：2018/3/27-10:24
 * Description: 消息中心
 */
public class MessageCenterActivity extends BaseActivity<MessageCenterPresenter> {
    @BindView(R.id.rlv_message_container)
    XRecyclerContentLayout mRlvMessageContainer;
    private MessageMyListAdapter mMessageMyListAdapter;
    /**
     * 头布局
     */
    private View mHeaderView;
    /**
     * 头布局里面的好友消息列表
     */
    private RecyclerView mRlvMessageFriendsList;
    /**
     * 头布局里面的群聊消息列表
     */
    private RecyclerView mRlvMessageGroupList;
    /**
     * 显示有新朋友的LinearLayout
     */
    private LinearLayout mLlMessageFriendsNew;
    /**
     * 显示群聊消息的LinearLayout
     */
    private TextView mTvMessageGroupList;
    MessageFriendListAdapter messageFriendListAdapter;

    @Override
    public void initData(Bundle savedInstanceState) {
        setTitleBar(getString(R.string.text_message_center));
        initHeaderView();
        initMessageList();

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_center_message;
    }

    @Override
    public MessageCenterPresenter newP() {
        return new MessageCenterPresenter();
    }


    public static void toMessageCenter(Activity activity) {
        Router.newIntent(activity).to(MessageCenterActivity.class).launch();
    }


    /**
     * 初始化消息布局
     */
    private void initMessageList() {
        mRlvMessageContainer.getRecyclerView().setLayoutManager(new LinearLayoutManager(this));
        mMessageMyListAdapter = new MessageMyListAdapter(this);
        mRlvMessageContainer.getRecyclerView().setAdapter(mMessageMyListAdapter);
        mRlvMessageContainer.getRecyclerView().addHeaderView(mHeaderView);
        mMessageMyListAdapter.setRecItemClick(new RecyclerItemCallback<String, MessageMyListAdapter.MessageMyListHolder>() {
            @Override
            public void onItemClick(int position, String model, int tag, MessageMyListAdapter.MessageMyListHolder holder) {
                super.onItemClick(position, model, tag, holder);
                /**
                 * 点击事件的flag CLICK_TOPIC 进入话题  CLICK_ACTIVITY 进入活动  CLICK_INFO 进入查看用户信息  CLICK_TRIBE 进入部落
                 private static final int CLICK_TOPIC = 0, CLICK_ACTIVITY = 1, CLICK_INFO = 2, CLICK_TRIBE = 3;
                 */
                switch (tag) {
                    case 0:
                        Toast.makeText(MessageCenterActivity.this, "进入话题", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(MessageCenterActivity.this, "进入活动", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(MessageCenterActivity.this, "进入查看用户信息", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(MessageCenterActivity.this, "进入部落", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * 初始化头布局
     */
    private void initHeaderView() {
        mHeaderView = View.inflate(this, R.layout.header_message_center, null);
        mRlvMessageFriendsList = mHeaderView.findViewById(R.id.rlv_message_friends_list);
        mRlvMessageGroupList = mHeaderView.findViewById(R.id.rlv_message_group_list);
        mLlMessageFriendsNew = mHeaderView.findViewById(R.id.ll_message_new_friends);
        mTvMessageGroupList = mHeaderView.findViewById(R.id.tv_message_my_group);

        LinearLayoutManager llForUnReadMessage = new LinearLayoutManager(this);
        llForUnReadMessage.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRlvMessageFriendsList.setLayoutManager(llForUnReadMessage);
        mRlvMessageGroupList.setLayoutManager(new LinearLayoutManager(this));
        messageFriendListAdapter = new MessageFriendListAdapter(this);
        mRlvMessageFriendsList.setAdapter(messageFriendListAdapter);
        mRlvMessageGroupList.setAdapter(new MessageGroupListAdapter(this));
        mLlMessageFriendsNew.setOnClickListener(v -> NewFriendsActivity.toNewFriendsActivity(MessageCenterActivity.this));
        mTvMessageGroupList.setOnClickListener(v -> MyGroupChatActivity.toMyGroupChatActivity(MessageCenterActivity.this));

        messageFriendListAdapter.setRecItemClick(new RecyclerItemCallback<String, MessageFriendListAdapter.MessageFriendListHolder>() {
            @Override
            public void onItemClick(int position, String model, int tag, MessageFriendListAdapter.MessageFriendListHolder holder) {
                MyFriendActivity.toMyFriendActivity(MessageCenterActivity.this);
            }
        });
    }

}
