package com.playingjoy.fanrabbit.ui.activity.mine;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.ui.adapter.mine.MyTaskListAdapter;
import com.playingjoy.fanrabbit.ui.presenter.mine.MyTaskPresenter;
import com.playingjoy.fanrabbit.widget.MyTaskTimeProgressBar;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * 我的任务
 *
 * @author Morphine
 * @date 2018-04-09.
 */

public class MyTaskActivity extends BaseActivity<MyTaskPresenter> {
    @BindView(R.id.tv_online_time)
    TextView mTvOnlineTime;
    @BindView(R.id.pb_online)
    MyTaskTimeProgressBar mPbOnline;
    @BindView(R.id.xlv_novice_task)
    XRecyclerView mXlvNoviceTask;
    @BindView(R.id.xlv_tribal_task)
    XRecyclerView mXlvTribalTask;

    @Override
    public void initData(Bundle savedInstanceState) {
        setTitleBar(getString(R.string.text_my_task));
        getP().getMyTaskData();
        initNoviceTaskList();
        initTribalTaskList();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_task;
    }

    private void initNoviceTaskList() {
        mXlvNoviceTask.setLayoutManager(new LinearLayoutManager(context));
        mXlvNoviceTask.setNestedScrollingEnabled(false);
        mXlvNoviceTask.setAdapter(new MyTaskListAdapter(context));
    }

    private void initTribalTaskList() {
        mXlvTribalTask.setLayoutManager(new LinearLayoutManager(context));
        mXlvTribalTask.setNestedScrollingEnabled(false);
        mXlvTribalTask.setAdapter(new MyTaskListAdapter(context));
    }


    /**
     * 设置在线时长
     *
     * @param onLineTime
     */
    public void setOnLineTime(int onLineTime) {
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(getResources().getColor(R.color.my_task_time_red_color));
        AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(Kits.Dimens.dpToPxInt(context, 20));
        String format = String.format(getString(R.string.format_my_task_online_time), onLineTime);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(format);
        spannableStringBuilder.setSpan(colorSpan, 4, 5 + String.valueOf(onLineTime).length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        spannableStringBuilder.setSpan(sizeSpan, 4, 5 + String.valueOf(onLineTime).length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        mTvOnlineTime.setText(spannableStringBuilder);

        mPbOnline.setCurValue(onLineTime >= 60 ? 4 : onLineTime >= 30 ? 3 : onLineTime >= 10 ? 2 : onLineTime >= 3 ? 1 : -1);
    }

    @Override
    public MyTaskPresenter newP() {
        return new MyTaskPresenter();
    }

    public static void toMyTaskActivity(Activity activity) {
        Router.newIntent(activity).to(MyTaskActivity.class).launch();
    }

}
