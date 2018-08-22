package com.playingjoy.fanrabbit.ui.adapter.index;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.domain.impl.GameDetailBean;
import com.playingjoy.fanrabbit.widget.CircularProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;

/**
 * Author: Ly
 * Data：2018/3/21-14:02
 * Description: 游戏列表适配器
 */
public class GameListAdapter extends SimpleRecAdapter<GameDetailBean, GameListAdapter.ViewHolder> {


    public GameListAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_game_list;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        holder.mCpbGameProgress.setOnClickListener(v -> {
            if (getRecItemClick() != null) {
                getRecItemClick().onItemClick(position, data.get(position), 0, holder);
            }
        });
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_game_pic)
        ImageView mIvGamePic;
        @BindView(R.id.tv_game_name)
        TextView mTvGameName;
        @BindView(R.id.tv_game_size)
        TextView mTvGameSize;
        @BindView(R.id.tv_game_desc)
        TextView mTvGameDesc;
        @BindView(R.id.cpb_game_progress)
        CircularProgressBar mCpbGameProgress;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}



