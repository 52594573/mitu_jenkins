package com.playingjoy.fanrabbit.ui.adapter.index;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
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
 * Data：2018/3/29-11:08
 * Description: 游戏详情 fragment里面的评论适配器
 */
public class GameDetailCommentAdapter extends SimpleRecAdapter<String, GameDetailCommentAdapter.GameDetailCommentHolder> {
    public GameDetailCommentAdapter(Context context) {
        super(context);
    }

    @Override
    public GameDetailCommentHolder newViewHolder(View itemView) {
        return new GameDetailCommentHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_game_detail_comment;
    }

    @Override
    public void onBindViewHolder(GameDetailCommentHolder holder, int position) {
        holder.mRlvDetailComment.setLayoutManager(new LinearLayoutManager(context));
        holder.mRlvDetailComment.setAdapter(new CommentReplayAdapter(context));
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public static class GameDetailCommentHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_detail_comment_pic)
        ImageView mIvDetailCommentPic;
        @BindView(R.id.tv_detail_comment_name)
        TextView mTvDetailCommentName;
        @BindView(R.id.tv_detail_like_name)
        TextView mTvDetailLikeName;
        @BindView(R.id.tv_detail_comment_date)
        TextView mTvDetailCommentDate;
        @BindView(R.id.tv_detail_comment_desc)
        TextView mTvDetailCommentDesc;
        @BindView(R.id.rlv_detail_comment_replay)
        RecyclerView mRlvDetailComment;

        GameDetailCommentHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
