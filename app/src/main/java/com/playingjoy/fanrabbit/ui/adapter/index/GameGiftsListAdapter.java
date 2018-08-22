package com.playingjoy.fanrabbit.ui.adapter.index;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hyphenate.util.DensityUtil;
import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.utils.GiftsConfig;
import com.playingjoy.fanrabbit.widget.FilletProgressBarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xrecyclerview.RecyclerAdapter;

/**
 * 游戏详情内礼包页面适配器
 *
 * @author Morphine
 * @date 2018-03-29.
 */

public class GameGiftsListAdapter extends RecyclerAdapter<String, RecyclerView.ViewHolder> {

    private OnButtonClickListener onButtonClickListener;
    private int giftsType;

    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
        this.onButtonClickListener = onButtonClickListener;
    }

    public GameGiftsListAdapter(int giftsType, Context context) {
        super(context);
        this.giftsType = giftsType;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 3;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0: //可预定类型item
            default:
                return new PredestineViewHolder(View.inflate(context, R.layout.item_game_detail_gifts_predestine_list, null));
            case 1: //可领号类型item
                return new ReceiveViewHolder(View.inflate(context, R.layout.item_game_detail_gifts_receive_list, null));
            case 2: //淘号类型item
                return new RushViewHolder(View.inflate(context, R.layout.item_game_detail_gifts_rush_list, null));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PredestineViewHolder) {
            PredestineViewHolder predestineViewHolder = (PredestineViewHolder) holder;
            predestineViewHolder.tvGiftsName.setText(data.get(position));
            SpannableString spannableString = new SpannableString(String.format(context.getString(R.string.format_predestine_person_count), String.valueOf(position * 1024)));
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(context.getResources().getColor(R.color.yellow_color));
            spannableString.setSpan(colorSpan, 2, 3 + String.valueOf(position * 1024).length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
            predestineViewHolder.tvPredestineCount.setText(spannableString);
            predestineViewHolder.tvPredestineContent.setText(data.get(position));
            ((PredestineViewHolder) holder).btnPredestine.setOnClickListener(v -> onButtonClickListener.onBtnClick(giftsType, position));
        } else if (holder instanceof ReceiveViewHolder) {
            ReceiveViewHolder receiveViewHolder = (ReceiveViewHolder) holder;
            receiveViewHolder.pbLastNumberProgress.setProgress(position * 10);
            receiveViewHolder.tvGiftsName.setText(data.get(position));
            receiveViewHolder.tvPredestineContent.setText(data.get(position));
            ((ReceiveViewHolder) holder).btnReceiveNumber.setOnClickListener(v -> onButtonClickListener.onBtnClick(giftsType, position));
        } else if (holder instanceof RushViewHolder) {
            RushViewHolder rushViewHolder = (RushViewHolder) holder;
            rushViewHolder.tvGiftsName.setText(data.get(position));
            rushViewHolder.tvPredestineContent.setText(data.get(position));
            SpannableString spannableString = new SpannableString("已淘" + position * 1024 + "次");
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(context.getResources().getColor(R.color.yellow_color));
            spannableString.setSpan(colorSpan, 2, 2 + String.valueOf(position * 1024).length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
            rushViewHolder.tvRushCount.setText(spannableString);
            ((RushViewHolder) holder).btnRushNumber.setOnClickListener(v -> onButtonClickListener.onBtnClick(giftsType, position));
        }
        setGiftsType(holder);
        holder.itemView.setOnClickListener(v -> {
            if (getRecItemClick() != null) {
                getRecItemClick().onItemClick(position, data.get(position), 0, holder);
            }
        });
    }

    private void setGiftsType(RecyclerView.ViewHolder holder) {
        TextView tvGiftsName = holder.itemView.findViewById(R.id.tv_gifts_name);
        tvGiftsName.setCompoundDrawablePadding(DensityUtil.dip2px(context.getApplicationContext(), 8));
        tvGiftsName.setCompoundDrawables(null, null, GiftsConfig.getGiftsDrawableFlag(context.getApplicationContext(), giftsType), null);
    }


    public static class PredestineViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_gifts_name)
        TextView tvGiftsName;
        @BindView(R.id.tv_predestine_count)
        TextView tvPredestineCount;
        @BindView(R.id.tv_predestine_content)
        TextView tvPredestineContent;
        @BindView(R.id.btn_predestine)
        Button btnPredestine;

        public PredestineViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class ReceiveViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_gifts_name)
        TextView tvGiftsName;
        @BindView(R.id.pb_last_number_progress)
        FilletProgressBarView pbLastNumberProgress;
        @BindView(R.id.tv_predestine_content)
        TextView tvPredestineContent;
        @BindView(R.id.btn_receive_number)
        Button btnReceiveNumber;

        public ReceiveViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class RushViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_gifts_name)
        TextView tvGiftsName;
        @BindView(R.id.tv_rush_count)
        TextView tvRushCount;
        @BindView(R.id.tv_predestine_content)
        TextView tvPredestineContent;
        @BindView(R.id.btn_rush_number)
        Button btnRushNumber;

        public RushViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnButtonClickListener {
        void onBtnClick(int giftsType, int position);
    }
}
