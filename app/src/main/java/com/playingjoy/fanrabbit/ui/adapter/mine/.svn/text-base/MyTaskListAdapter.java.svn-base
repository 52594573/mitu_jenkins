package com.playingjoy.fanrabbit.ui.adapter.mine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.playingjoy.fanrabbit.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;

/**
 * @author Morphine
 * @date 2018-04-10.
 */

public class MyTaskListAdapter extends SimpleRecAdapter<String, MyTaskListAdapter.ViewHolder> {


    public MyTaskListAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_my_task_list;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int i = position % 3;
        holder.mBtnTaskAction.setBackground(getDrawable(i == 0 ? R.drawable.bg_my_task_btn : R.drawable.bg_fillet_gifts_btn));
        holder.mBtnTaskAction.setText(i == 0 ? "去完成" : "领取");
        holder.mBtnTaskAction.setTextColor(getColor(i == 0 ? R.color.yellow_color : R.color.second_color));
        holder.line.setVisibility(position == getItemCount()-1?View.GONE:View.VISIBLE);
        holder.mTvTaskName.setText("这是测试任务名称");
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_task_name)
        TextView mTvTaskName;
        @BindView(R.id.tv_task_exp)
        TextView mTvTaskExp;
        @BindView(R.id.tv_task_diamond)
        TextView mTvTaskDiamond;
        @BindView(R.id.btn_task_action)
        TextView mBtnTaskAction;
        @BindView(R.id.line)
        LinearLayout line;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
