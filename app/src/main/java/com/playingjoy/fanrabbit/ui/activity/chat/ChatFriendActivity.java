package com.playingjoy.fanrabbit.ui.activity.chat;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.ui.presenter.chat.ChatFriendPresenter;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * 好友聊天界面
 *
 * @author Morphine
 * @date 2018-04-02.
 */

public class ChatFriendActivity extends BaseActivity<ChatFriendPresenter> {
    @Override
    public void initData(Bundle savedInstanceState) {
        EmLogout();
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        EaseChatFragment fragment = new EaseChatFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EaseConstant.EXTRA_USER_ID, "123");
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.container, fragment, "chat");
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        EaseChatFragment chatFragment = (EaseChatFragment) getSupportFragmentManager().findFragmentByTag("chat");
        if (chatFragment != null) {
            chatFragment.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    private void EmLogout() {
        EMClient.getInstance().logout(true, new EMCallBack() {

            @Override
            public void onSuccess() {
                Emlogin();
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {

            }
        });
    }

    private void Emlogin() {
        EMClient.getInstance().login("222", "222", new EMCallBack() {
            @Override
            public void onSuccess() {
                XLog.e("环信登录成功");
                EMClient.getInstance().chatManager().loadAllConversations();
                EMClient.getInstance().groupManager().loadAllGroups();
            }

            @Override
            public void onError(int i, String s) {
                XLog.e("环信登录失败:" + s);
            }

            @Override
            public void onProgress(int i, String s) {
                XLog.e("环信登录失败:" + s);
            }
        });
    }

    public static void toChatFriendActivity(Activity activity) {
        Router.newIntent(activity).to(ChatFriendActivity.class).launch();
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_chat_friend;
    }

    @Override
    public ChatFriendPresenter newP() {
        return null;
    }
}
