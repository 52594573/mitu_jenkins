package com.playingjoy.fanrabbit.ui.activity.login;

import android.app.Activity;
import android.os.Bundle;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.playingjoy.fanrabbit.MainActivity;
import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.domain.event.LoginViewFinishEvent;
import com.playingjoy.fanrabbit.domain.impl.UserLoginInfoBean;
import com.playingjoy.fanrabbit.ui.presenter.login.RegisterPresenter;
import com.playingjoy.fanrabbit.utils.CountDownUtils;
import com.playingjoy.fanrabbit.utils.cache.UserInfoManager;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xdroidmvp.router.Router;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: Ly
 * Data：2018/3/22-12:00
 * Description:注册页面
 */
public class RegisterActivity extends BaseActivity<RegisterPresenter> {

    @BindView(R.id.et_register_phone)
    EditText mEtRegisterPhone;
    @BindView(R.id.et_register_verification)
    EditText mEtRegisterVerification;
    @BindView(R.id.ll_register_password)
    LinearLayout mLlRegisterPassword;
    @BindView(R.id.et_register_password)
    EditText mEtRegisterPassword;
    @BindView(R.id.tv_register_to_submit)
    TextView mTvRegisterToSubmit;
    @BindView(R.id.cb_register_user_agreement)
    CheckBox mCbRegisterUserAgreement;
    @BindView(R.id.tv_register_user_agreement)
    TextView mTvRegisterUserAgreement;
    @BindView(R.id.ll_title_right)
    LinearLayout mLlTitleRight;
    @BindView(R.id.iv_register_status)
    ImageView mIvRegisterStatus;
    @BindView(R.id.tv_register_get_verification)
    TextView mTvRegisterGetVerification;

    private boolean isShowPassWord = false;
    /**
     * 默认是
     * 注册 1
     * 忘记密码 2
     * 手机绑定 3
     * 手机解绑 4
     */
    private int mRegOrForgetFlag = 1;

    private CountDownUtils mCountDownUtils;

