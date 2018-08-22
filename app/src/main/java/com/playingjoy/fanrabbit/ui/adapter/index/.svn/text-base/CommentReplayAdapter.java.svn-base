package com.playingjoy.fanrabbit.ui.adapter.index;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.playingjoy.fanrabbit.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;

/**
 * Author: Ly
 * Data：2018/3/30-17:53
 * Description: 评论的回复适配器
 */
public class CommentReplayAdapter extends SimpleRecAdapter<String, CommentReplayAdapter.CommentReplayHolder> {
    public CommentReplayAdapter(Context context) {
        super(context);
    }

    @Override
    public CommentReplayHolder newViewHolder(View itemView) {
        return new CommentReplayHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_comment_replay;
    }

    @Override
    public void onBindViewHolder(CommentReplayHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    static class CommentReplayHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_replay_name)
        TextView mTvReplayName;
        @BindView(R.id.tv_replay_str)
        TextView mTvReplayStr;

        CommentReplayHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
