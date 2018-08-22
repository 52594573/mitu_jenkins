package com.playingjoy.fanrabbit.ui.presenter.login;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BasePresenter;
import com.playingjoy.fanrabbit.domain.impl.UserLoginInfoBean;
import com.playingjoy.fanrabbit.ui.activity.login.LoginActivity;
import com.playingjoy.fanrabbit.utils.net.ErrCodeMessage;
import com.playingjoy.fanrabbit.utils.net.HttpRequest;

import java.util.Map;

import cn.droidlover.xdroidmvp.cache.SharedPref;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;

/**
 * Author: Ly
 * Data：2018/3/22-9:55
 * Description: 登录页面的P
 */
public class LoginPresenter extends BasePresenter<LoginActivity> {

    /**
     * 手机号码登录
     *
     * @param phoneStr
     * @param passStr
     */
    public void doLoginPassWord(String phoneStr, String passStr) {

        if (!Kits.Regular.isPhoneNumber(phoneStr)) {
            getV().showTs(getV().getString(R.string.text_input_phone_number));
            return;
        }

        HttpRequest.getApiService().doPhoneLogin(phoneStr, passStr, "1")
                .compose(getV().bindToLifecycle())
                .compose(showLoadingDialog())
                .subscribe(new ApiSubscriber<UserLoginInfoBean>(getV(), true) {
                    @Override
                    public void onNext(UserLoginInfoBean userLoginInfoBean) {
                        if (userLoginInfoBean.getStatus().equals(ErrCodeMessage.statusSuc)) {
                            getV().LoginSuc(userLoginInfoBean);
                        } else {
                            getV().showTs(userLoginInfoBean.getMessage());
                        }
                    }
                });
    }


    /**
     * 微信登录
     *
     * @param code
     */
    public void doLoginWithWechat(String code) {
        HttpRequest.getApiService().doWeChatLogin(code, "1")
                .compose(showLoadingDialog())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<UserLoginInfoBean>(getV(), true) {
                    @Override
                    public void onNext(UserLoginInfoBean userLoginInfoBean) {
                        if (userLoginInfoBean.getStatus().equals(ErrCodeMessage.statusSuc)) {
                            getV().LoginSuc(userLoginInfoBean);
                        } else {
                            getV().showTs(userLoginInfoBean.getMessage());
                        }
                    }
                });
    }

    /**
     * 新浪登录
     *
     * @param map
     */
    public void doLoginWithSina(Map<String, String> map) {
        map.put("login_type", "2");
        doLoginWithPlatform(map);
    }

    /**
     * QQ登录
     *
     * @param map
     */
    public void doLoginWithQq(Map<String, String> map) {
        map.put("login_type", "1");
        doLoginWithPlatform(map);
    }

    /***
     * 平台登录
     * @param map
     */
    private void doLoginWithPlatform(Map<String, String> map) {
        XLog.e("the map is" + map.toString());
        HttpRequest.getApiService().doQqSinaLogin(map)
                .compose(showLoadingDialog())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<UserLoginInfoBean>(getV(), true) {

                    @Override
                    public void onNext(UserLoginInfoBean userLoginInfoBean) {
                        if (userLoginInfoBean.getStatus().equals(ErrCodeMessage.statusSuc)) {
                            getV().LoginSuc(userLoginInfoBean);
                        } else {
                            getV().showTs(userLoginInfoBean.getMessage());
                        }
                    }
                });
    }

    /**
     * 获取软键盘高度
     *
     * @return
     */
    public int getKeyBoardHeight() {
        return SharedPref.getInstance(getV()).getInt("key_board_height", 0);
    }

    /**
     * 保存软键盘高度
     *
     * @param keyboardHeight
     */
    public void saveKeyBoardHeight(int keyboardHeight) {
        SharedPref.getInstance(getV()).putInt("key_board_height", keyboardHeight);
    }
}
