package com.playingjoy.fanrabbit.ui.adapter.index;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.playingjoy.fanrabbit.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;

/**
 * Author: Ly
 * Data：2018/3/27-16:22
 * Description: 消息中心我的消息列表
 */
public class MessageMyListAdapter extends SimpleRecAdapter<String, MessageMyListAdapter.MessageMyListHolder> {
    /**
     * 点击事件的flag CLICK_TOPIC 进入话题  CLICK_ACTIVITY 进入活动  CLICK_INFO 进入查看用户信息  CLICK_TRIBE 进入部落
     */
    private static final int CLICK_TOPIC = 0, CLICK_ACTIVITY = 1, CLICK_INFO = 2, CLICK_TRIBE = 3;

    public MessageMyListAdapter(Context context) {
        super(context);
    }

    @Override
    public MessageMyListHolder newViewHolder(View itemView) {
        return new MessageMyListHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_message_my_un_read;
    }

    @Override
    public void onBindViewHolder(MessageMyListHolder holder, int position) {

        if (position == 0) {
            SpannableString spannableString = getSpannableString("#万圣节# 今晚去哪里鬼混，来迷兔参加PK大赛赢大奖，万圣节好礼等你拿！参加活动>>");
            spannableString.setSpan(new strClickable(v -> {
                if (getRecItemClick() != null) {
                    getRecItemClick().onItemClick(position, null, CLICK_TOPIC, holder);
                }
            }), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new strClickable(v -> {
                if (getRecItemClick() != null) {
                    getRecItemClick().onItemClick(position, null, CLICK_ACTIVITY, holder);
                }
            }), spannableString.length() - 6, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.mTvMyMessageText.setText(spannableString);
            holder.mTvMyMessageText.setMovementMethod(LinkMovementMethod.getInstance());
        } else if (position == 1) {
            SpannableString spannableString = getSpannableString("关注了你 查看他资料>");
            spannableString.setSpan(new strClickable(v -> {
                if (getRecItemClick() != null) {
                    getRecItemClick().onItemClick(position,null, CLICK_INFO, holder);
                }
            }), spannableString.length() - 6, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.mTvMyMessageText.setText(spannableString);
            holder.mTvMyMessageText.setMovementMethod(LinkMovementMethod.getInstance());
        } else {
            SpannableString spannableString = getSpannableString("公告：西游正传豪华礼包，楚留香节日礼包上架，大家快来领取 进入部落>>");
            spannableString.setSpan(new strClickable(v -> {
                if (getRecItemClick() != null) {
                    getRecItemClick().onItemClick(position, null, CLICK_TRIBE, holder);
                }
            }), spannableString.length() - 6, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.mTvMyMessageText.setText(spannableString);
            holder.mTvMyMessageText.setMovementMethod(LinkMovementMethod.getInstance());
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public static class MessageMyListHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_my_message_pic)
        ImageView mIvMyMessagePic;
        @BindView(R.id.tv_my_message_name)
        TextView mTvMyMessageName;
        @BindView(R.id.tv_my_message_time)
        TextView mTvMyMessageTime;
        @BindView(R.id.tv_my_message_text)
        TextView mTvMyMessageText;

        MessageMyListHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private SpannableString getSpannableString(String str) {
        return new SpannableString(str);
    }

    /**
     * 文字点击效果
     */
    private class strClickable extends ClickableSpan {
        private final View.OnClickListener mOnClickListener;

        strClickable(View.OnClickListener l) {
            mOnClickListener = l;
        }

        @Override
        public void onClick(View widget) {
            mOnClickListener.onClick(widget);
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setUnderlineText(false);
            ds.setColor(Color.parseColor("#4787fc"));
        }
    }


}
