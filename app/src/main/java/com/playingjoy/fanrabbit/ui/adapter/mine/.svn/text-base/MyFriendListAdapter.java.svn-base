package com.playingjoy.fanrabbit.ui.adapter.mine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.playingjoy.fanrabbit.R;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * @author Morphine
 * @date 2018-04-02.
 */

public class MyFriendListAdapter extends SimpleRecAdapter<String, MyFriendListAdapter.ViewHolder> {


    public MyFriendListAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_my_friend_lis;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTvFriendName.setText(data.get(position));
        Glide.with(context).load(R.drawable.game_icon)
                .apply(new RequestOptions().circleCrop())
                .into(holder.mIvFriendAvatar);
        holder.itemView.setOnClickListener(v -> {
            if (getRecItemClick() != null) {
                getRecItemClick().onItemClick(position, data.get(position), 0, holder);
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_friend_avatar)
        ImageView mIvFriendAvatar;
        @BindView(R.id.tv_un_read_num)
        TextView mTvUnReadNum;
        @BindView(R.id.tv_friend_name)
        TextView mTvFriendName;
        @BindView(R.id.tv_last_message)
        TextView mTvLastMessage;
        @BindView(R.id.tv_last_message_time)
        TextView mTvLastMessageTime;

        public ViewHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }
}
