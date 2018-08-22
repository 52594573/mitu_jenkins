package com.playingjoy.fanrabbit.ui.adapter.tribe;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.utils.StringUtil;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * @author Morphine
 * @date 2018-04-17.
 */

public class TransferTribeMemberListAdapter extends SimpleRecAdapter<String, TransferTribeMemberListAdapter.ViewHolder> {

    private boolean isManager = false;
    private int choosePosition = -1;
    private OnMemberSelectListener onMemberSelectListener;
    private String keyword;
    private boolean isShowCheckcbox = true;

    public TransferTribeMemberListAdapter(Context context) {
        this(true, false, context);
    }

    /**
     * @param isShowCheckBox 是否显示选择框
     * @param isManager      是否是管理
     * @param context
     */
    public TransferTribeMemberListAdapter(boolean isShowCheckBox, boolean isManager, Context context) {
        super(context);
        this.isShowCheckcbox = isShowCheckBox;
        this.isManager = isManager;
    }

    public TransferTribeMemberListAdapter(Context context, boolean isManager) {
        this(true, isManager, context);
    }

    public void setOnMemberSelectListener(OnMemberSelectListener onMemberSelectListener) {
        this.onMemberSelectListener = onMemberSelectListener;
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_transfer_tribe_member_list;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.setIsRecyclable(!isShowCheckcbox);

        holder.mTvUserName.setText(StringUtil.getSearchSpanString("名字这是我的名字" + position, keyword));
        holder.mTvIntroduction.setText(StringUtil.getSearchSpanString("这啊是个人简介我的个人简介我的" + position, keyword));

        holder.mCbPick.setVisibility(isManager && position == 0 ? View.GONE : View.VISIBLE);
        holder.mIvLevel.setVisibility(isManager ? View.VISIBLE : View.GONE);
        holder.mIvLevel.setImageResource(position == 0 ? R.drawable.ic_tribe_lv_2 : R.drawable.ic_tribe_lv_1);
        Glide.with(context).load(R.drawable.game_icon).apply(new RequestOptions().circleCrop()).into(holder.mIvAvatar);
        holder.mTvLevel.setVisibility(isManager ? View.VISIBLE : View.GONE);
        holder.mTvLevel.setText(context.getString(position == 0 ? R.string.text_president : R.string.text_vice_president));
        holder.mTvLevel.setBackgroundResource(position == 0 ? R.drawable.bg_tribe_lv_chairman_flag : R.drawable.bg_tribe_lv_vice_chairman_flag);
        holder.mTvLevel.setTextColor(getColor(position == 0 ? R.color.red_color : R.color.gifts_type_color_tribe));
        holder.mCbPick.setVisibility(isShowCheckcbox ? View.VISIBLE : View.GONE);
        holder.mCbPick.setTag(position);
        holder.mCbPick.setChecked(position == choosePosition);
        holder.mCbPick.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (buttonView.getTag().equals(position) && isChecked) {
                choosePosition = position;
                if (onMemberSelectListener != null) {
                    onMemberSelectListener.onMemberSelect(isManager, choosePosition);
                }
                holder.mCbPick.post(this::notifyDataSetChanged);
            } else if (choosePosition == position && buttonView.getTag().equals(position) && !isChecked) {
                choosePosition = -1;
                if (onMemberSelectListener != null) {
                    onMemberSelectListener.onMemberSelect(isManager, choosePosition);
                }
                holder.mCbPick.post(this::notifyDataSetChanged);
            }
        });
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


    public interface OnMemberSelectListener {
        void onMemberSelect(boolean isManager, int position);
    }


    public void setChoosePosition(int choosePosition) {
        this.choosePosition = choosePosition;
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_avatar)
        ImageView mIvAvatar;
        @BindView(R.id.iv_level)
        ImageView mIvLevel;
        @BindView(R.id.tv_user_name)
        TextView mTvUserName;
        @BindView(R.id.tv_user_level)
        TextView mTvUserLevel;
        @BindView(R.id.tv_level)
        TextView mTvLevel;
        @BindView(R.id.iv_user_sex)
        ImageView mIvUserSex;
        @BindView(R.id.cb_pick)
        CheckBox mCbPick;
        @BindView(R.id.tv_introduction)
        TextView mTvIntroduction;

        public ViewHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }
}
