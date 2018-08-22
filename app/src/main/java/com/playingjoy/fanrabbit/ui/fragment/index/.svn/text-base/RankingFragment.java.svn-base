package com.playingjoy.fanrabbit.ui.fragment.index;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseFragment;
import com.playingjoy.fanrabbit.ui.adapter.index.RankingAdapter;
import com.playingjoy.fanrabbit.ui.dialog.ShareDialog;
import com.playingjoy.fanrabbit.ui.presenter.gamedetail.RecommendPresenter;
import com.playingjoy.fanrabbit.utils.ShareUtils;
import com.umeng.socialize.bean.SHARE_MEDIA;

import butterknife.BindView;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;

/**
 * Author: Ly
 * Data：2018/3/23-14:11
 * Description: 首页推荐页面
 */
public class RankingFragment extends BaseFragment<RecommendPresenter> {
    private RankingAdapter mRankingAdapter;

    @BindView(R.id.rlv_recommend_list)
    XRecyclerContentLayout mRlvRecommendList;

    public static RankingFragment newInstance() {
        Bundle args = new Bundle();
        RankingFragment fragment = new RankingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mRankingAdapter = new RankingAdapter(getActivity());
        setDefConfRecyclerView(mRlvRecommendList);
        mRlvRecommendList.getRecyclerView().setLayoutManager(new LinearLayoutManager(getActivity()));
        mRlvRecommendList.getRecyclerView().setAdapter(mRankingAdapter);
        mRankingAdapter.setRecItemClick(new RecyclerItemCallback<String, RankingAdapter.RankingHolder>() {
            @Override
            public void onItemClick(int position, String model, int tag, RankingAdapter.RankingHolder holder) {
                super.onItemClick(position, model, tag, holder);
                switch (tag) {
                    case 0:
                        ShareDialog.showDialog(getActivity(), getString(R.string.text_share_to))
                                .setOnItemClickListener((shareMenuBean, message) -> ShareUtils.getInstance().shareWeb(getActivity(),
                                        SHARE_MEDIA.WEIXIN, "http://www.baidu.com", "分享title", "https://pic.cnblogs.com/face/833725/20160123233734.png",
                                        "desc", null));
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_recommend;
    }

    @Override
    public RecommendPresenter newP() {
        return new RecommendPresenter();
    }


}
