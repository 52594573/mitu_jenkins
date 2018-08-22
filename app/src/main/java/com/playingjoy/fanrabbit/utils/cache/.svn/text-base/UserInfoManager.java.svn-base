package com.playingjoy.fanrabbit.utils.cache;

import com.google.gson.Gson;
import com.playingjoy.fanrabbit.application.FanRabbitApplication;
import com.playingjoy.fanrabbit.domain.impl.UserLoginInfoBean;

import cn.droidlover.xdroidmvp.cache.SharedPref;
import cn.droidlover.xdroidmvp.log.XLog;

/**
 * Author: Ly
 * Data：2018/4/4-11:20
 * Description: 用户个人信息管理类
 */
public class UserInfoManager {
    private static final String USER_KEY = "sp_user_info";

    private static UserLoginInfoBean sUserLoginInfoBean;

    /**
     * 存储用户信息
     *
     * @param userLoginInfoBean
     */
    public static void setUser(UserLoginInfoBean userLoginInfoBean) {
        SharedPref.getInstance(FanRabbitApplication.getInstance()).putString(USER_KEY, userLoginInfoBean.toString());
    }


    /**
     * 是否登录
     *
     * @return
     */
    public static boolean isLogin() {
        return getUser() != null;
    }

    /**
     * 是否已加入工会
     * @return
     */
    public static boolean isJoinTribe(){
        return false;
    }


    /**
     * 获取用户信息
     *
     * @return 可能会返回null
     */
    public static UserLoginInfoBean getUser() {
        if (sUserLoginInfoBean == null) {
            String userInfoStr = SharedPref.getInstance(FanRabbitApplication.getInstance()).getString(USER_KEY, null);
            XLog.e("the user info get from sp" + userInfoStr);
            sUserLoginInfoBean = new Gson().fromJson(userInfoStr, UserLoginInfoBean.class);
        } else {
            XLog.e("the user info get from memory" + sUserLoginInfoBean);
        }
        return sUserLoginInfoBean;
    }
}
