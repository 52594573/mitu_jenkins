package com.playingjoy.fanrabbit.ui.adapter.index;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.playingjoy.fanrabbit.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;

/**
 * Author: Ly
 * Data：2018/3/27-18:07
 * Description: 新朋友列表
 */
public class NewFriendsListAdapter extends SimpleRecAdapter<String, NewFriendsListAdapter.NewFriendsListViewHolder> {
    public NewFriendsListAdapter(Context context) {
        super(context);
    }

    @Override
    public NewFriendsListViewHolder newViewHolder(View itemView) {
        return new NewFriendsListViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_new_friends_apply;
    }

    @Override
    public void onBindViewHolder(NewFriendsListViewHolder holder, int position) {
        holder.mTvFriendsDelete.setOnClickListener(v -> {
            if (getRecItemClick()!=null){
                getRecItemClick().onItemClick(position,null,0,holder);
            }
        });
        holder.mTvFriendsRefuse.setOnClickListener(v -> {
            if (getRecItemClick()!=null){
                getRecItemClick().onItemClick(position,null,1,holder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    public  static class NewFriendsListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_friends_pic)
        ImageView mIvFriendsPic;
        @BindView(R.id.tv_friends_name)
        TextView mTvFriendsName;
        @BindView(R.id.tv_friends_from)
        TextView mTvFriendsFrom;
        @BindView(R.id.tv_friends_desc)
        TextView mTvFriendsDesc;
        @BindView(R.id.tv_friends_agree)
        TextView mTvFriendsAgree;
        @BindView(R.id.tv_friends_refuse)
        TextView mTvFriendsRefuse;
        @BindView(R.id.tv_friends_delete)
        TextView mTvFriendsDelete;

        NewFriendsListViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
