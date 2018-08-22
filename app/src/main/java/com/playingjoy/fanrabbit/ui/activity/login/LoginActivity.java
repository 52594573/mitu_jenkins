package com.playingjoy.fanrabbit.ui.activity.login;

import android.Manifest;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.playingjoy.fanrabbit.MainActivity;
import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.domain.event.LoginViewFinishEvent;
import com.playingjoy.fanrabbit.domain.event.WechatCodeEvent;
import com.playingjoy.fanrabbit.domain.impl.UserLoginInfoBean;
import com.playingjoy.fanrabbit.ui.presenter.login.LoginPresenter;
import com.playingjoy.fanrabbit.utils.StatusBarUtil;
import com.playingjoy.fanrabbit.utils.WxConf;
import com.playingjoy.fanrabbit.utils.cache.UserInfoManager;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.router.Router;
import io.reactivex.Observable;

/**
 * Author: Ly
 * Data：2018/3/22-9:55
 * Description: 登录页面
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements UMAuthListener {

    @BindView(R.id.et_login_phone)
    EditText mEtLoginPhone;
    @BindView(R.id.et_login_password)
    EditText mEtLoginPassword;
    @BindView(R.id.iv_login_status)
    ImageView mIvLoginStatus;
    @BindView(R.id.tv_login_quick_register)
    TextView mTvLoginQuickRegister;
    @BindView(R.id.tv_login_forget_password)
    TextView mTvLoginForgetPassword;
    @BindView(R.id.tv_login_do_login)
    TextView mTvLoginDoLogin;
    @BindView(R.id.cb_login_user_agreement)
    CheckBox mCbLoginUserAgreement;
    @BindView(R.id.iv_login_wechat)
    ImageView mIvLoginWechat;
    @BindView(R.id.iv_login_qq)
    ImageView mIvLoginQq;
    @BindView(R.id.iv_login_sina)
    ImageView mIvLoginSina;

    private UMShareAPI mUMShareAPI;
    /***
     * 是否显示密码
     */
    private boolean isShowPassWord = false;

    private IWXAPI mIWXAPI;

    public int keyboardHeight = 0;
    boolean isVisiableForLast = false;
    ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = null;

    @Override
    public void setUpStatusBar() {
        super.setUpStatusBar();
        StatusBarUtil.setColor(this, getResources().getColor(R.color.bg_color));
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        setTitleBarColor(getResources().getColor(R.color.bg_color));
        doWatcherAllInput();
        registerWeChatCode();
        addOnSoftKeyBoardVisibleListener();
        registerFinishEvent();
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public LoginPresenter newP() {
        return new LoginPresenter();
    }


    @OnClick({R.id.iv_login_status, R.id.tv_login_quick_register, R.id.tv_login_forget_password,
            R.id.tv_login_do_login, R.id.iv_login_wechat, R.id.iv_login_qq, R.id.iv_login_sina,
            R.id.tv_login_user_agreement})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_status:
                doSwitchPassWord();
                break;
            case R.id.tv_login_quick_register:
                RegisterActivity.toRegisterActivity(LoginActivity.this, 1);
                back2DefConf();
                break;
            case R.id.tv_login_forget_password:
                RegisterActivity.toRegisterActivity(LoginActivity.this, 2);
                back2DefConf();
                break;
            case R.id.tv_login_do_login:
                if (mCbLoginUserAgreement.isChecked()) {
                    getP().doLoginPassWord(mEtLoginPhone.getText().toString(), mEtLoginPassword.getText().toString());
                } else {
                    showTs(getString(R.string.text_please_check_agreement));
                }
                break;
            case R.id.iv_login_wechat:
                if (mCbLoginUserAgreement.isChecked()) {
                    doLoginWithWechat();
                } else {
                    showTs(getString(R.string.text_please_check_agreement));
                }
                break;
            case R.id.iv_login_qq:
                if (mCbLoginUserAgreement.isChecked()) {
                    doLoginWithQq();
                } else {
                    showTs(getString(R.string.text_please_check_agreement));
                }
                break;
            case R.id.iv_login_sina:
                if (mCbLoginUserAgreement.isChecked()) {
                    doLoginWithSina();
                } else {
                    showTs(getString(R.string.text_please_check_agreement));
                }
                break;
            case R.id.tv_login_user_agreement:
                UserAgreementActivity.toUserAgreementActivity(LoginActivity.this);
                break;
            default:
                break;
        }
    }




    /**
     * QQ登录
     */
    private void doLoginWithQq() {
        if (getUMShareAPI().isInstall(LoginActivity.this, SHARE_MEDIA.QQ)) {
            getRxPermissions().request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe(aBoolean -> {
                        if (aBoolean) {
                            getUMShareAPI().getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, this);
                        } else {
                            showAlert2AppInfo(getString(R.string.text_please_agree_to_authorize));
                        }
                    });
        } else {
            Toast.makeText(LoginActivity.this, R.string.text_un_install_QQ, Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * wechat登录
     */
    private void doLoginWithWechat() {
        if (getUMShareAPI().isInstall(LoginActivity.this, SHARE_MEDIA.WEIXIN)) {
            if (mIWXAPI == null) {
                mIWXAPI = WXAPIFactory.createWXAPI(LoginActivity.this, WxConf.getWxAppKey(), true);
                mIWXAPI.registerApp(WxConf.getWxAppKey());
            }
            SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "wechat_sdk_demo_test";
            mIWXAPI.sendReq(req);
        } else {
            Toast.makeText(LoginActivity.this, R.string.text_un_install_wechat, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 微博登录
     */
    private void doLoginWithSina() {
        getUMShareAPI().getPlatformInfo(LoginActivity.this, SHARE_MEDIA.SINA, this);
    }

    /**
     * 接受回调过来的微信Code
     */
    private void registerWeChatCode() {
        BusProvider.getBus().toFlowable(WechatCodeEvent.class)
                .compose(bindToLifecycle())
                .subscribe(wechatCodeEvent -> {
                    XLog.e("收到了请求回来的code");
                    getP().doLoginWithWechat(wechatCodeEvent.getCode());
                });
    }

    /**
     * 注册关闭事件
     */
    private void registerFinishEvent() {
        BusProvider.getBus().toFlowable(LoginViewFinishEvent.class)
                .compose(bindToLifecycle())
                .subscribe(loginViewFinishEvent -> finish());
    }

    /**
     * 获取UMShareAPI
     *
     * @return
     */
    private UMShareAPI getUMShareAPI() {
        if (mUMShareAPI == null) {
            mUMShareAPI = UMShareAPI.get(this);
        }
        return mUMShareAPI;
    }

    /**
     * 切换密码是否可视
     */
    private void doSwitchPassWord() {
        if (!isShowPassWord) {
            mEtLoginPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            mIvLoginStatus.setImageResource(R.drawable.ic_show);
        } else {
            mEtLoginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            mIvLoginStatus.setImageResource(R.drawable.ic_hide);
        }
        isShowPassWord = !isShowPassWord;
        mEtLoginPassword.postInvalidate();
        CharSequence text = mEtLoginPassword.getText();
        if (!TextUtils.isEmpty(text)) {
            Spannable spanText = (Spannable) text;
            Selection.setSelection(spanText, text.length());
        }
    }

    /**
     * 页面重置
     */
    private void back2DefConf() {
        mEtLoginPassword.setText(null);
        mEtLoginPhone.setText(null);
        mEtLoginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        mIvLoginStatus.setImageResource(R.drawable.ic_hide);
        mCbLoginUserAgreement.setChecked(true);
    }

    /**
     * 账号密码字体监听
     */
    private void doWatcherAllInput() {
        Observable<CharSequence> obPhone = RxTextView.textChanges(mEtLoginPhone);
        Observable<CharSequence> obPassword = RxTextView.textChanges(mEtLoginPassword);
        Observable.combineLatest(obPhone, obPassword, (charSequence, charSequence2) -> {
            String phoneStr = charSequence.toString();
            String passStr = charSequence2.toString();
            boolean isPhoneSuc = !TextUtils.isEmpty(phoneStr);
            boolean isPassSuc = !TextUtils.isEmpty(passStr);
            return isPassSuc && isPhoneSuc;
        })
                .compose(bindToLifecycle())
                .subscribe(aBoolean -> {
                    if (aBoolean) {
                        mTvLoginDoLogin.setClickable(true);
                        mTvLoginDoLogin.setTextColor(getResources().getColor(R.color.main_black));
                        mTvLoginDoLogin.setBackgroundResource(R.drawable.bg_fillet_login);
                    } else {
                        mTvLoginDoLogin.setClickable(false);
                        mTvLoginDoLogin.setTextColor(getResources().getColor(R.color.enable_color));
                        mTvLoginDoLogin.setBackgroundResource(R.drawable.bg_fillet_verification);
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onStart(SHARE_MEDIA share_media) {
        showLoadingDialog();
        XLog.e("onStart---" + share_media.getName());
    }

    @Override
    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
        XLog.e("onComplete---" + map.toString());
        dismissLoadingDialog();
        if (share_media == SHARE_MEDIA.QQ) {
            Map<String, String> platformInfo = new HashMap<>();
            platformInfo.put("social", map.get("openid"));
            platformInfo.put("credential", map.get("accessToken"));
            platformInfo.put("expire_time", map.get("expiration"));
            platformInfo.put("credential", map.get("accessToken"));
            platformInfo.put("platform", "1");
            platformInfo.put("gender", map.get("gender").equals("男") ? "1" : "2");
            platformInfo.put("username", map.get("screen_name"));
            platformInfo.put("avatar", map.get("profile_image_url"));
            getP().doLoginWithQq(platformInfo);
        } else if (share_media == SHARE_MEDIA.SINA) {
            Map<String, String> platformInfo = new HashMap<>();
            platformInfo.put("social", map.get("uid"));
            platformInfo.put("credential", map.get("accessToken"));
            platformInfo.put("expire_time", map.get("expires_in"));
            platformInfo.put("credential", map.get("accessToken"));
            platformInfo.put("platform", "1");
            platformInfo.put("gender", map.get("gender").equals("男") ? "1" : "2");
            platformInfo.put("username", map.get("name"));
            platformInfo.put("avatar", map.get("profile_image_url"));
            getP().doLoginWithSina(platformInfo);
        }
    }

    @Override
    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
        dismissLoadingDialog();
        showTs(getString(R.string.text_net_error));
        XLog.e("onError---" + throwable.toString());
    }

    @Override
    public void onCancel(SHARE_MEDIA share_media, int i) {
        dismissLoadingDialog();
        XLog.e("onCancel---" + share_media.toString());
    }

    /**
     * 登录成功
     *
     * @param userLoginInfoBean
     */
    public void LoginSuc(UserLoginInfoBean userLoginInfoBean) {
        UserInfoManager.setUser(userLoginInfoBean);
        MainActivity.toMainActivity(LoginActivity.this);
        finish();
    }

    public static void toLoginActivity(Activity activity) {
        Router.newIntent(activity).to(LoginActivity.class).launch();
    }


    /**
     * 获取软键盘高度
     */
    public void addOnSoftKeyBoardVisibleListener() {
        keyboardHeight = getP().getKeyBoardHeight();
        if (keyboardHeight > 0) {
            return;
        }
        final View decorView = getWindow().getDecorView();
        onGlobalLayoutListener = () -> {
            Rect rect = new Rect();
            decorView.getWindowVisibleDisplayFrame(rect);
            //计算出可见屏幕的高度
            int displayHight = rect.bottom - rect.top;
            //获得屏幕整体的高度
            int hight = decorView.getHeight();
            boolean visible = (double) displayHight / hight < 0.8;
            int statusBarHeight = 0;
            try {
                Class<?> c = Class.forName("com.android.internal.R$dimen");
                Object obj = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = Integer.parseInt(field.get(obj).toString());
                statusBarHeight = context.getResources().getDimensionPixelSize(x);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (visible && visible != isVisiableForLast) {
                //获得键盘高度
                keyboardHeight = hight - displayHight - statusBarHeight;
                getP().saveKeyBoardHeight(keyboardHeight);
            }
            isVisiableForLast = visible;
        };
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
    }
}
