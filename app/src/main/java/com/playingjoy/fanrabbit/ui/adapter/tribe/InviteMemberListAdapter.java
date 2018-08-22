package com.playingjoy.fanrabbit.ui.adapter.tribe;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.playingjoy.fanrabbit.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Ly on 2018/4/19.
 * 邀请成员的时候，成员列表的适配器
 */

public class InviteMemberListAdapter extends SimpleRecAdapter<String, InviteMemberListAdapter.InviteMemberListHolder> {

    private int count = 0;

    public void hideData() {
        count = 0;
        notifyDataSetChanged();
    }

    public void showData() {
        count = 20;
        notifyDataSetChanged();
    }

    public boolean isHide() {
        return count == 0;
    }

    public InviteMemberListAdapter(Context context) {
        super(context);
    }

    @Override
    public InviteMemberListHolder newViewHolder(View itemView) {
        return new InviteMemberListHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_invite_member_list;
    }

    @Override
    public void onBindViewHolder(InviteMemberListHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return count;
    }

    static class InviteMemberListHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_member_list_pic)
        CircleImageView mIvMemberListPic;
        @BindView(R.id.iv_member_list_post)
        ImageView mIvMemberListPost;
        @BindView(R.id.tv_member_list_name)
        TextView mTvMemberListName;
        @BindView(R.id.tv_member_list_lv)
        TextView mTvMemberListLv;
        @BindView(R.id.tv_member_list_job)
        TextView mTvMemberListJob;
        @BindView(R.id.iv_member_list_sex)
        ImageView mIvMemberListSex;
        @BindView(R.id.tv_member_list_desc)
        TextView mTvMemberListDesc;
        @BindView(R.id.cb_member_list_check_status)
        CheckBox mCbMemberListCheckStatus;

        InviteMemberListHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
