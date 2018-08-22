package com.playingjoy.fanrabbit.ui.fragment.index;

import android.Manifest;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.notification.FileDownloadNotificationHelper;
import com.playingjoy.fanrabbit.BuildConfig;
import com.playingjoy.fanrabbit.MainActivity;
import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseFragment;
import com.playingjoy.fanrabbit.domain.impl.GameListBean;
import com.playingjoy.fanrabbit.ui.activity.index.GameDetailActivity;
import com.playingjoy.fanrabbit.ui.activity.mine.MyGameActivity;
import com.playingjoy.fanrabbit.ui.adapter.index.GameFriendsAdapter;
import com.playingjoy.fanrabbit.ui.adapter.index.GameLikerAdapter;
import com.playingjoy.fanrabbit.ui.adapter.index.GameRecommendListAdapter;
import com.playingjoy.fanrabbit.ui.presenter.gamedetail.RecommendPresenter;
import com.playingjoy.fanrabbit.utils.cache.DownLoadInfoManager;
import com.playingjoy.fanrabbit.widget.MarqueeView;
import com.playingjoy.fanrabbit.widget.ProgressTextView;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * Author: Ly
 * Data：2018/3/23-14:11
 * Description: 首页推荐页面
 */
public class RecommendFragment extends BaseFragment<RecommendPresenter> {
    /**
     * 是否登录了 这里只是为了方便调试布局而用的一个变量
     */
    private boolean isLogin;


    @BindView(R.id.rlv_recommend_list)
    XRecyclerContentLayout mRlvRecommendList;

    /**
     * 推荐游戏列表适配器
     */
    private GameRecommendListAdapter mGameRecommendListAdapter;
    /**
     * 游戏头布局
     */
    private View mHeaderView;
    /**
     * 头布局里面的XXX人喜欢的列表 好友玩过的列表/精选游戏
     */
    private RecyclerView mRlvHeaderLiker, mRlvHeaderFriendsPlaying;
    /**
     * 跑马灯广告
     */
    private MarqueeView mMvHeaderAds;
    /**
     * 头布局里面的下载按钮
     */
    private ProgressTextView mProHeaderDownLoad;


    public static RecommendFragment newInstance(boolean isLogin) {
        Bundle args = new Bundle();
        args.putBoolean("isLogin", isLogin);
        RecommendFragment fragment = new RecommendFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        if (!getP().getDownLoadPath().exists()) {
            // 创建缓存文件夹
            boolean isMkdirSuc = getP().getDownLoadPath().mkdir();
            XLog.e("是否创建成功了" + isMkdirSuc);
        }

        isLogin = getArguments().getBoolean("isLogin");
        initHeaderView();
        mGameRecommendListAdapter = new GameRecommendListAdapter(getActivity());
        mRlvRecommendList.getRecyclerView().setLayoutManager(new LinearLayoutManager(getActivity()));
        setDefConfRecyclerView(mRlvRecommendList);
        mRlvRecommendList.getRecyclerView().setAdapter(mGameRecommendListAdapter);
        mRlvRecommendList.getRecyclerView().addHeaderView(mHeaderView);
        mRlvRecommendList.getRecyclerView().setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                getP().getGameList();
            }

