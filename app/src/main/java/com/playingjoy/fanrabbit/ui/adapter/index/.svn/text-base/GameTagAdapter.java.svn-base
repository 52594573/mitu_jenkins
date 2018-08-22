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
 * Data：2018/3/28-15:34
 * Description: 游戏标签适配器
 */
public class GameTagAdapter extends SimpleRecAdapter<String, GameTagAdapter.GameTagHolder> {
    public GameTagAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public GameTagHolder newViewHolder(View itemView) {
        return new GameTagHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_game_tag;
    }

    @Override
    public void onBindViewHolder(GameTagHolder holder, int position) {

    }

    public static class GameTagHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_game_type_str)
        TextView mTvGameTypeStr;

        GameTagHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
