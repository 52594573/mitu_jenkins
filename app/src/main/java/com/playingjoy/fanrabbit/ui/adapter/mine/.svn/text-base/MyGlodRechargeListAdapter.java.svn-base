package com.playingjoy.fanrabbit.ui.adapter.mine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.playingjoy.fanrabbit.R;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * 充值选项列表适配器
 * @author Morphine
 * @date 2018-04-11.
 */

public class MyGlodRechargeListAdapter extends SimpleRecAdapter<String, MyGlodRechargeListAdapter.ViewHolder> {

    private int pickPosition = 0;


    public MyGlodRechargeListAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_my_gload_recharge_list;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTvGoldValue.setBackground(getDrawable(pickPosition == position ? R.drawable.bg_gold_recharge_item_p : R.drawable.bg_gold_recharge_item));
        holder.mTvGoldValue.setTextColor(getColor(pickPosition == position ? R.color.main_black : R.color.second_color));
        holder.itemView.setOnClickListener(v -> {
            if (getRecItemClick() != null) {
                getRecItemClick().onItemClick(position, "", 0, holder);
            }
            pickPosition = position;
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_coin_value)
        TextView mTvGoldValue;

        public ViewHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }
}
