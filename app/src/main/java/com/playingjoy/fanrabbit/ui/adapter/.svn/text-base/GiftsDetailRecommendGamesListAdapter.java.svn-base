package com.playingjoy.fanrabbit.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.playingjoy.fanrabbit.R;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * 礼包详情页相关游戏推荐列表Adapter
 *
 * @author Morphine
 * @date 2018-03-31.
 */

public class GiftsDetailRecommendGamesListAdapter extends SimpleRecAdapter<String, GiftsDetailRecommendGamesListAdapter.ViewHolder> {


    public GiftsDetailRecommendGamesListAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_gifts_detail_recommend_games_list;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTvGameName.setText(data.get(position));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_game_icon)
        ImageView mIvGameIcon;
        @BindView(R.id.tv_game_name)
        TextView mTvGameName;

        public ViewHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }
}
