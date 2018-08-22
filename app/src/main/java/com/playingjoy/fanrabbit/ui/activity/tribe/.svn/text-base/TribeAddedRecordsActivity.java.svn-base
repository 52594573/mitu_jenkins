package com.playingjoy.fanrabbit.ui.activity.tribe;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.ui.adapter.tribe.TribeAddedRecordsListAdapter;
import com.playingjoy.fanrabbit.ui.presenter.tribe.TribeAddedRecordsSearchPresenter;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * 部落加入记录
 *
 * @author Morphine
 * @date 2018-04-18.
 */

public class TribeAddedRecordsActivity extends BaseActivity<TribeAddedRecordsSearchPresenter> {
    @BindView(R.id.xlv_added_records)
    XRecyclerView mXlvAddedRecords;
    TribeAddedRecordsListAdapter tribeAddedRecordsListAdapter;


    @Override
    public void initData(Bundle savedInstanceState) {
        setTitleBarRightImg(getString(R.string.text_added_records), R.drawable.search, v -> TribeAddedRecordsSearchActivity.toTribeAddedRecordsSearchActivity(this));
        initList();
    }

    private void initList() {
        View view = View.inflate(context, R.layout.header_tribe_added_records, null);
        mXlvAddedRecords.setLayoutManager(new LinearLayoutManager(context));
        tribeAddedRecordsListAdapter = new TribeAddedRecordsListAdapter(context, false);
        mXlvAddedRecords.setAdapter(tribeAddedRecordsListAdapter);
        mXlvAddedRecords.addHeaderView(view);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_tribe_added_records;
    }

    public static void toTribeAddedRecordsActivity(Activity activity) {
        Router.newIntent(activity).to(TribeAddedRecordsActivity.class).launch();
    }

    @Override
    public TribeAddedRecordsSearchPresenter newP() {
        return new TribeAddedRecordsSearchPresenter();
    }
}
