package com.playingjoy.fanrabbit.ui.adapter.tribe;

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
 * 选择所属游戏列表的适配器
 */

public class SelectGameAdapter extends SimpleRecAdapter<String, SelectGameAdapter.SelectGameHolder> {
    private static final int SELECT_FLAG = 0;

    public SelectGameAdapter(Context context) {
        super(context);
    }

    @Override
    public SelectGameHolder newViewHolder(View itemView) {
        return new SelectGameHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_select_game_list;
    }

    @Override
    public void onBindViewHolder(SelectGameHolder holder, int position) {
        holder.tvSelectSelect.setOnClickListener(v -> {
            if (getRecItemClick() != null) {
                getRecItemClick().onItemClick(position, null, SELECT_FLAG, holder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public static class SelectGameHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_select_pic)
        ImageView ivSelectPic;
        @BindView(R.id.tv_select_name)
        TextView tvSelectName;
        @BindView(R.id.tv_select_select)
        TextView tvSelectSelect;

        SelectGameHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
