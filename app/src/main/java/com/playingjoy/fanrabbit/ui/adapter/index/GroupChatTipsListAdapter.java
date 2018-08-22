package com.playingjoy.fanrabbit.ui.adapter.index;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.playingjoy.fanrabbit.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;

/**
 * Author: Ly
 * Data：2018/3/27-18:07
 * Description: 群聊提示列表
 */
public class GroupChatTipsListAdapter extends SimpleRecAdapter<String, GroupChatTipsListAdapter.NewFriendsListViewHolder> {
    public GroupChatTipsListAdapter(Context context) {
        super(context);
    }

    @Override
    public NewFriendsListViewHolder newViewHolder(View itemView) {
        return new NewFriendsListViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_group_chat_list;
    }

    @Override
    public void onBindViewHolder(NewFriendsListViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 10;
    }


    public  static class NewFriendsListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_group_pic)
        ImageView mIvGroupPic;
        @BindView(R.id.tv_group_name)
        TextView mTvGroupName;
        @BindView(R.id.tv_group_from)
        TextView mTvGroupFrom;
        @BindView(R.id.tv_group_desc)
        TextView mTvGroupDesc;
        @BindView(R.id.tv_group_un_read_num)
        TextView mTvGroupUnReadNum;
        @BindView(R.id.content)
        LinearLayout mContent;
        @BindView(R.id.tv_group_refuse)
        TextView mTvGroupRefuse;
        @BindView(R.id.tv_group_delete)
        TextView mTvGroupDelete;
        @BindView(R.id.ll_group_right_menu)
        LinearLayout mLlGroupRightMenu;

        NewFriendsListViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
