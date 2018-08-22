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
 * 交易记录列表适配器
 *
 * @author Morphine
 * @date 2018-04-11.
 */

public class TransactionRecordListAdapter extends SimpleRecAdapter<String, TransactionRecordListAdapter.ViewHolder> {

    public TransactionRecordListAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_transaction_record_list;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_record_name)
        TextView mTvRecordName;
        @BindView(R.id.tv_time)
        TextView mTvTime;
        @BindView(R.id.tv_amount)
        TextView mTvAmount;

        public ViewHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }
}
