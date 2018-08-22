package com.playingjoy.fanrabbit.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.playingjoy.fanrabbit.R;

/**
 * @author Morphine
 * @date 2018-03-29.
 */

public class PromotionAuthTipDialog extends Dialog {

    Button cancelBtn;
    Button confirmBtn;

    public PromotionAuthTipDialog(@NonNull Context context) {
        this(context, R.style.CustomDialog);
    }

    public PromotionAuthTipDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_no_promotion_auth_tip, null);
        cancelBtn = view.findViewById(R.id.btn_cancel);
        confirmBtn = view.findViewById(R.id.btn_confirm);
        this.setContentView(view);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        Display defaultDisplay = getWindow().getWindowManager().getDefaultDisplay();
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
        attributes.width = (int) (defaultDisplay.getWidth() *0.832);
        getWindow().setAttributes(attributes);
    }

    protected PromotionAuthTipDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public PromotionAuthTipDialog setCancelBtnClickListener(View.OnClickListener onClickListener) {
        if (onClickListener == null) return this;
        cancelBtn.setOnClickListener(onClickListener);
        return this;
    }

    public PromotionAuthTipDialog setConfirmBtnClickListener(View.OnClickListener onClickListener) {
        if (onClickListener == null) return this;
        confirmBtn.setOnClickListener(onClickListener);
        return this;
    }
}
