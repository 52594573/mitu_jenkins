package com.playingjoy.fanrabbit.ui.presenter.tribe;

import com.playingjoy.fanrabbit.base.BasePresenter;
import com.playingjoy.fanrabbit.ui.activity.tribe.TribeAddedRecordsSearchActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 部落加入记录搜索控制器
 *
 * @author Morphine
 * @date 2018-04-18.
 */

public class TribeAddedRecordsSearchPresenter extends BasePresenter<TribeAddedRecordsSearchActivity> {

    public void searchUser(String keyword) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(keyword + i);
        }
        getV().setSearchResult(list);
    }

}
