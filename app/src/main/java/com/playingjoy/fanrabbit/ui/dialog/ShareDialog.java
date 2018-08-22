package com.playingjoy.fanrabbit.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.domain.impl.ShareMenuBean;
import com.playingjoy.fanrabbit.ui.adapter.share.ShareMenuAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.droidlover.xrecyclerview.RecyclerItemCallback;

/**
 * @author Ly
 * @date 2017/10/14
 * 分享的弹窗
 */

public class ShareDialog extends DialogFragment {

    /**
     * dialog
     */
    protected Dialog dialog;
    /**
     * 就是一个标志位
     */
    private static final String TAG = ShareDialog.class.getName();
    /**
     * 分享的菜单
     */
    private RecyclerView mRlvShareMenu;
    /**
     * 分享的菜单的适配器
     */
    private ShareMenuAdapter mShareMenuAdapter;
    /**
     * 分享的菜单的数据源
     */
    private List<ShareMenuBean> mShareMenus = new ArrayList<>();


    private OnItemClickListener mOnItemClickListener;


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public static ShareDialog newInstance(String message) {
        Bundle args = new Bundle();
        args.putString("message", message);
        ShareDialog fragment = new ShareDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(R.layout.diaog_share_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.dialog);
        View view = View.inflate(getActivity(), R.layout.diaog_share_layout, null);
        builder.setView(view);
        dialog = builder.create();
        dialog.setCanceledOnTouchOutside(true);
        // 设置宽度为屏宽、靠近屏幕底部。
        Window window = dialog.getWindow();
        if (window != null && window.getDecorView() != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams wlp = window.getAttributes();
            wlp.gravity = Gravity.BOTTOM;
            //设置没有效果
            wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(wlp);
        } else {
            Log.e(TAG, "window是空的或者是window.getDecorView()是空的");
        }
        initView(view);
        return dialog;
    }

    /**
     * 初始化视图
     *
     * @param view
     */
    String message = null;
    private void initView(View view) {

        if (getArguments() != null) {
            message = getArguments().getString("message");
        }
        mShareMenus.add(new ShareMenuBean(R.drawable.share_wechat, getActivity().getString(R.string.text_wechat)));
        mShareMenus.add(new ShareMenuBean(R.drawable.share_wechat_circle, getActivity().getString(R.string.text_wx_circle)));
        mShareMenus.add(new ShareMenuBean(R.drawable.share_sina, getActivity().getString(R.string.text_sina)));
        mShareMenus.add(new ShareMenuBean(R.drawable.share_qq, getActivity().getString(R.string.text_qq)));
        mShareMenus.add(new ShareMenuBean(R.drawable.share_qq_zone, getActivity().getString(R.string.text_qzone)));
        mShareMenus.add(new ShareMenuBean(R.drawable.share_copy_link, getActivity().getString(R.string.text_copy)));
//        分享的标题
        TextView tvShareTitle = view.findViewById(R.id.tv_share_title);
        tvShareTitle.setText(message);
        mRlvShareMenu = view.findViewById(R.id.rlv_share_menu);
//        分享取消按钮
        TextView tvShareCancel = view.findViewById(R.id.tv_share_cancel);
        mShareMenuAdapter = new ShareMenuAdapter(getActivity());
        mRlvShareMenu.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mRlvShareMenu.setAdapter(mShareMenuAdapter);
        mShareMenuAdapter.addData(mShareMenus);
        mShareMenuAdapter.setRecItemClick(new RecyclerItemCallback<ShareMenuBean, ShareMenuAdapter.ShareMenuHolder>() {
            @Override
            public void onItemClick(int position, ShareMenuBean model, int tag, ShareMenuAdapter.ShareMenuHolder holder) {
                super.onItemClick(position, model, tag, holder);
                mOnItemClickListener.onMenuClickListener(model, message);
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        tvShareCancel.setOnClickListener(v -> {
            if (dialog != null) {
                dialog.dismiss();
            }
        });
    }


    /**
     * 防止重复弹出 显示此dialog的唯一方法
     *
     * @param activity
     * @param message
     * @return
     */
    public static ShareDialog showDialog(FragmentActivity activity, String message) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        ShareDialog shareDialog =
                (ShareDialog) fragmentManager.findFragmentByTag(TAG);
        if (null == shareDialog) {
            shareDialog = newInstance(message);
        }
        if (!activity.isFinishing()
                && null != shareDialog
                && !shareDialog.isAdded()) {
            fragmentManager.beginTransaction()
                    .add(shareDialog, TAG)
                    .commitAllowingStateLoss();
        }
        return shareDialog;
    }

    public interface OnItemClickListener {
        /**
         * 点击菜单项的回调事件
         *
         * @param shareMenuBean
         * @param message
         */
        void onMenuClickListener(ShareMenuBean shareMenuBean, String message);
    }


}
