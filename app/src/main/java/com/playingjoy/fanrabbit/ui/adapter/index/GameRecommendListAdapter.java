package com.playingjoy.fanrabbit.ui.adapter.index;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.domain.impl.GameListBean;
import com.playingjoy.fanrabbit.widget.MarqueeView;
import com.playingjoy.fanrabbit.widget.ProgressTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.log.XLog;

/**
 * Author: Ly
 * Data：2018/3/23-18:35
 * Description: 推荐游戏适配器
 */
public class GameRecommendListAdapter extends SimpleRecAdapter<GameListBean.DataBean.GamesBean, GameRecommendListAdapter.GameRecommendListHolder> {
    /**
     * DOWNLOAD_FLAG 下载的flag
     * GAME_DETAIL_FLAG 游戏详情的flag
     */
    private static final int DOWNLOAD_FLAG = 0, GAME_DETAIL_FLAG = 1;


    public GameRecommendListAdapter(Context context) {
        super(context);
    }

    @Override
    public GameRecommendListHolder newViewHolder(View itemView) {
        return new GameRecommendListHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_game_index_list;
    }

    @Override
    public void onBindViewHolder(GameRecommendListHolder holder, int position, List<Object> payloads) {
        XLog.e("onBindViewHolder with payloads-----------" + payloads);


        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            holder.mTvGameItemDown.setText(data.get(position).getMsg());
            holder.mTvGameItemDown.setProgress(data.get(position).getProgress());
        }
    }

    @Override
    public void onBindViewHolder(GameRecommendListHolder holder, int position) {
        XLog.e("onBindViewHolder without payloads-----------" + position);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.mRlvGameItemLikeMan.setLayoutManager(linearLayoutManager);
        holder.mRlvGameItemLikeMan.setAdapter(new GameLikerAdapter(context));

        holder.mTvGameItemDown.setText(data.get(position).getMsg());

        holder.itemView.setOnClickListener(v -> {
            XLog.e("this is the click method " + v.getId());
            if (getRecItemClick() != null) {
                getRecItemClick().onItemClick(position, data.get(position), GAME_DETAIL_FLAG, holder);
            }
        });
        holder.mTvGameItemDown.setOnClickListener(v -> {
            if (getRecItemClick() != null) {
                getRecItemClick().onItemClick(position, data.get(position), DOWNLOAD_FLAG, holder);
            }
        });
    }


    public static class GameRecommendListHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_game_item_pic)
        ImageView mIvGameItemPic;
        @BindView(R.id.tv_game_item_name)
        TextView mTvGameItemName;
        @BindView(R.id.tv_game_item_type_one)
        TextView mTvGameItemTypeOne;
        @BindView(R.id.tv_game_item_type_two)
        TextView mTvGameItemTypeTwo;
        @BindView(R.id.tv_game_item_type_three)
        TextView mTvGameItemTypeThree;
        @BindView(R.id.ptv_game_item_down)
        ProgressTextView mTvGameItemDown;
        @BindView(R.id.iv_game_item_big_pic)
        ImageView mIvGameItemBigPic;
        @BindView(R.id.tv_game_item_big_desc)
        TextView mTvGameItemBigDesc;
        @BindView(R.id.rlv_game_item_like_man)
        RecyclerView mRlvGameItemLikeMan;
        @BindView(R.id.tv_game_item_like_num)
        TextView mTvGameItemLikeNum;

        GameRecommendListHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public ProgressTextView getTvGameItemDown() {
            return mTvGameItemDown;
        }
    }
}
