package com.playingjoy.fanrabbit.ui.adapter.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.playingjoy.fanrabbit.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;

/**
 * @author Morphine
 * @date 2018-03-28.
 * 搜索页面推荐部落适配器
 */

public class RecommendTribeListAdapter extends SimpleRecAdapter<String, RecommendTribeListAdapter.ViewHolder> {

    public RecommendTribeListAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_search_recommend_tribe_list;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //不同名次的排名字体颜色不同
        holder.tvRank.setTextColor(context.getResources().getColor(position == 0 ? R.color.tribe_rank_first :
                position == 1 ? R.color.tribe_rank_second :
                        position == 2 ? R.color.tribe_rank_third : R.color.tribe_rank_other));
        holder.tvRank.setText(String.valueOf(position + 1));
        holder.rbTribeRating.setRating(position % 5);
        holder.tvTribeName.setText(data.get(position));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_rank)
        TextView tvRank;
        @BindView(R.id.iv_tribe_pic)
        ImageView ivTribePic;
        @BindView(R.id.tv_tribe_name)
        TextView tvTribeName;
        @BindView(R.id.rb_tribe_rating)
        RatingBar rbTribeRating;
        @BindView(R.id.tv_tribe_id)
        TextView tvTribeId;
        @BindView(R.id.tv_tribe_member_count)
        TextView tvTribeMemberCount;
        @BindView(R.id.tv_tribe_games)
        TextView tvTribeGames;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
