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
 * Data：2018/3/26-16:29
 * Description: 游戏排行列表
 */
public class RankingAdapter extends SimpleRecAdapter<String, RankingAdapter.RankingHolder> {
    public RankingAdapter(Context context) {
        super(context);
    }

    @Override
    public RankingHolder newViewHolder(View itemView) {
        return new RankingHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_index_ranking;
    }

    @Override
    public void onBindViewHolder(RankingHolder holder, int position) {
        holder.mTvRankingOrder.setText(String.valueOf(position));
        holder.mIvRankingPromotion.setOnClickListener(v -> {
            if (getRecItemClick()!=null){
                getRecItemClick().onItemClick(position,null,0,holder);
            }
        });
    }


    @Override
    public int getItemCount() {
        return 10;
    }

    public static class RankingHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_ranking_order)
        TextView mTvRankingOrder;
        @BindView(R.id.iv_ranking_pic)
        ImageView mIvRankingPic;
        @BindView(R.id.iv_ranking_promotion)
        ImageView mIvRankingPromotion;
        @BindView(R.id.tv_ranking_name)
        TextView mTvRankingName;
        @BindView(R.id.tv_ranking_down_num)
        TextView mTvRankingDownNum;
        @BindView(R.id.tv_ranking_gifts)
        TextView mTvRankingGifts;
        @BindView(R.id.tv_ranking_tribe)
        TextView mTvRankingTribe;
        @BindView(R.id.tv_ranking_download)
        TextView mTvRankingDownload;

        RankingHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
