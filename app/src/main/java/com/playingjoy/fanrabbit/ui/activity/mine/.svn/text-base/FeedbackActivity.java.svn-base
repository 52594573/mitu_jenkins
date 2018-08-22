package com.playingjoy.fanrabbit.ui.activity.mine;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.playingjoy.fanrabbit.BuildConfig;
import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.ui.activity.comm.BigImageActivity;
import com.playingjoy.fanrabbit.ui.dialog.ModifyUserPicDialog;
import com.playingjoy.fanrabbit.ui.presenter.mine.FeedbackPresenter;
import com.playingjoy.fanrabbit.utils.PictureUtils;
import com.playingjoy.fanrabbit.utils.StatusBarUtil;
import com.playingjoy.fanrabbit.widget.BorderImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.router.Router;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * Author: Ly
 * Data：2018/4/11-10:45
 * Description: 意见反馈页面
 */
public class FeedbackActivity extends BaseActivity<FeedbackPresenter> {
    @BindView(R.id.iv_feedback_pic_4)
    BorderImageView mIvFeedBackPic4;
    @BindView(R.id.et_feedback_str)
    EditText mEtFeedbackStr;
    @BindView(R.id.iv_feedback_pic_1)
    BorderImageView mIvFeedbackPic1;
    @BindView(R.id.iv_feedback_pic_2)
    BorderImageView mIvFeedbackPic2;
    @BindView(R.id.iv_feedback_pic_3)
    BorderImageView mIvFeedbackPic3;
    @BindView(R.id.et_feedback_qq)
    EditText mEtFeedbackQq;
    @BindView(R.id.et_feedback_phone)
    EditText mEtFeedbackPhone;
    @BindView(R.id.tv_feedback_submit)
    TextView mTvFeedbackSubmit;
    @BindView(R.id.tv_feedback_pic_num)
    TextView mTvFeedBackPicNum;

    /**
     * 用于存储照片的path List
     */
    private ArrayList<String> picPathList = new ArrayList<>();
    /**
     * 1 相机
     * 2 图库
     */
    private static final int PIC_CAMERA = 1, PIC_GALLERY = 2;
    private static final int PIC_MAX = 4;
    /**
     * 临时图片路径
     */
    private String mTempPhotoPath;




    @Override
    public void initData(Bundle savedInstanceState) {
        setTitleBar(getString(R.string.text_feedback));
        mTvFeedBackPicNum.setText(String.format(getString(R.string.text_pic_num_tips), String.valueOf(picPathList.size())));
    }



    @Override
    public int getLayoutId() {
        return R.layout.activity_feedback;
    }

    @Override
    public FeedbackPresenter newP() {
        return new FeedbackPresenter();
    }


    public static void toFeedBackActivity(Activity activity) {
        Router.newIntent(activity).to(FeedbackActivity.class).launch();
    }


    @OnClick({R.id.iv_feedback_pic_1, R.id.iv_feedback_pic_2, R.id.iv_feedback_pic_3, R.id.iv_feedback_pic_4, R.id.tv_feedback_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_feedback_pic_1:
                if (picPathList.size() < 1) {
                    showDialog2SelectPic();
                } else {
                    toBigImageActivity(0);

                }
                break;
            case R.id.iv_feedback_pic_2:
                if (picPathList.size() < 2) {
                    showDialog2SelectPic();
                } else {
                    toBigImageActivity(1);
                }
                break;
            case R.id.iv_feedback_pic_3:
                if (picPathList.size() < 3) {
                    showDialog2SelectPic();
                } else {
                    toBigImageActivity(2);
                }
                break;
            case R.id.iv_feedback_pic_4:
                if (picPathList.size() < 4) {
                    showDialog2SelectPic();
                } else {
                    toBigImageActivity(3);
                }
                break;
            case R.id.tv_feedback_submit:
                break;
        }
    }

    private void toBigImageActivity(int position) {
        BigImageActivity.toBigImageActivity(this, picPathList, position);
    }

    /**
     * 显示选择图片的dialog
     */
    private void showDialog2SelectPic() {
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
                                mTempPhotoPath = Kits.PHOTO.takePhoto(FeedbackActivity.this, BuildConfig.APPLICATION_ID, PIC_CAMERA);
                            }

                            @Override
                            public void onClickToFromAlbum() {
                                MultiImageSelector.create(context)
                                        .showCamera(false) // 是否显示相机. 默认为显示
                                        .count(PIC_MAX - picPathList.size()) // 最大选择图片数量, 默认为9. 只有在选择模式为多选时有效
                                        .single() // 单选模式
                                        .multi() // 多选模式, 默认模式;
                                        .start(context, PIC_GALLERY);
//                                Kits.PHOTO.getImageFromAlbum(FeedbackActivity.this, PIC_GALLERY);
                            }
                        });
                    } else {
                        showAlert2AppInfo(getString(R.string.text_please_agree_to_authorize));
                    }
                });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PIC_CAMERA:
//                获取图库的相片
                if (resultCode != Activity.RESULT_OK) return;
                Uri uri = Uri.parse(mTempPhotoPath);
                String path = uri.getPath();
                PictureUtils.galleryAddPic(mTempPhotoPath, this);
                picPathList.add(path);
                break;
            case PIC_GALLERY:
                //获取相册的图片
                if (resultCode == RESULT_OK) {
                    // 获取返回的图片列表
                    List<String> pathtemp = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                    picPathList.addAll(pathtemp);
                }
                break;
            default:
                break;
        }
        if (picPathList.size() > 0) {
            mIvFeedbackPic1.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mIvFeedbackPic1.setImageBitmap(BitmapFactory.decodeFile(picPathList.get(0)));
        }
        if (picPathList.size() > 1) {
            mIvFeedbackPic2.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mIvFeedbackPic2.setImageBitmap(BitmapFactory.decodeFile(picPathList.get(1)));
        }
        if (picPathList.size() > 2) {
            mIvFeedbackPic3.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mIvFeedbackPic3.setImageBitmap(BitmapFactory.decodeFile(picPathList.get(2)));
        }
        if (picPathList.size() > 3) {
            mIvFeedBackPic4.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mIvFeedBackPic4.setImageBitmap(BitmapFactory.decodeFile(picPathList.get(3)));
        }
        if (picPathList.size() == 0) {
            mIvFeedbackPic1.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.pic_add_));
        }
        if (picPathList.size() == 1) {
            mIvFeedbackPic2.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.pic_add_));
        }
        if (picPathList.size() == 2) {
            mIvFeedbackPic3.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.pic_add_));
        }
        if (picPathList.size() == 3) {
            mIvFeedBackPic4.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.pic_add_));
        }
        mTvFeedBackPicNum.setText(String.format(getString(R.string.text_pic_num_tips), String.valueOf(picPathList.size())));
    }

}
