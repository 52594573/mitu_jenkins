package com.playingjoy.fanrabbit.ui.adapter.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hyphenate.util.DensityUtil;
import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.widget.ProgressTextView;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * @author Morphine
 * @date 2018-04-10.
 * 搜索结果游戏列表适配器
 */

public class SearchResultGameListAdapter extends SimpleRecAdapter<String, SearchResultGameListAdapter.ViewHolder> {


    public SearchResultGameListAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_search_result_game_list;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mPtvGameItemDown.setProgress(50 * (position % 2));
        for (int i = 0; i < 3; i++) {
            TextView gameTag = (TextView) View.inflate(context, R.layout.view_game_tag, null);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.rightMargin = DensityUtil.dip2px(context, 4);
            gameTag.setLayoutParams(layoutParams);
            gameTag.setText("游戏标签" + i);
            holder.mLlGameTag.addView(gameTag);
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_game_icon)
        ImageView mIvGameIcon;
        @BindView(R.id.tv_game_name)
        TextView mTvGameName;
        @BindView(R.id.ll_game_tag)
        LinearLayout mLlGameTag;
        @BindView(R.id.ptv_game_item_down)
        ProgressTextView mPtvGameItemDown;

        public ViewHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }
}
