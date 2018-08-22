package com.playingjoy.fanrabbit.ui.activity.mine;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.playingjoy.fanrabbit.BuildConfig;
import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.ui.dialog.ModifyUserPicDialog;
import com.playingjoy.fanrabbit.ui.presenter.mine.UserInfoPresenter;
import com.playingjoy.fanrabbit.utils.PictureUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.router.Router;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: Ly
 * Data：2018/4/4-17:23
 * Description: 用户个人信息
 */
public class UserInfoActivity extends BaseActivity<UserInfoPresenter> {
    @BindView(R.id.iv_user_info_more)
    ImageView mIvUserInfoMore;
    @BindView(R.id.iv_user_info_pic)
    CircleImageView mIvUserInfoPic;
    @BindView(R.id.tv_user_info_nickname)
    TextView mTvUserInfoNickname;
    @BindView(R.id.tv_user_info_sex)
    TextView mTvUserInfoSex;
    @BindView(R.id.tv_user_info_birthday)
    TextView mTvUserInfoBirthday;
    @BindView(R.id.tv_user_info_desc)
    TextView mTvUserInfoDesc;

    /**
     * 临时缓存目录
     */
    private String cacheFile = null;
    private String mPublicPhotoPath;
    /***
     *REQ_GALLERY 拍照、
     * CROP_PHOTO 截图
     * REQUEST_CODE_PICK_IMAGE 相册
     */
    private static final int REQ_GALLERY = 333, CROP_PHOTO = 444, REQUEST_CODE_PICK_IMAGE = 555;

    @Override
    public void initData(Bundle savedInstanceState) {
        setTitleBar(getString(R.string.text_user_info));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    public UserInfoPresenter newP() {
        return new UserInfoPresenter();
    }

    public static void toUserInfoActivity(Activity activity) {
        Router.newIntent(activity).to(UserInfoActivity.class).launch();
    }


    @OnClick({R.id.iv_user_info_pic, R.id.tv_user_info_nickname, R.id.tv_user_info_sex, R.id.tv_user_info_birthday, R.id.tv_user_info_desc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_user_info_pic:
                doSelectPic();
                break;
            case R.id.tv_user_info_nickname:
                ModifyNickNameActivity.toModifyUserNickNameActivity(context, mTvUserInfoNickname.getText().toString());
                break;
            case R.id.tv_user_info_sex:
                doSelectSex();
                break;
            case R.id.tv_user_info_birthday:
                doSelectBirthday();
                break;
            case R.id.tv_user_info_desc:
                ModifyDescActivity.toModifyDescActivity(context, mTvUserInfoDesc.getText().toString());
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_GALLERY:
                if (resultCode != Activity.RESULT_OK) return;
                Uri uri = Uri.parse(mPublicPhotoPath);
                String path = uri.getPath();
                PictureUtils.galleryAddPic(mPublicPhotoPath, this);
                cacheFile = Kits.PHOTO.startPhotoZoom(UserInfoActivity.this, new File(path), CROP_PHOTO);
                break;
            case CROP_PHOTO:
                mIvUserInfoPic.setImageBitmap(BitmapFactory.decodeFile(cacheFile));
                break;
            case REQUEST_CODE_PICK_IMAGE:
                //获取相册的图片
                if (data == null) return;
                Uri urlFrom = data.getData();
                int sdkVersion = Integer.valueOf(Build.VERSION.SDK);
                if (sdkVersion >= 19) {
                    path = PictureUtils.getPath_above19(UserInfoActivity.this, urlFrom);
                } else {
                    path = PictureUtils.getFilePath_below19(UserInfoActivity.this, urlFrom);
                }
                if (!TextUtils.isEmpty(path))
                    cacheFile = Kits.PHOTO.startPhotoZoom(UserInfoActivity.this, new File(path), CROP_PHOTO);
                break;
            default:
                break;
        }
    }


    /**
     * 选择头像
     */
    private void doSelectPic() {
        getRxPermissions().request(Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                    if (aBoolean) {
                        ModifyUserPicDialog.showDialog(this).setOnPickerClickListener(new ModifyUserPicDialog.onPickerClickListener() {
                            @Override
                            public void onClickToTakePhone() {
                                mPublicPhotoPath = Kits.PHOTO.takePhoto(UserInfoActivity.this, BuildConfig.APPLICATION_ID, REQ_GALLERY);
                            }

                            @Override
                            public void onClickToFromAlbum() {
                                Kits.PHOTO.getImageFromAlbum(UserInfoActivity.this, REQUEST_CODE_PICK_IMAGE);
                            }
                        });
                    } else {
                        showAlert2AppInfo(getString(R.string.text_please_agree_to_authorize));
                    }
                });
    }

    /**
     * 选择生日
     */
    private void doSelectBirthday() {
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        startDate.set(1900, 0, 1);
        TimePickerView pvTime = new TimePickerBuilder(UserInfoActivity.this, (date, v) -> mTvUserInfoBirthday.setText(Kits.Date.getYmdDot(date)))
                .setRangDate(startDate, endDate)
                .setDate(endDate)
                .setTitleText(getString(R.string.text_select_birthday))
                .setTitleColor(getResources().getColor(R.color.second_color)).setTitleSize(16)
                .setCancelColor(getResources().getColor(R.color.yellow_color))
                .setSubmitColor(getResources().getColor(R.color.yellow_color)).build();
        pvTime.show();
    }

    /**
     * 选择年龄
     */
    private void doSelectSex() {
        List<String> sexList = new ArrayList<>();
        sexList.add(getString(R.string.text_is_boy));
        sexList.add(getString(R.string.text_is_girl));
        OptionsPickerView pvOptions = new OptionsPickerBuilder(UserInfoActivity.this, (options1, option2, options3, v) -> {
            //返回的分别是三个级别的选中位置
            mTvUserInfoSex.setText(sexList.get(options1));
        }).setTitleText(getString(R.string.text_select_sex))
                .setTitleColor(getResources().getColor(R.color.second_color)).setTitleSize(16)
                .setCancelColor(getResources().getColor(R.color.yellow_color))
                .setSubmitColor(getResources().getColor(R.color.yellow_color)).build();
        pvOptions.setPicker(sexList);
        pvOptions.show();
    }
}
