package com.playingjoy.fanrabbit.ui.activity.tribe;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.domain.event.SelectGameEvent;
import com.playingjoy.fanrabbit.ui.presenter.tribe.CreateGroupPresenter;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Created by Ly on 2018/4/18.
 * 创建群
 */

public class CreateGroupActivity extends BaseActivity<CreateGroupPresenter> {
    @BindView(R.id.et_create_group_name)
    EditText etCreateGroupName;
    @BindView(R.id.tv_create_group_constraint)
    TextView tvCreateGroupConstraint;
    @BindView(R.id.tv_create_game_type)
    TextView tvCreateGameType;
    @BindView(R.id.rl_create_game_type)
    RelativeLayout rlCreateGameType;
    @BindView(R.id.tv_add_check_tips)
    TextView tvAddCheckTips;
    @BindView(R.id.tv_add_check_tips_desc)
    TextView tvAddCheckTipsDesc;
    @BindView(R.id.sw_create_verification_notification)
    Switch swCreateVerificationNotification;
    @BindView(R.id.tv_add_chairman_tips)
    TextView tvAddChairmanTips;
    @BindView(R.id.tv_add_chairman_tips_desc)
    TextView tvAddChairmanTipsDesc;
    @BindView(R.id.sw_create_chairman_notification)
    Switch swCreateChairmanNotification;
    @BindView(R.id.rl_create_invite)
    RelativeLayout rlCreateInvite;
    @BindView(R.id.tv_create_submit)
    TextView tvCreateSubmit;

    @Override
    public void initData(Bundle savedInstanceState) {
        setTitleBar(getString(R.string.text_create_group));
        watchGroupNameInput();
        registerSelectGameEvent();
    }

    /**
     * 游戏名字监听
     */
    private void watchGroupNameInput() {
        etCreateGroupName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(etCreateGroupName.getText())) {
                    tvCreateGroupConstraint.setTextColor(getResources().getColor(R.color.hint_color));
                } else {
                    tvCreateGroupConstraint.setTextColor(getResources().getColor(R.color.red_color));
                }
                tvCreateGroupConstraint.setText(String.valueOf(etCreateGroupName.getText().length()));
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_create_group;
    }

    @Override
    public CreateGroupPresenter newP() {
        return new CreateGroupPresenter();
    }

    public static void toCreateGroupActivity(Activity activity) {
        Router.newIntent(activity).to(CreateGroupActivity.class).launch();
    }


    @OnClick({R.id.rl_create_game_type, R.id.rl_create_invite, R.id.tv_create_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_create_game_type:
                SelectGameActivity.toSelectGameActivity(context);
                break;
            case R.id.rl_create_invite:
                InviteActivity.toInviteActivity(context);
                break;
            case R.id.tv_create_submit:
                break;
        }
    }

    /**
     * 注册接受选择事件的eventBus
     */
    private void registerSelectGameEvent() {
        BusProvider.getBus().toFlowable(SelectGameEvent.class)
                .compose(bindToLifecycle())
                .subscribe(selectGameEvent -> {
                    tvCreateGameType.setText(selectGameEvent.getGameName());
                });
    }
}