    @Override
    public void initData(Bundle savedInstanceState) {
        mRegOrForgetFlag = getIntent().getIntExtra("regOrForgetFlag", 1);
        if (mRegOrForgetFlag == 1) {
            // 注册
            setTitleBar(getString(R.string.text_register));
        } else if (mRegOrForgetFlag == 2) {
            // 忘记密码
            setTitleBar(getString(R.string.text_forget_password));
        } else if (mRegOrForgetFlag == 3) {
            // 手机绑定
            setTitleBar(getString(R.string.text_phone_bind));
            mCbRegisterUserAgreement.setVisibility(View.GONE);
            mCbRegisterUserAgreement.setChecked(true);
            mTvRegisterUserAgreement.setVisibility(View.GONE);
            mTvRegisterToSubmit.setText(getString(R.string.text_bind));
        } else if (mRegOrForgetFlag == 4) {
            // 手机解绑
            setTitleBar(getString(R.string.text_phone_un_bind));
            mCbRegisterUserAgreement.setVisibility(View.GONE);
            mCbRegisterUserAgreement.setChecked(true);
            mTvRegisterUserAgreement.setVisibility(View.GONE);
            mTvRegisterToSubmit.setText(getString(R.string.text_un_bind));
            mLlRegisterPassword.setVisibility(View.GONE);
            mEtRegisterPassword.setText(R.string.text_input_password_number);
        }
        doWatcherInputPhone();
        doWatcherAllInput();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public RegisterPresenter newP() {
        return new RegisterPresenter();
    }


    /**
     * @param activity
     * @param regOrForgetFlag 1 快速注册 2 忘记密码 3 绑定手机  4  解绑手机
     */
    public static void toRegisterActivity(Activity activity, int regOrForgetFlag) {
        Router.newIntent(activity).putInt("regOrForgetFlag", regOrForgetFlag).to(RegisterActivity.class).launch();
    }


    @OnClick({R.id.iv_register_status, R.id.tv_register_to_submit, R.id.tv_register_get_verification, R.id.tv_register_user_agreement})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_register_status:
                doSwitchPassWord();
                break;
            case R.id.tv_register_to_submit:
                if (mCbRegisterUserAgreement.isChecked()) {
                    if (mRegOrForgetFlag == 1) {
//                      注册
                        getP().doRegister(mEtRegisterPhone.getText().toString(), mEtRegisterVerification.getText().toString(), mEtRegisterPassword.getText().toString(), null, null);
                    } else if (mRegOrForgetFlag == 2) {
//                      忘记密码
                        getP().doReSet(mEtRegisterPhone.getText().toString(), mEtRegisterVerification.getText().toString(), mEtRegisterPassword.getText().toString());
                    } else if (mRegOrForgetFlag == 3) {
//                        账号绑定
                    } else if (mRegOrForgetFlag == 4) {
//                        账号解绑
                    }
                } else {
                    showTs(getString(R.string.text_please_check_agreement));
                }
                break;
            case R.id.tv_register_get_verification:
                if (mRegOrForgetFlag == 1) {
//                      注册
                    getP().getSmsCode(mEtRegisterPhone.getText().toString(), "register");
                } else if (mRegOrForgetFlag == 2) {
//                      忘记密码
                    getP().getSmsCode(mEtRegisterPhone.getText().toString(), "forget");
                } else if (mRegOrForgetFlag == 3) {
//                       账号绑定
                    getP().getSmsCode(mEtRegisterPhone.getText().toString(), "binding");
                } else if (mRegOrForgetFlag == 4) {
//                    账号解绑
                    getP().getSmsCode(mEtRegisterPhone.getText().toString(), "unbind");
                }

                break;
            case R.id.tv_register_user_agreement:
                UserAgreementActivity.toUserAgreementActivity(RegisterActivity.this);
                break;
            default:
                break;
        }
    }

    /**
     * 切换密码是否可视
     */
    private void doSwitchPassWord() {
        if (!isShowPassWord) {
            mEtRegisterPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            mIvRegisterStatus.setImageResource(R.drawable.ic_show);
        } else {
            mEtRegisterPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            mIvRegisterStatus.setImageResource(R.drawable.ic_hide);
        }
        isShowPassWord = !isShowPassWord;
        mEtRegisterPassword.postInvalidate();
        CharSequence text = mEtRegisterPassword.getText();
        if (!TextUtils.isEmpty(text)) {
            Spannable spanText = (Spannable) text;
            Selection.setSelection(spanText, text.length());
        }
    }

    /**
     * 监听手机输入
     */
    private void doWatcherInputPhone() {
        RxTextView.textChanges(mEtRegisterPhone)
                .subscribeOn(Schedulers.io())
                .compose(bindToLifecycle())
                .debounce(200, TimeUnit.MILLISECONDS)
                .map(charSequence -> !TextUtils.isEmpty(charSequence))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                    if (aBoolean) {
                        mTvRegisterGetVerification.setClickable(true);
                        mTvRegisterGetVerification.setTextColor(getResources().getColor(R.color.main_black));
                        mTvRegisterGetVerification.setBackgroundResource(R.drawable.bg_fillet_login);
                    } else {
                        mTvRegisterGetVerification.setClickable(false);
                        mTvRegisterGetVerification.setTextColor(getResources().getColor(R.color.enable_color));
                        mTvRegisterGetVerification.setBackgroundResource(R.drawable.bg_fillet_verification);
                    }
                });
    }


    /**
     * 监听手机输入 验证码输入 密码输入
     * 密码 6 到 16
     */
    private void doWatcherAllInput() {
        Observable<CharSequence> obPhone = RxTextView.textChanges(mEtRegisterPhone);
        Observable<CharSequence> obVerification = RxTextView.textChanges(mEtRegisterVerification);
        Observable<CharSequence> obPassWord = RxTextView.textChanges(mEtRegisterPassword);
        Observable.combineLatest(obPhone, obVerification, obPassWord, (charSequence, charSequence2, charSequence3) -> {
            String phoneStr = charSequence.toString();
            String VerificationStr = charSequence2.toString();
            String passWordStr = charSequence3.toString();
            return !TextUtils.isEmpty(phoneStr) && !TextUtils.isEmpty(VerificationStr) && !TextUtils.isEmpty(passWordStr);
        })
                .compose(bindToLifecycle())
                .subscribe(aBoolean -> {
                    if (aBoolean) {
                        mTvRegisterToSubmit.setClickable(true);
                        mTvRegisterToSubmit.setTextColor(getResources().getColor(R.color.main_black));
                        mTvRegisterToSubmit.setBackgroundResource(R.drawable.bg_fillet_login);
                    } else {
                        mTvRegisterToSubmit.setClickable(false);
                        mTvRegisterToSubmit.setTextColor(getResources().getColor(R.color.enable_color));
                        mTvRegisterToSubmit.setBackgroundResource(R.drawable.bg_fillet_verification);
                    }
                });
    }


    /**
     * 倒数计时
     */
    public void doStartCountDown() {
        if (mCountDownUtils == null) {
            mCountDownUtils = new CountDownUtils(this, 60000, 1000, mTvRegisterGetVerification);
        }
        mCountDownUtils.start();
        mTvRegisterGetVerification.setTextColor(getResources().getColor(R.color.enable_color));
        mTvRegisterGetVerification.setBackgroundResource(R.drawable.bg_fillet_verification);
    }

    /**
     * 停止倒数技术
     */
    public void doStopCountDown() {
        if (mCountDownUtils == null) {
            mCountDownUtils = new CountDownUtils(this, 60000, 1000, mTvRegisterGetVerification);
        }
        mCountDownUtils.doFinish(getString(R.string.text_get_verification_code));
        mTvRegisterGetVerification.setTextColor(getResources().getColor(R.color.main_black));
        mTvRegisterGetVerification.setBackgroundResource(R.drawable.bg_fillet_login);
    }

    /**
     * 登录成功
     *
     * @param userLoginInfoBean
     */
    public void doLoginSuc(UserLoginInfoBean userLoginInfoBean) {
        UserInfoManager.setUser(userLoginInfoBean);
        MainActivity.toMainActivity(RegisterActivity.this);
        BusProvider.getBus().post(new LoginViewFinishEvent());
        finish();
    }


}
