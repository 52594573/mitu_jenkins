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
 * Created by Ly on 2018/4/18.
 * 游戏军团适配器
 */

public class TribeCorpsAdapter extends SimpleRecAdapter<String, TribeCorpsAdapter.TribeCorpsHolder> {
    public TribeCorpsAdapter(Context context) {
        super(context);
    }

    @Override
    public TribeCorpsHolder newViewHolder(View itemView) {
        return new TribeCorpsHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_tribe_corps_list;
    }

    @Override
    public void onBindViewHolder(TribeCorpsHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    static class TribeCorpsHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_corps_pic)
        ImageView ivCorpsPic;
        @BindView(R.id.tv_corps_name)
        TextView tvCorpsName;
        @BindView(R.id.tv_corps_online_num)
        TextView tvCorpsOnlineNum;
        @BindView(R.id.tv_corps_all_num)
        TextView tvCorpsAllNum;
        @BindView(R.id.iv_corps_more)
        ImageView ivCorpsMore;

        TribeCorpsHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
