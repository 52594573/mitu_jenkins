package com.playingjoy.fanrabbit.ui.adapter.tribe.detail;

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
 * Data：2018/4/17-14:28
 * Description: 部落游戏列表适配器
 */

public class TribeGamesAdapter extends SimpleRecAdapter<String, TribeGamesAdapter.TribeGamesHolder> {
    public TribeGamesAdapter(Context context) {
        super(context);
    }

    @Override
    public TribeGamesHolder newViewHolder(View itemView) {
        return new TribeGamesHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_tribe_game_list;
    }

    @Override
    public void onBindViewHolder(TribeGamesHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    static class TribeGamesHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_game_pic)
        ImageView ivGamePic;
        @BindView(R.id.tv_game_name)
        TextView tvGameName;

        TribeGamesHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
