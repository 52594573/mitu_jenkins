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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * @author Morphine
 * @date 2018-04-18.
 */

public class TribeAddedRecordsListAdapter extends SimpleRecAdapter<String, TribeAddedRecordsListAdapter.ViewHolder> {

    private boolean isShowCheckBox = false;
    private String keyword;
    List<Integer> selectList = new ArrayList<>();
    private OnSelectChangeListener onSelectChangeListener;

    public TribeAddedRecordsListAdapter(Context context) {
        super(context);
    }

    public TribeAddedRecordsListAdapter(Context context, boolean isShowCheckBox) {
        super(context);
        this.isShowCheckBox = isShowCheckBox;
    }

    public void setOnSelectChangeListener(OnSelectChangeListener onSelectChangeListener) {
        this.onSelectChangeListener = onSelectChangeListener;
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_tribe_blacklist_list;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mCbPick.setVisibility(isShowCheckBox ? View.VISIBLE : View.GONE);
        holder.mTvUserName.setText(StringUtil.getSearchSpanString("这是名字啊啊我这是我的", keyword));
        holder.mTvDate.setText("2018-4-18");
        Glide.with(context).load(R.drawable.game_icon).apply(new RequestOptions().circleCrop()).into(holder.mIvAvatar);
        holder.mCbPick.setChecked(selectList.contains(position));
        holder.mCbPick.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (!selectList.contains(position) && isChecked) {
                selectList.add(position);
                if (onSelectChangeListener != null)
                    onSelectChangeListener.onSelectChange(selectList);
            } else if (selectList.contains(position) && !isChecked) {
                selectList.remove(((Integer) position));
                if (onSelectChangeListener != null)
                    onSelectChangeListener.onSelectChange(selectList);
            }
        });
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
        notifyDataSetChanged();
    }

    public interface OnSelectChangeListener {
        void onSelectChange(List<Integer> selectList);
    }


    @Override
    public int getItemCount() {
        return 10;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cb_pick)
        CheckBox mCbPick;
        @BindView(R.id.iv_avatar)
        ImageView mIvAvatar;
        @BindView(R.id.tv_user_name)
        TextView mTvUserName;
        @BindView(R.id.tv_date)
        TextView mTvDate;

        public ViewHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }
}
