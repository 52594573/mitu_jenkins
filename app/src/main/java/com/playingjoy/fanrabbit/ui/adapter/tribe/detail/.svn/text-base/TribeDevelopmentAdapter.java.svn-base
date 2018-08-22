package com.playingjoy.fanrabbit.ui.adapter.tribe.detail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.playingjoy.fanrabbit.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;

/**
 * Created by Ly on 2018/4/18.
 * 工会发展历程适配器
 */

public class TribeDevelopmentAdapter extends SimpleRecAdapter<String, TribeDevelopmentAdapter.TribeDevelopmentHolder> {
    public TribeDevelopmentAdapter(Context context) {
        super(context);
    }

    @Override
    public TribeDevelopmentHolder newViewHolder(View itemView) {
        return new TribeDevelopmentHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_tribe_develoipment_list;
    }

    @Override
    public void onBindViewHolder(TribeDevelopmentHolder holder, int position) {
        if (position == 0) {
            holder.viewDevelopmentTop.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    static class TribeDevelopmentHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.view_development_top)
        View viewDevelopmentTop;
        @BindView(R.id.tv_development_date)
        TextView tvDevelopmentDate;
        @BindView(R.id.view_development_bottom)
        View viewDevelopmentBottom;
        @BindView(R.id.tv_development_desc)
        TextView tvDevelopmentDesc;

        TribeDevelopmentHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
