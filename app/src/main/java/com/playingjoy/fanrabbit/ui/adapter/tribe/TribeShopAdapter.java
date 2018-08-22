package com.playingjoy.fanrabbit.ui.adapter.tribe;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.widget.FilletProgressBarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;

/**
 * Author: Ly
 * Data：2018/4/4-14:49
 * Description:部落商店的
 */
public class TribeShopAdapter extends SimpleRecAdapter<String, TribeShopAdapter.TribeShopHolder> {
    private OnGetNumberBtnClickListener onGetNumberBtnClickListener;


    public TribeShopAdapter(Context context) {
        super(context);
    }

    @Override
    public TribeShopHolder newViewHolder(View itemView) {
        return new TribeShopHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_tribe_shop;
    }

    public void setOnGetNumberBtnClickListener(OnGetNumberBtnClickListener onGetNumberBtnClickListener) {
        this.onGetNumberBtnClickListener = onGetNumberBtnClickListener;
    }


    @Override
    public void onBindViewHolder(TribeShopHolder holder, int position) {
        holder.mTvRushCount.setVisibility(position % 2 == 0 ? View.VISIBLE : View.GONE);
        holder.mPbTribeGiftsProgress.setVisibility(position % 2 == 0 ? View.GONE : View.VISIBLE);
        holder.mBtnTribeGiftsGetNumber.setText(position % 2 == 0 ? R.string.text_rush_number : R.string.receive_number);
        SpannableString spannableString = new SpannableString(String.format(getString(R.string.format_rush_count), position));
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(context.getResources().getColor(R.color.yellow_color));
        spannableString.setSpan(colorSpan,2,2+String.valueOf(position).length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        holder.mTvRushCount.setText(spannableString);

        holder.itemView.setOnClickListener(v -> {
            if (getRecItemClick() != null) {
                getRecItemClick().onItemClick(position, "", 0, holder);
            }
        });
        holder.mBtnTribeGiftsGetNumber.setOnClickListener(v -> {
            if (onGetNumberBtnClickListener != null)
                onGetNumberBtnClickListener.onGetNumberBtnClick(position);
        });
    }

    public interface OnGetNumberBtnClickListener {
        void onGetNumberBtnClick(int position);
    }


    @Override
    public int getItemCount() {
        return 10;
    }

    public static class TribeShopHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_tribe_gifts_pic)
        ImageView mIvTribeGiftsPic;
        @BindView(R.id.tv_tribe_gifts_name)
        TextView mTvTribeGiftsName;
        @BindView(R.id.pb_tribe_gifts_progress)
        FilletProgressBarView mPbTribeGiftsProgress;
        @BindView(R.id.tv_tribe_gifts_tips)
        TextView mTvTribeGiftsTips;
        @BindView(R.id.tv_tribe_gifts_contribution)
        TextView mTvTribeGiftsContribution;
        @BindView(R.id.btn_tribe_gifts_get_number)
        TextView mBtnTribeGiftsGetNumber;
        @BindView(R.id.tv_rush_count)
        TextView mTvRushCount;

        TribeShopHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
