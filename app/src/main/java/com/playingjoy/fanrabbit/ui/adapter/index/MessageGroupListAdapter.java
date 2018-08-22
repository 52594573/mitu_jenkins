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
 * Description: 消息中心  群聊未读消息适配器
 */
public class MessageGroupListAdapter extends SimpleRecAdapter<String, MessageGroupListAdapter.MessageGroupListHolder> {
    public MessageGroupListAdapter(Context context) {
        super(context);
    }

    @Override
    public MessageGroupListHolder newViewHolder(View itemView) {
        return new MessageGroupListHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_message_group_un_read;
    }

    @Override
    public void onBindViewHolder(MessageGroupListHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }



    public static class MessageGroupListHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_un_read_pic)
        ImageView mTvUnReadPic;
        @BindView(R.id.tv_un_read_name)
        TextView mTvUnReadName;
        @BindView(R.id.tv_un_read_online)
        TextView mTvUnReadOnline;
        @BindView(R.id.tv_un_read_total)
        TextView mTvUnReadTotal;
        @BindView(R.id.tv_un_read_num)
        TextView mTvUnReadNum;

        MessageGroupListHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
