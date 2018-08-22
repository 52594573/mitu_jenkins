package com.playingjoy.fanrabbit.ui.adapter.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.domain.event.AddFriendEvent;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * @author Morphine
 * @date 2018-04-10.
 * 搜索结果用户列表适配器
 */

public class SearchResultUserListAdapter extends SimpleRecAdapter<String, SearchResultUserListAdapter.ViewHolder> {


    public SearchResultUserListAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_search_result_user_list;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(R.drawable.game_icon).apply(new RequestOptions().circleCrop())
                .into(holder.mIvAvatar);
        holder.mTvAddFriend.setText(getString(position % 2 == 0 ? R.string.text_add_friend : R.string.text_added));
        holder.mTvAddFriend.setTextColor(getColor(position % 2 == 0 ? R.color.main_black : R.color.hint_color));
        if (position % 2 == 0) {
            holder.mTvAddFriend.setBackground(getDrawable(R.drawable.bg_fillet_login));
        } else {
            holder.mTvAddFriend.setBackgroundColor(getColor(R.color.transparent_color));
        }
        holder.mTvAddFriend.setOnClickListener(v -> {
            if (position % 2 == 0) {
                BusProvider.getBus().post(new AddFriendEvent("123"));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_avatar)
        ImageView mIvAvatar;
        @BindView(R.id.tv_user_name)
        TextView mTvUserName;
        @BindView(R.id.tv_user_level)
        TextView mTvUserLevel;
        @BindView(R.id.iv_user_sex)
        ImageView mIvUserSex;
        @BindView(R.id.tv_add_friend)
        TextView mTvAddFriend;


        public ViewHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }
}
