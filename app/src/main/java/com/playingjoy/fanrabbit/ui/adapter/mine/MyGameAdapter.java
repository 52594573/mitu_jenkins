package com.playingjoy.fanrabbit.ui.adapter.mine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.widget.ProgressTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;

/**
 * Author: Ly
 * Data：2018/3/29-15:38
 * Description:我的游戏适配器
 */
public class MyGameAdapter extends SimpleRecAdapter<String, MyGameAdapter.MyGameHolder> {
    public MyGameAdapter(Context context) {
        super(context);
    }

    @Override
    public MyGameHolder newViewHolder(View itemView) {
        return new MyGameHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_my_game_list;
    }

    @Override
    public void onBindViewHolder(MyGameHolder holder, int position) {
        holder.mptvMyGamePromotion.setProgress(40);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public static class MyGameHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_my_game_pic)
        ImageView mIvMyGamePic;
        @BindView(R.id.ptv_my_game_download)
        ProgressTextView mptvMyGamePromotion;
        @BindView(R.id.tv_my_game_name)
        TextView mTvMyGameName;
        @BindView(R.id.tv_my_game_down_num)
        TextView mTvMyGameDownNum;
        @BindView(R.id.tv_my_game_gifts)
        TextView mTvMyGameGifts;
        @BindView(R.id.tv_my_game_tribe)
        TextView mTvMyGameTribe;
        @BindView(R.id.tv_my_game_download)
        TextView mTvMyGameDownload;
        @BindView(R.id.ll_my_game_content)
        LinearLayout mLlMyGameContent;
        @BindView(R.id.tv_my_game_delete)
        TextView mTvMyGameDelete;
        @BindView(R.id.ll_my_game_right_menu)
        LinearLayout mLlMyGameRightMenu;

        MyGameHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
