package com.playingjoy.fanrabbit.utils.net;


import com.playingjoy.fanrabbit.domain.impl.GameListBean;
import com.playingjoy.fanrabbit.domain.impl.ReSetBean;
import com.playingjoy.fanrabbit.domain.impl.RegisterBean;
import com.playingjoy.fanrabbit.domain.impl.SmsCodeBean;
import com.playingjoy.fanrabbit.domain.impl.UserLoginInfoBean;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * @author Ly
 * @date 2017/8/14
 * 接口类
 */

public interface ApiService {

    /**
     * 用户手机号注册接口
     *
     * @param phone
     * @param code
     * @param password
     * @param platform 登录平台 默认填 1
     * @param type     登录平台 默认填 4
     * @return
     */
    @FormUrlEncoded
    @POST("user/register")
    Flowable<RegisterBean> doReg(@Field("phone") String phone, @Field("code") String code,
                                 @Field("password") String password, @Field("platform") String platform,
                                 @Field("type") String type);


    /**
     * 获取验证码
     *
     * @param phone
     * @param smsType 短信类型 register:注册 forget:忘记密码 reset:修改密码 binding:绑定手机 unbind:解绑手机
     * @return
     */
    @FormUrlEncoded
    @POST("http://center.maowan.com:8080/api/register/sms")
    Flowable<SmsCodeBean> doSmsCode(@Field("phone") String phone, @Field("sms_type") String smsType);


    /**
     * 微信登录
     *
     * @param code
     * @param platform
     * @return
     */
    @FormUrlEncoded
    @POST("login/chat")
    Flowable<UserLoginInfoBean> doWeChatLogin(@Field("code") String code, @Field("platform") String platform);

    /**
     * qq微博登录
     *
     * @param fields
     * @return
     */
    @FormUrlEncoded
    @POST("login/social")
    Flowable<UserLoginInfoBean> doQqSinaLogin(@FieldMap Map<String, String> fields);

    /**
     * 手机号码登录
     *
     * @param phone
     * @param password
     * @param platform
     * @return
     */
    @FormUrlEncoded
    @POST("login/phone")
    Flowable<UserLoginInfoBean> doPhoneLogin(@Field("phone") String phone,
                                             @Field("password") String password,
                                             @Field("platform") String platform);

    /**
     * 重设密码
     *
     * @param phone
     * @param code
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("http://center.maowan.com:8080/api/user/reset")
    Flowable<ReSetBean> doReSetPassWord(@Field("phone") String phone, @Field("code") String code, @Field("password") String password);


    @GET("http://www.wanandroid.com/tools/mockapi/4265/gamelist")
    Flowable<GameListBean> getGameList();
}
