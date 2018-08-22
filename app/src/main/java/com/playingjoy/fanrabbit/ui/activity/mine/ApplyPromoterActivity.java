package com.playingjoy.fanrabbit.ui.activity.mine;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.playingjoy.fanrabbit.BuildConfig;
import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.ui.dialog.ModifyUserPicDialog;
import com.playingjoy.fanrabbit.ui.presenter.mine.ApplyPromoterPresenter;
import com.playingjoy.fanrabbit.utils.GiftsDialogUtil;
import com.playingjoy.fanrabbit.utils.PictureUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.router.Router;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: Ly
 * Data：2018/4/9-17:18
 * Description: 申请推广员界面
 */
public class ApplyPromoterActivity extends BaseActivity<ApplyPromoterPresenter> {
    @BindView(R.id.et_apply_real_name)
    EditText mEtApplyRealName;
    @BindView(R.id.et_apply_card_num)
    EditText mEtApplyCardNum;
    @BindView(R.id.iv_apply_positive)
    ImageView mIvApplyPositive;
    @BindView(R.id.iv_apply_positive_pic)
    ImageView mIvApplyPositivePic;
    @BindView(R.id.iv_apply_negative_pic)
    ImageView mIvApplyNegativePic;
    @BindView(R.id.iv_apply_negative)
    ImageView mIvApplyNegative;
    @BindView(R.id.cb_apply_wechat)
    CheckBox mCbApplyWechat;
    @BindView(R.id.et_apply_wechat)
    EditText mEtApplyWechat;
    @BindView(R.id.cb_apply_ali)
    CheckBox mCbApplyAli;
    @BindView(R.id.et_apply_ali)
    EditText mEtApplyAli;
    @BindView(R.id.tv_apply_submit)
    TextView mTvApplySubmit;

    private GiftsDialogUtil mGiftsDialogUtil;
    /***
     *  POSITIVE_CODE 正面
     *  NEGATIVE_CODE 反面
     */
    private static final int POSITIVE_CODE = 0, NEGATIVE_CODE = 1;

    @Override
    public void initData(Bundle savedInstanceState) {
        setTitleBar(getString(R.string.text_apply_promotion));
        initCheckBoxListener();
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_apply_promoter;
    }

    @Override
    public ApplyPromoterPresenter newP() {
        return new ApplyPromoterPresenter();
    }

    public static void toApplyPromoterActivity(Activity activity) {
        Router.newIntent(activity).to(ApplyPromoterActivity.class).launch();
    }


    @OnClick({R.id.iv_apply_positive_pic, R.id.iv_apply_negative_pic, R.id.tv_apply_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_apply_positive_pic:
                doSelectPic(POSITIVE_CODE);
                break;
            case R.id.iv_apply_negative_pic:
                doSelectPic(NEGATIVE_CODE);
                break;
            case R.id.tv_apply_submit:
                showSubmitDialog();
                break;
        }
    }

    /**
     * 显示提交成功的dialog
     */
    private void showSubmitDialog() {
        if (mGiftsDialogUtil == null) {
            mGiftsDialogUtil = new GiftsDialogUtil();
        }
        mGiftsDialogUtil.getAccountBindDialog(ApplyPromoterActivity.this, "提交成功", "您的推广员申请已提交成功，将于 3 个 工作日内进行审核确定，请留意消息中 心的信息。",
                "知道了", v -> {

                }).show();
    }

    /**
     * 初始化CheckBox
     */
    private void initCheckBoxListener() {
        mCbApplyWechat.setChecked(true);
        mEtApplyAli.setHint("");
        mEtApplyAli.setClickable(false);
        mCbApplyWechat.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                mCbApplyAli.setChecked(false);
                mEtApplyAli.setHint("");
                mEtApplyAli.setClickable(false);
                mEtApplyWechat.setHint(getString(R.string.text_please_input_wechat_account));
                mEtApplyWechat.setClickable(true);
            } else {
                mCbApplyWechat.setChecked(false);
                mEtApplyWechat.setHint("");
                mEtApplyWechat.setClickable(false);
            }
        });
        mCbApplyAli.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                mCbApplyWechat.setChecked(false);
                mEtApplyWechat.setHint("");
                mEtApplyWechat.setClickable(false);
                mEtApplyAli.setHint(getString(R.string.text_please_input_ali_account));
                mEtApplyAli.setClickable(true);
            } else {
                mCbApplyAli.setChecked(false);
                mEtApplyAli.setHint("");
                mEtApplyAli.setClickable(false);
            }
        });
    }

    /**
     * 选择身份证
     *
     * @param code
     */
    private void doSelectPic(int code) {
        getRxPermissions().request(Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                    if (aBoolean) {
                        Kits.PHOTO.getImageFromAlbum(ApplyPromoterActivity.this, code);
                    } else {
                        showAlert2AppInfo(getString(R.string.text_please_agree_to_authorize));
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case POSITIVE_CODE:
                //获取相册的图片
                String pathPositive;
                if (data == null) return;
                Uri urlPositive = data.getData();
                int sdkVersionPositive = Integer.valueOf(Build.VERSION.SDK);
                if (sdkVersionPositive >= 19) {
                    pathPositive = PictureUtils.getPath_above19(ApplyPromoterActivity.this, urlPositive);
                } else {
                    pathPositive = PictureUtils.getFilePath_below19(ApplyPromoterActivity.this, urlPositive);
                }
                if (!TextUtils.isEmpty(pathPositive)) {
                    mIvApplyPositivePic.setImageBitmap(BitmapFactory.decodeFile(pathPositive));
                }
                break;
            case NEGATIVE_CODE:
                //获取相册的图片
                String pathNegative;
                if (data == null) return;
                Uri urlNegative = data.getData();
                int sdkVersionNegative = Integer.valueOf(Build.VERSION.SDK);
                if (sdkVersionNegative >= 19) {
                    pathNegative = PictureUtils.getPath_above19(ApplyPromoterActivity.this, urlNegative);
                } else {
                    pathNegative = PictureUtils.getFilePath_below19(ApplyPromoterActivity.this, urlNegative);
                }
                if (!TextUtils.isEmpty(pathNegative)) {
                    mIvApplyNegativePic.setImageBitmap(BitmapFactory.decodeFile(pathNegative));
                }
            default:
                break;
        }
    }
}
