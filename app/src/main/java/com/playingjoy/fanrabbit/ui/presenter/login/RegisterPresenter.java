package com.playingjoy.fanrabbit.ui.presenter.login;

import android.text.TextUtils;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BasePresenter;
import com.playingjoy.fanrabbit.domain.impl.ReSetBean;
import com.playingjoy.fanrabbit.domain.impl.SmsCodeBean;
import com.playingjoy.fanrabbit.domain.impl.UserLoginInfoBean;
import com.playingjoy.fanrabbit.ui.activity.login.RegisterActivity;
import com.playingjoy.fanrabbit.utils.net.ErrCodeMessage;
import com.playingjoy.fanrabbit.utils.net.HttpRequest;

import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;
import io.reactivex.Flowable;

/**
 * Author: Ly
 * Data：2018/3/22-12:00
 * Description:
 */
public class RegisterPresenter extends BasePresenter<RegisterActivity> {

    /**
     * 获取验证码
     *
     * @param phone
     * @param smsType 短信类型 register:注册 forget:忘记密码 reset:修改密码 binding:绑定手机 unbind:解绑手机
     */
    public void getSmsCode(String phone, String smsType) {
        if (!Kits.Regular.isPhoneNumber(phone)) {
            getV().showTs(getV().getString(R.string.text_input_phone_number));
            return;
        }
        HttpRequest.getApiService().doSmsCode(phone, smsType)
                .compose(showLoadingDialog())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<SmsCodeBean>(getV(), true) {
                    @Override
                    public void onNext(SmsCodeBean smsCodeBean) {
                        if (smsCodeBean.getStatus().equals(ErrCodeMessage.statusSuc)) {
                            getV().showTs(getV().getString(R.string.text_send_sms_suc));
                            getV().doStartCountDown();
                        } else {
                            getV().showTs(getV().getString(R.string.text_send_sms_err));
                            getV().doStopCountDown();
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        super.onFail(error);
                        getV().doStopCountDown();
                    }
                });
    }

    /***
     * 注册
     * @param phone
     * @param code
     * @param passWord
     * @param platform
     * @param type
     */
    public void doRegister(String phone, String code, String passWord, String platform, String type) {
        if (!Kits.Regular.isPhoneNumber(phone)) {
            getV().showTs(getV().getString(R.string.text_input_phone_number));
            return;
        }
        if (code.length() != 6) {
            getV().showTs(getV().getString(R.string.text_input_code_number));
            return;
        }
        if (!Kits.Regular.isValidatePassWord(passWord)) {
            getV().showTs(getV().getString(R.string.text_input_password_number));
            return;
        }
        platform = "1";
        type = "4";
        HttpRequest.getApiService().doReg(phone, code, passWord, platform, type)
                .compose(showLoadingDialog())
                .compose(getV().bindToLifecycle())
                .flatMap(registerBean -> {
                    if (registerBean.getStatus().equals(ErrCodeMessage.statusSuc)) {
                        return doLogin(phone, passWord, "1");
                    } else {
                        getV().showTs(registerBean.getMessage());
                        getV().dismissLoadingDialog();
                        return Flowable.empty();
                    }
                }).subscribe(new ApiSubscriber<UserLoginInfoBean>(getV(), true) {

            @Override
            public void onNext(UserLoginInfoBean userLoginInfoBean) {
                getV().doLoginSuc(userLoginInfoBean);
            }
        });
    }

    /**
     * 重设密码
     *
     * @param phone
     * @param code
     * @param password
     */
    public void doReSet(String phone, String code, String password) {
        if (!Kits.Regular.isPhoneNumber(phone)) {
            getV().showTs(getV().getString(R.string.text_input_phone_number));
            return;
        }
        if (code.length() != 6) {
            getV().showTs(getV().getString(R.string.text_input_code_number));
            return;
        }
        if (!Kits.Regular.isValidatePassWord(password)) {
            getV().showTs(getV().getString(R.string.text_input_password_number));
            return;
        }
        HttpRequest.getApiService().doReSetPassWord(phone, code, password)
                .compose(getV().bindToLifecycle())
                .compose(showLoadingDialog())
                .subscribe(new ApiSubscriber<ReSetBean>(getV(), true) {

                    @Override
                    public void onNext(ReSetBean reSetBean) {
                        if (reSetBean.getStatus().equals(ErrCodeMessage.statusSuc)) {
                            getV().showTs(getV().getString(R.string.text_reset_success_re_login));
                            getV().finish();
                        } else {
                            getV().showTs(reSetBean.getMessage());
                        }
                    }
                });
    }

    /**
     * 登录
     *
     * @param phone
     * @param passWord
     * @param platform
     * @return
     */
    private Flowable<UserLoginInfoBean> doLogin(String phone, String passWord, String platform) {
        return HttpRequest.getApiService().doPhoneLogin(phone, passWord, platform)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(getV().bindToLifecycle());
    }


}
