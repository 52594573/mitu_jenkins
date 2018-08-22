package com.playingjoy.fanrabbit.ui.adapter.mine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.util.DensityUtil;
import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.utils.GiftsConfig;
import com.playingjoy.fanrabbit.widget.ProgressTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;

/**
 * Author: Ly
 * Data：2018/4/4-16:23
 * Description:
 */
public class MyGiftsAdapter extends SimpleRecAdapter<String, MyGiftsAdapter.MyGiftsHolder> {
    public MyGiftsAdapter(Context context) {
        super(context);
    }

    @Override
    public MyGiftsHolder newViewHolder(View itemView) {
        return new MyGiftsHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_my_gifts;
    }

    @Override
    public void onBindViewHolder(MyGiftsHolder holder, int position) {
        holder.mTvGameGiftName.setCompoundDrawablePadding(DensityUtil.dip2px(context, 8));
        holder.mTvGameGiftName.setCompoundDrawables(null, null,  GiftsConfig.getGiftsDrawableFlag(context.getApplicationContext(), GiftsConfig.GIFTS_TYPE_TRIBE), null);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    static class MyGiftsHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_game_gift_pic)
        ImageView mIvGameGiftPic;
        @BindView(R.id.tv_game_gift_name)
        TextView mTvGameGiftName;
        @BindView(R.id.tv_game_gift_desc)
        TextView mTvGameGiftDesc;
        @BindView(R.id.tv_game_gift_status)
        TextView mTvGameGiftStatus;
        @BindView(R.id.tv_game_gift_code_tips)
        TextView mTvGameGiftCodeTips;
        @BindView(R.id.tv_game_gift_code)
        TextView mTvGameGiftCode;
        @BindView(R.id.tv_game_gift_copy)
        TextView mTvGameGiftCopy;
        @BindView(R.id.tv_game_gift_validity_period_tips)
        TextView mTvGameGiftValidityPeriodTips;
        @BindView(R.id.tv_game_gift_validity_period)
        TextView mTvGameGiftValidityPeriod;
        @BindView(R.id.ptv_game_gift_download)
        ProgressTextView mPtvGameGiftDownload;


        MyGiftsHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