            @Override
            public void onLoadMore(int page) {

            }
        });
        mGameRecommendListAdapter.setRecItemClick(new RecyclerItemCallback<GameListBean.DataBean.GamesBean, GameRecommendListAdapter.GameRecommendListHolder>() {
            @Override
            public void onItemClick(int position, GameListBean.DataBean.GamesBean model, int tag, GameRecommendListAdapter.GameRecommendListHolder holder) {
                super.onItemClick(position, model, tag, holder);
                /**
                 * DOWNLOAD_FLAG 下载的flag
                 * GAME_DETAIL_FLAG 游戏详情的flag
                 * DOWNLOAD_FLAG = 0, GAME_DETAIL_FLAG = 1
                 */
                switch (tag) {
                    case 0:
                        doCheckDownload(model.getDownload(), model.getName(), position);
                        break;
                    case 1:
                        GameDetailActivity.toGameDetailActivity(getActivity());
                        break;

                    default:
                        break;
                }
            }
        });

        getP().getGameList();

    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_recommend;
    }

    @Override
    public RecommendPresenter newP() {
        return new RecommendPresenter();
    }

    /**
     * 初始化头布局
     */
    private void initHeaderView() {
        TextView tvHeaderMyGame, tvHeaderFriendsGame;
        mHeaderView = View.inflate(getActivity(), R.layout.header_game_index_list, null);
        // 喜欢的人的recyclerView
        mRlvHeaderLiker = mHeaderView.findViewById(R.id.rlv_game_item_like_man);
        // 跑马灯广告
        mMvHeaderAds = mHeaderView.findViewById(R.id.mv_game_item_ad);
        // 朋友玩的游戏&今日推荐 的标题
        tvHeaderFriendsGame = mHeaderView.findViewById(R.id.tv_header_friends_game);
        // 我的游戏&游戏精选 的标题
        tvHeaderMyGame = mHeaderView.findViewById(R.id.tv_header_my_game);
        // 朋友玩的游戏 recyclerView
        mRlvHeaderFriendsPlaying = mHeaderView.findViewById(R.id.rlv_game_item_friends_playing);
        // 喜欢的人的recyclerView的LinearManager
        LinearLayoutManager llForLiker = new LinearLayoutManager(getActivity());
        // 朋友玩的游戏的recyclerView的LinearManager
        LinearLayoutManager llForFriends = new LinearLayoutManager(getActivity());
        mProHeaderDownLoad = mHeaderView.findViewById(R.id.ptv_game_item_down);
        llForLiker.setOrientation(LinearLayoutManager.HORIZONTAL);
        llForFriends.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRlvHeaderLiker.setLayoutManager(llForLiker);
        mRlvHeaderFriendsPlaying.setLayoutManager(llForFriends);
        mRlvHeaderLiker.setAdapter(new GameLikerAdapter(getActivity()));
        mRlvHeaderFriendsPlaying.setAdapter(new GameFriendsAdapter(getActivity()));
        mRlvHeaderFriendsPlaying.setNestedScrollingEnabled(false);
        if (isLogin) {
            // 已经登录 显示我的游戏 朋友正在玩的游戏 显示ad
            mMvHeaderAds.setVisibility(View.VISIBLE);
            mMvHeaderAds.setContent("恋与制作人最新礼包待领：10000金币，200钻，许愿券若干，充钱就送");
            tvHeaderMyGame.setText(getString(R.string.text_my_games));
            tvHeaderFriendsGame.setText(getString(R.string.text_friends_game));
        } else {
            // 还没登录 显示进入推荐 精选游戏 不显示ad
            mMvHeaderAds.setVisibility(View.GONE);
            tvHeaderMyGame.setText(getString(R.string.text_today_recommend));
            tvHeaderFriendsGame.setText(getString(R.string.text_featured_games));
        }
        mHeaderView.setOnClickListener(v -> GameDetailActivity.toGameDetailActivity(getActivity()));
        mProHeaderDownLoad.setOnClickListener(v -> doCheckDownloadIsHeader("http://imtt.dd.qq.com/16891/F86997E4A7292389DCE0E75A06907CE4.apk",
                "全民打飞机"));
    }


    /**
     * 显示Notification
     *
     * @param apkName
     * @param status
     * @param downloadId
     * @param max
     * @param progress
     */
    private void showNotification(String apkName, String status, int downloadId, int max, int progress) {
        Intent actionIntent = new Intent(context, MyGameActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(apkName)
                .setContentIntent(pendingIntent)
                .setContentText(status)
                .setProgress(max, progress, false)
                .setAutoCancel(true);
        if (notificationManager != null) {
            notificationManager.notify(downloadId, builder.build());
        }
    }

    /**
     * 头布局里面的
     * 确认下载
     * 检查是否有权限 是否创建了下载文件夹
     *
     * @param url
     * @param fileName
     */
    private void doCheckDownloadIsHeader(String url, String fileName ) {
        String finalFileName = getP().getDownLoadPath() + File.separator + fileName + ".apk";
        getRxPermissions().request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(aBoolean -> {
                    if (aBoolean) {
                        getP().doLoadLoad(context, mProHeaderDownLoad.getText().toString(), url, fileName, finalFileName, new FileDownloadListener() {
                            @Override
                            protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                                showNotification(fileName, getString(R.string.text_pending), task.getId(), totalBytes, soFarBytes);
                                updateDownLoadStatus(task.getId(), false, 0, getResources().getString(R.string.text_start_download), 0, 0);
                            }

                            @Override
                            protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                                showNotification(fileName, getString(R.string.text_downloading), task.getId(), totalBytes, soFarBytes);
                                updateDownLoadStatus(task.getId(), false, 0, Kits.Calculation.calculaSize(totalBytes), soFarBytes, totalBytes);
                            }

                            @Override
                            protected void completed(BaseDownloadTask task) {
                                updateDownLoadStatus(task.getId(), false, 0, getResources().getString(R.string.text_download_finish), 0, 0);
                                showNotification(fileName, getString(R.string.text_download_finish), task.getId(), 1, 1);
                                Kits.Package.installNormal(context, BuildConfig.APPLICATION_ID, task.getPath());
                            }

                            @Override
                            protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                                showNotification(fileName, getString(R.string.text_download_paused), task.getId(), soFarBytes, totalBytes);
                                updateDownLoadStatus(task.getId(), false, 0, getResources().getString(R.string.text_download_goon), soFarBytes, totalBytes);
                            }

                            @Override
                            protected void error(BaseDownloadTask task, Throwable e) {
                                showNotification(fileName, getString(R.string.text_download_error), task.getId(), 1, 1);
                                updateDownLoadStatus(task.getId(), false, 0, getResources().getString(R.string.text_download_error), 0, 0);
                            }

                            @Override
                            protected void warn(BaseDownloadTask task) {
                                showNotification(fileName, getString(R.string.text_download_error), task.getId(), 1, 1);
                                updateDownLoadStatus(task.getId(), false, 0, getResources().getString(R.string.text_download_error), 0, 0);
                            }
                        });
                    } else {
                        showAlert2AppInfo(getString(R.string.text_please_agree_to_authorize));
                    }
                });
    }

    /**
     * 确认下载
     * 检查是否有权限 是否创建了下载文件夹
     *
     * @param url
     * @param fileName
     */
    private void doCheckDownload(String url, String fileName, int position) {
        String finalFileName = getP().getDownLoadPath() + File.separator + fileName + ".apk";
        getRxPermissions().request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(aBoolean -> {
                    if (aBoolean) {
                        getP().doLoadLoad(context, getDownLoadFlag(position), url, fileName, finalFileName, new FileDownloadListener() {
                            @Override
                            protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                                showNotification(fileName, getString(R.string.text_pending), task.getId(), totalBytes, soFarBytes);
                                updateDownLoadStatus(task.getId(), true, position, getResources().getString(R.string.text_start_download), 0, 0);
                            }

                            @Override
                            protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                                showNotification(fileName, getString(R.string.text_downloading), task.getId(), totalBytes, soFarBytes);
                                updateDownLoadStatus(task.getId(), true, position, Kits.Calculation.calculaSize(totalBytes), soFarBytes, totalBytes);
                            }

                            @Override
                            protected void completed(BaseDownloadTask task) {
                                updateDownLoadStatus(task.getId(), true, position, getResources().getString(R.string.text_download_finish), 0, 0);
                                showNotification(fileName, getString(R.string.text_download_finish), task.getId(), 1, 1);
                                Kits.Package.installNormal(context, BuildConfig.APPLICATION_ID, task.getPath());
                            }

                            @Override
                            protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                                updateDownLoadStatus(task.getId(), true, position, getResources().getString(R.string.text_download_goon), soFarBytes, totalBytes);
                                showNotification(fileName, getString(R.string.text_download_paused), task.getId(), 0, 0);
                            }

                            @Override
                            protected void error(BaseDownloadTask task, Throwable e) {
                                updateDownLoadStatus(task.getId(), true, position, getResources().getString(R.string.text_download_error), 0, 0);
                                showNotification(fileName, getString(R.string.text_download_error), task.getId(), 1, 1);
                            }

                            @Override
                            protected void warn(BaseDownloadTask task) {
                                updateDownLoadStatus(task.getId(), true, position, getResources().getString(R.string.text_download_error), 0, 0);
                                showNotification(fileName, getString(R.string.text_download_error), task.getId(), 1, 1);
                            }
                        });
                    } else {
                        showAlert2AppInfo(getString(R.string.text_please_agree_to_authorize));
                    }
                });
    }

    /**
     * 获取flag 用于recyclerView列表刷新
     *
     * @param position
     * @return
     */
    public String getDownLoadFlag(int position) {
        RecyclerView.ViewHolder viewHolder = mRlvRecommendList.getRecyclerView().findViewHolderForAdapterPosition(position + 1);
        if (viewHolder != null && viewHolder instanceof GameRecommendListAdapter.GameRecommendListHolder) {
            GameRecommendListAdapter.GameRecommendListHolder holder = (GameRecommendListAdapter.GameRecommendListHolder) viewHolder;
            return holder.getTvGameItemDown().getText().toString();
        } else {
            return null;
        }
    }

    /**
     * 更新下载状态
     */
    public void updateDownLoadStatus(int downloadID, boolean isRecyclerView, int position, String str, int soFarBytes, int totalBytes) {
        if (isRecyclerView) {
            if (isCurrentListViewItemVisible(position, mRlvRecommendList.getRecyclerView())) {
                RecyclerView.ViewHolder viewHolder = mRlvRecommendList.getRecyclerView().findViewHolderForAdapterPosition(position + 1);
                if (viewHolder != null && viewHolder instanceof GameRecommendListAdapter.GameRecommendListHolder) {
                    GameRecommendListAdapter.GameRecommendListHolder holder = (GameRecommendListAdapter.GameRecommendListHolder) viewHolder;
                    if (soFarBytes != 0 && totalBytes != 0) {
                        holder.getTvGameItemDown().setProgress(soFarBytes, totalBytes);
                        DownLoadInfoManager.updateDownloadProgress(downloadID, soFarBytes, totalBytes);
                    }
                    holder.getTvGameItemDown().setText(str);
                }
            }
        } else {
            mProHeaderDownLoad.setText(str);
            if (soFarBytes != 0 && totalBytes != 0) {
                mProHeaderDownLoad.setProgress(soFarBytes, totalBytes);
                DownLoadInfoManager.updateDownloadProgress(downloadID, soFarBytes, totalBytes);
            }
        }
    }

    public void getGameListSuc(List<GameListBean.DataBean.GamesBean> games) {
        mGameRecommendListAdapter.setData(games);
    }

    /**
     * 获取当前的recyclerView的item是不是可见的
     *
     * @param position     下标值
     * @param recyclerView recyclerView 控件
     * @return
     */
    protected boolean isCurrentListViewItemVisible(int position, RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int first = layoutManager.findFirstVisibleItemPosition();
        int last = layoutManager.findLastVisibleItemPosition();
        return first <= position && position <= last;
    }

}
