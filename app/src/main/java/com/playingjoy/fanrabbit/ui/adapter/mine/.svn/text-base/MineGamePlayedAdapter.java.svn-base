package com.playingjoy.fanrabbit.ui.adapter.mine;

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
 * Data：2018/4/3-15:56
 * Description:
 */
public class MineGamePlayedAdapter extends SimpleRecAdapter<String,MineGamePlayedAdapter.MineGamePlayedHolder> {
    public MineGamePlayedAdapter(Context context) {
        super(context);
    }

    @Override
    public MineGamePlayedHolder newViewHolder(View itemView) {
        return new MineGamePlayedHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_my_game_played;
    }

    @Override
    public void onBindViewHolder(MineGamePlayedHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    static class MineGamePlayedHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_played_pic)
        ImageView mIvPlayedPic;

        MineGamePlayedHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
