package com.playingjoy.fanrabbit.ui.fragment.index.search;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseFragment;
import com.playingjoy.fanrabbit.domain.event.AddFriendEvent;
import com.playingjoy.fanrabbit.ui.activity.chat.AddFriendActivity;
import com.playingjoy.fanrabbit.ui.adapter.search.SearchResultUserListAdapter;
import com.playingjoy.fanrabbit.ui.presenter.index.SearchUserResultPresenter;
import com.playingjoy.fanrabbit.utils.TipDialogUtil;
import com.playingjoy.fanrabbit.utils.cache.UserInfoManager;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import io.reactivex.Flowable;

/**
 * 搜索结果用户页面
 *
 * @author Morphine
 * @date 2018-04-10.
 */
@SuppressWarnings("unused")
public class SearchUserResultFragment extends BaseFragment<SearchUserResultPresenter> {

    @BindView(R.id.xlv_search_result_list)
    XRecyclerContentLayout mXlvSearchResultList;
    String keyword;
    TipDialogUtil tipDialogUtil;

    public static SearchUserResultFragment newInstance(String keyword) {
        Bundle args = new Bundle();
        args.putString("keyword", keyword);
        SearchUserResultFragment fragment = new SearchUserResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        keyword = getArguments().getString("keyword");
        initResultList();
        registerAddFriendEvent();
        getP().getSearchUserResult(keyword);
    }

    /**
     * 注册添加好友事件
     */
    private void registerAddFriendEvent() {
        BusProvider.getBus().toFlowable(AddFriendEvent.class)
                .compose(bindToLifecycle())
                .flatMap(addFriendEvent -> {
                    if (UserInfoManager.isLogin()) {
                        return Flowable.just(addFriendEvent.getModel());
                    } else {
                        return Flowable.error(new Throwable());
                    }
                }).subscribe(s -> AddFriendActivity.toAddFriendActivity(getActivity()), throwable -> {
            if (tipDialogUtil == null) {
                tipDialogUtil = new TipDialogUtil(getActivity());
            }
            tipDialogUtil.showLoginTip(getString(R.string.text_login_tip), getString(R.string.hint_must_login), getString(R.string.text_go_login));
        });
    }


    /**
     * 初始化搜索结果列表
     */
    private void initResultList() {
        mXlvSearchResultList.getRecyclerView().setLayoutManager(new LinearLayoutManager(context));
        mXlvSearchResultList.getRecyclerView().setAdapter(new SearchResultUserListAdapter(context));
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_search_result_simple;
    }

    @Override
    public SearchUserResultPresenter newP() {
        return new SearchUserResultPresenter();
    }

}
