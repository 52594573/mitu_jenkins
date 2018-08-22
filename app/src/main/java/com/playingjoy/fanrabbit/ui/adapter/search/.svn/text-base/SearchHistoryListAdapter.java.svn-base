package com.playingjoy.fanrabbit.ui.adapter.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.playingjoy.fanrabbit.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;

/**
 * @author Morphine
 * @date 2018-03-28.
 * 搜索页面搜索历史适配器
 */

public class SearchHistoryListAdapter extends SimpleRecAdapter<String, SearchHistoryListAdapter.ViewHolder> {
    public SearchHistoryListAdapter(Context context) {
        super(context);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvKeyword.setText(data.get(position));
        holder.itemView.setOnClickListener(v -> {
            if (getRecItemClick() != null) {
                getRecItemClick().onItemClick(position, data.get(position), 0, holder);
            }
        });
    }

    @Override
    public ViewHolder newViewHolder(View convertView) {
        return new ViewHolder(convertView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_search_history;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_keyword)
        TextView tvKeyword;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
