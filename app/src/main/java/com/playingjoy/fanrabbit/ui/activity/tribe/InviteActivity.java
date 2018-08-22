package com.playingjoy.fanrabbit.ui.activity.tribe;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.ui.adapter.tribe.InviteMemberListAdapter;
import com.playingjoy.fanrabbit.ui.presenter.tribe.InvitePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * Created by Ly on 2018/4/19.
 * 邀请成员页面
 */

public class InviteActivity extends BaseActivity<InvitePresenter> {
    @BindView(R.id.rlv_invite_all_member)
    XRecyclerView rlvInviteAllMember;
    @BindView(R.id.ll_invite_recenter_container)
    LinearLayout llInviteRecenterContainer;
    @BindView(R.id.tv_recent_member_title)
    TextView mTvRecentMemberTitle;
    @BindView(R.id.iv_recent_member_status)
    ImageView mIvRecentMemberStatus;
    @BindView(R.id.ll_recent_member_title)
    LinearLayout mLlRecentMemberTitle;
    @BindView(R.id.rlv_invite_metoo_friends)
    RecyclerView mRlvInviteMetooFriends;
    @BindView(R.id.rlv_invite_recent_talk)
    RecyclerView mRlvInviteRecentTalk;
    @BindView(R.id.iv_mitoo_friends_status)
    ImageView mIvMitooFriendsStatus;
    @BindView(R.id.ll_mitoo_friends_title)
    LinearLayout mLlMitooFriendsTitle;
    @BindView(R.id.iv_recent_talk_status)
    ImageView mIvRecentTalkStatus;
    @BindView(R.id.ll_recent_talk_title)
    LinearLayout mLlRecentTalkTitle;
    /**
     * 全部好友列表的适配器
     */
    private InviteMemberListAdapter mAllMemberListAdapter;
    /**
     * 米兔好友列表
     */
    private InviteMemberListAdapter mMeTooFriendsAdapter;
    /**
     * 临时会话列表
     */
    private InviteMemberListAdapter mRecentTalkAdapter;

    @Override
    public void initData(Bundle savedInstanceState) {
        setTitleBar(getString(R.string.text_invite_some));
        initAllMemberList();
        initMeTooFriends();
        initRecentTalk();
    }

    /**
     * 临时会话列表
     */
    private void initRecentTalk() {
        mRecentTalkAdapter = new InviteMemberListAdapter(context);
        mRlvInviteRecentTalk.setLayoutManager(new LinearLayoutManager(context));
        mRlvInviteRecentTalk.setAdapter(mRecentTalkAdapter);
        mRlvInviteRecentTalk.setNestedScrollingEnabled(false);
    }

    /**
     * 米兔好友列表
     */
    private void initMeTooFriends() {
        mMeTooFriendsAdapter = new InviteMemberListAdapter(context);
        mRlvInviteMetooFriends.setLayoutManager(new LinearLayoutManager(context));
        mRlvInviteMetooFriends.setAdapter(mMeTooFriendsAdapter);
        mRlvInviteMetooFriends.setNestedScrollingEnabled(false);
    }

    /**
     * 全部成员列表
     */
    private void initAllMemberList() {
        mAllMemberListAdapter = new InviteMemberListAdapter(context);
        rlvInviteAllMember.setLayoutManager(new LinearLayoutManager(context));
        rlvInviteAllMember.setAdapter(mAllMemberListAdapter);
        rlvInviteAllMember.setNestedScrollingEnabled(false);
        initAllMemberHeader();
    }

    /**
     * 全部成员列表头部
     */
    private void initAllMemberHeader() {
        View headerAll = View.inflate(context, R.layout.header_invite_all, null);
        rlvInviteAllMember.setLayoutManager(new LinearLayoutManager(this));
        rlvInviteAllMember.addHeaderView(headerAll);
        LinearLayout llMemberList = headerAll.findViewById(R.id.ll_all_member_list);
        TextView tvMemberTitle = headerAll.findViewById(R.id.tv_all_member_title);
        ImageView ivMemberStatus = headerAll.findViewById(R.id.iv_all_member_status);
        llMemberList.setOnClickListener(v -> {
            if (mAllMemberListAdapter.isHide()) {
                ivMemberStatus.setImageResource(R.drawable.ic_drop_more);
                mAllMemberListAdapter.showData();
            } else {
                ivMemberStatus.setImageResource(R.drawable.ic_drop_down);
                mAllMemberListAdapter.hideData();
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_invite_some_one;
    }

    @Override
    public InvitePresenter newP() {
        return new InvitePresenter();
    }


    public static void toInviteActivity(Activity activity) {
        Router.newIntent(activity).to(InviteActivity.class).launch();
    }


    @OnClick({R.id.ll_mitoo_friends_title, R.id.ll_recent_talk_title, R.id.ll_recent_member_title, R.id.tv_invite_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_mitoo_friends_title:
                if (mMeTooFriendsAdapter.isHide()) {
                    mIvMitooFriendsStatus.setImageResource(R.drawable.ic_drop_more);
                    mMeTooFriendsAdapter.showData();
                } else {
                    mIvMitooFriendsStatus.setImageResource(R.drawable.ic_drop_down);
                    mMeTooFriendsAdapter.hideData();
                }
                break;
            case R.id.ll_recent_talk_title:
                if (mRecentTalkAdapter.isHide()) {
                    mIvRecentTalkStatus.setImageResource(R.drawable.ic_drop_more);
                    mRecentTalkAdapter.showData();
                } else {
                    mIvRecentTalkStatus.setImageResource(R.drawable.ic_drop_down);
                    mRecentTalkAdapter.hideData();
                }
                break;
            case R.id.ll_recent_member_title:
                if (llInviteRecenterContainer.getVisibility() == View.GONE) {
                    mIvRecentMemberStatus.setImageResource(R.drawable.ic_drop_more);
                    llInviteRecenterContainer.setVisibility(View.VISIBLE);
                } else {
                    mIvRecentMemberStatus.setImageResource(R.drawable.ic_drop_down);
                    llInviteRecenterContainer.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_invite_submit:
                break;
            default:
                break;
        }
    }
}
