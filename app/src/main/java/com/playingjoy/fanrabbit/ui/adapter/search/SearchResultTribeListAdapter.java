package com.playingjoy.fanrabbit.ui.adapter.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.playingjoy.fanrabbit.R;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * @author Morphine
 * @date 2018-04-10.
 * 搜索结果部落列表适配器
 */

public class SearchResultTribeListAdapter extends SimpleRecAdapter<String, SearchResultTribeListAdapter.ViewHolder> {


    public SearchResultTribeListAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_search_result_tribe_list;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_tribe_pic)
        ImageView mIvTribePic;
        @BindView(R.id.tv_tribe_name)
        TextView mTvTribeName;
        @BindView(R.id.rb_tribe_rating)
        RatingBar mRbTribeRating;
        @BindView(R.id.rl_name_layout)
        RelativeLayout mRlNameLayout;
        @BindView(R.id.tv_tribe_id)
        TextView mTvTribeId;
        @BindView(R.id.tv_tribe_member_count)
        TextView mTvTribeMemberCount;
        @BindView(R.id.tv_tribe_games)
        TextView mTvTribeGames;

        public ViewHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }
}
