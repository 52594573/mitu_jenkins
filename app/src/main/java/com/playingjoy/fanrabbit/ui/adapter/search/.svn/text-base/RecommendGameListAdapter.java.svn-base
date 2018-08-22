package com.playingjoy.fanrabbit.ui.adapter.search;

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
 * @author Morphine
 * @date 2018-03-28.
 * 搜索页面推荐游戏适配器
 */

public class RecommendGameListAdapter extends SimpleRecAdapter<String, RecommendGameListAdapter.ViewHolder> {


    public RecommendGameListAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_search_recommend_game_list;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvGameItemName.setText(data.get(position));
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_game_item_pic)
        ImageView ivGameItemPic;
        @BindView(R.id.tv_game_item_name)
        TextView tvGameItemName;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
