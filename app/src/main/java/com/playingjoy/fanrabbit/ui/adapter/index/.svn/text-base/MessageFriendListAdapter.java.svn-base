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
 * Data：2018/3/27-11:24
 * Description: 消息中心  好友未读消息适配器
 */
public class MessageFriendListAdapter extends SimpleRecAdapter<String, MessageFriendListAdapter.MessageFriendListHolder> {
    public MessageFriendListAdapter(Context context) {
        super(context);
    }

    @Override
    public MessageFriendListHolder newViewHolder(View itemView) {
        return new MessageFriendListHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_message_friends_un_read;
    }

    @Override
    public void onBindViewHolder(MessageFriendListHolder holder, int position) {
        holder.itemView.setOnClickListener(v -> {
            if (getRecItemClick() != null) {
                getRecItemClick().onItemClick(position, "", 0, holder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public static class MessageFriendListHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_un_read_pic)
        ImageView mIvUnReadPic;
        @BindView(R.id.tv_un_read_num)
        TextView mTvUnReadNum;
        @BindView(R.id.tv_un_read_name)
        TextView mTvUnReadName;

        MessageFriendListHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
