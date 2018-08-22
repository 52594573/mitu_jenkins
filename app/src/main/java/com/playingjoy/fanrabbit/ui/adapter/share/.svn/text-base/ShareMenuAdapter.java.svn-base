package com.playingjoy.fanrabbit.ui.adapter.share;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.domain.impl.ShareMenuBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.log.XLog;

/**
 * Author: Ly
 * Data：2018/3/29-13:57
 * Description:分享dialog里面的分享菜单的适配器
 */
public class ShareMenuAdapter extends SimpleRecAdapter<ShareMenuBean, ShareMenuAdapter.ShareMenuHolder> {
    public ShareMenuAdapter(Context context) {
        super(context);
    }

    @Override
    public ShareMenuHolder newViewHolder(View itemView) {
        return new ShareMenuHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_share_dialog;
    }

    @Override
    public void onBindViewHolder(final ShareMenuHolder holder, final int position) {
        final ShareMenuBean shareMenuBean = data.get(position);

        if (shareMenuBean != null) {
            XLog.e("------" + shareMenuBean.toString());
            holder.mIvHotGamePic.setImageResource(shareMenuBean.getMenuId());
            holder.mTvHotGameName.setText(shareMenuBean.getMenuName());
            holder.itemView.setOnClickListener(v -> {
                if (getRecItemClick() != null) {
                    getRecItemClick().onItemClick(position, shareMenuBean, 0, holder);
                }
            });
        } else {
            XLog.e("------is null ");
        }
    }

    public static class ShareMenuHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_hot_game_pic)
        ImageView mIvHotGamePic;
        @BindView(R.id.tv_hot_game_name)
        TextView mTvHotGameName;


        public ShareMenuHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
