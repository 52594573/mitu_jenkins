package com.playingjoy.fanrabbit.ui.adapter.tribe.detail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.playingjoy.fanrabbit.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Author: Ly
 * Data：2018/4/17-14:28
 * Description: 部落成员适配器
 */
public class TribeMemberAdapter extends SimpleRecAdapter<String, TribeMemberAdapter.TribeMemberHolder> {
    public TribeMemberAdapter(Context context) {
        super(context);
    }

    @Override
    public TribeMemberHolder newViewHolder(View itemView) {
        return new TribeMemberHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_tribe_member_list;
    }

    @Override
    public void onBindViewHolder(TribeMemberHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    static class TribeMemberHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_member_pic)
        CircleImageView ivMemberPic;
        @BindView(R.id.iv_member_name)
        TextView ivMemberName;
        @BindView(R.id.iv_member_job)
        TextView ivMemberJob;

        TribeMemberHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
