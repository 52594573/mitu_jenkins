package com.playingjoy.fanrabbit.ui.adapter.index;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.playingjoy.fanrabbit.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;

/**
 * Author: Ly
 * Data：2018/3/26-10:38
 * Description: 喜欢的人的适配器
 */
public class GameLikerAdapter extends SimpleRecAdapter<String, GameLikerAdapter.GameLikerHolder> {
    public GameLikerAdapter(Context context) {
        super(context);
    }

    @Override
    public GameLikerHolder newViewHolder(View itemView) {
        return new GameLikerHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_game_list_liker;
    }

    @Override
    public void onBindViewHolder(GameLikerHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class GameLikerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_game_list_liker_pic)
        ImageView mIvGameListLikerPic;

        GameLikerHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
